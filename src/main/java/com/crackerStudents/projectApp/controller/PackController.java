package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.CardService;
import com.crackerStudents.projectApp.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PackController {

    private final PackService packService;
    private final CardService cardService;

    @Autowired
    public PackController(PackService packService, CardService cardService) {
        this.packService = packService;
        this.cardService = cardService;
    }

    @GetMapping("/packs")
    public String view(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("packs", packService.getUserPacks(user));
        return "packs";
    }

    @GetMapping("/packs/{packName}")
    public String main(@PathVariable String packName, Model model, @AuthenticationPrincipal User user) {
        //[TODO]: add user as arguement
        PackDTO packDTO = packService.getPackDTOByName(packName);
        System.out.println(packDTO);
        System.out.println(packDTO.getName());
        model.addAttribute("pack", packDTO);
        model.addAttribute("cards", packDTO.getCards());
        model.addAttribute("time", packDTO.getCreated().toString());
        return "pack";
    }

    //[TODO]: Binding result сделать красиво
    @PostMapping("/packs/{packName}")
    public String addCard(Card card,
                          @PathVariable String packName, @AuthenticationPrincipal User user) {

        card.setAuthor(user);
        packService.addCardAndSave(card, packName, user);
        return "redirect:/packs/" + packName;
    }

    @GetMapping("/packs/{packName}/{idCard}")
    public String editCardShow(@PathVariable String packName, @PathVariable String idCard, Model model, @AuthenticationPrincipal User user) {
        //[TODO]: Add user check
        PackDTO packDTO = packService.getPackDTOByName(packName);
        Card card = packDTO.getCard(idCard);
        model.addAttribute("card", card);
        model.addAttribute("message", null);
        return "cardEdit";
    }

    @PostMapping("/packs/{name}/{id}")
    public String editCardDo(@PathVariable String name, @PathVariable String id, @RequestParam String answer, @RequestParam String question, @AuthenticationPrincipal User user, Model model) {
        //[TODO]: Add user check
        PackDTO packDTO = packService.getPackDTOByName(name);
        Card card = packDTO.getCard(id);
        if (!StringUtils.isEmpty(answer) && !StringUtils.isEmpty(question)) {
            cardService.updateCard(card, answer, question);
            return "redirect:/packs/"+ name;
        } else {
            model.addAttribute("card", card);
            model.addAttribute("message", "Заполните оба поля");
            return "cardEdit";
        }
    }
}