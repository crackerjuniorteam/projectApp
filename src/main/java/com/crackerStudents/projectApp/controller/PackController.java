package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.Pack;
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
        Pack pack = packService.getPackByName(packName, user);
        System.out.println(pack);
        System.out.println(pack.getName());
        model.addAttribute("pack", pack);
        model.addAttribute("cards", pack.getCards());
        model.addAttribute("time", pack.getCreated().toString());
        return "pack";
    }

    //[TODO]: Binding result сделать красиво
    @PostMapping("/packs/{namePack}")
    public String addCard(Card card, Model model,
                          @PathVariable String namePack, @AuthenticationPrincipal User user) {

        card.setAuthor(user);
        packService.AddCardAndSave(card, namePack, user);
        return "redirect:/packs/" + namePack;
    }

    @GetMapping("/packs/{namePack}/{idCard}")
    public String editCardShow(@PathVariable String namePack, @PathVariable String idCard, Model model, @AuthenticationPrincipal User user) {
        Pack pack = packService.getPackByName(namePack, user);
        Card card = pack.getCard(idCard);
        model.addAttribute("card", card);
        model.addAttribute("message", null);
        return "cardEdit";
    }

    @PostMapping("/packs/{name}/{id}")
    public String editCardDo(@PathVariable String name, @PathVariable String id, @RequestParam String answer, @RequestParam String question, @AuthenticationPrincipal User user, Model model) {
        Pack pack = packService.getPackByName(name, user);
        Card card = pack.getCard(id);
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