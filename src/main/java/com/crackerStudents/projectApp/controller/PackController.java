package com.crackerStudents.projectApp.controller;


import com.crackerStudents.projectApp.DTO.PackDTO;
import com.crackerStudents.projectApp.domain.Card;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.service.CardService;
import com.crackerStudents.projectApp.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class PackController {

    private final PackService packService;
    private final CardService cardService;

    @Autowired
    public PackController(PackService packService, CardService cardService) {
        this.packService = packService;
        this.cardService = cardService;
    }

    @GetMapping("/packs")
    public String view(@AuthenticationPrincipal User user, Model model, @PageableDefault Pageable pageable) {
        //model.addAttribute("packs", packService.getUserPacks(user));
        Page<PackDTO> page = packService.getAllByUserPackDTO(pageable, user);
        model.addAttribute("page", page);
        model.addAttribute("url", "/packs");
        return "packs";
    }

    @GetMapping("/packs/{packId}")
    public String main(@PathVariable UUID packId, Model model, @AuthenticationPrincipal User user) {
        PackDTO packDTO = packService.getPackDTOByID(packId);
        System.out.println(packDTO);
        System.out.println(packDTO.getName());
        model.addAttribute("pack", packDTO);
        model.addAttribute("cards", packDTO.getCards());
        model.addAttribute("time", packDTO.getCreated().toString());
        return "pack";
    }

    @PostMapping("/packs/{packId}")
    public String addCard(Card card, Model model,
                          @PathVariable UUID packId, @AuthenticationPrincipal User user) {

        card.setAuthor(user);
        //[TODO]: Добавить проверку на юзера
        packService.addCardAndSave(card, packId);
        return "redirect:/packs/" + packId;
    }

    @GetMapping("/packs/{packId}/{idCard}")
    public String editCardShow(@PathVariable UUID packId, @PathVariable UUID idCard, Model model, @AuthenticationPrincipal User user) {
        //[TODO]: Add user check
        PackDTO packDTO = packService.getPackDTOByID(packId);
        Card card = packDTO.getCard(idCard);
        model.addAttribute("card", card);
        model.addAttribute("message", null);
        return "cardEdit";
    }

    @PostMapping("/packs/{name}/{id}")
    public String editCardDo(@PathVariable UUID name, @PathVariable UUID id, @RequestParam String answer, @RequestParam String question, @AuthenticationPrincipal User user, Model model) {
        //[TODO]: Add user check
        PackDTO packDTO = packService.getPackDTOByID(name);
        Card card = packDTO.getCard(id);
        if (!StringUtils.isEmpty(answer) && !StringUtils.isEmpty(question)) {
            cardService.updateCard(card, answer, question);
            return "redirect:/packs/" + name;
        } else {
            model.addAttribute("card", card);
            model.addAttribute("message", "Заполните оба поля");
            return "cardEdit";
        }
    }
}