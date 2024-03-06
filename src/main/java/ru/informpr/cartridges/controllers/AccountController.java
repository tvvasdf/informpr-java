package ru.informpr.cartridges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.informpr.cartridges.lib.Cartridges;
import ru.informpr.cartridges.models.CartridgesElements;
import ru.informpr.cartridges.models.CartridgesSections;
import ru.informpr.cartridges.repo.CartridgesElementsRepository;
import ru.informpr.cartridges.repo.CartridgesSectionsRepository;

@Controller
public class AccountController {
    @Autowired
    private CartridgesElementsRepository cartridgesElementsRepository;
    @Autowired
    private CartridgesSectionsRepository cartridgesSectionsRepository;
    @Autowired
    private Cartridges cartridges;

    @GetMapping("/account")
    public String mainPage(Model view) {
        view.addAttribute("title", "Страница профиля");
        return "pages/account/main";
    }

    @GetMapping("/account/cartridges/section")
    public String createSection(Model view) {
        view.addAttribute("title", "Создание раздела");
        view.addAttribute("action", "create");
        return "pages/account/section";
    }

    @GetMapping("/account/cartridges/section/{id}")
    public String updateSection(Model view, @PathVariable Integer id) {
        if (cartridgesSectionsRepository.findById(id).isEmpty()) {
            return "redirect:/account/cartridges/section";
        }

        CartridgesSections item = cartridgesSectionsRepository.findById(id).get();

        view.addAttribute("title", "Редактирование раздела");
        view.addAttribute("action", "update");
        view.addAttribute("cartridge", item);
        return "pages/account/section";
    }

    @GetMapping("/account/cartridges/element")
    public String createElement(Model view) {
        view.addAttribute("title", "Создание элемента");
        view.addAttribute("action", "create");
        view.addAttribute("sections", cartridges.getSections());
        return "pages/account/element";
    }

    @GetMapping("/account/cartridges/element/{id}")
    public String updateElement(Model view, @PathVariable Integer id) {
        if (cartridgesElementsRepository.findById(id).isEmpty()) {
            return "redirect:/account/cartridges/element";
        }

        CartridgesElements item = cartridgesElementsRepository.findById(id).get();

        view.addAttribute("title", "Редактирование элемента");
        view.addAttribute("action", "update");
        view.addAttribute("cartridge", item);
        return "pages/account/element";
    }
}
