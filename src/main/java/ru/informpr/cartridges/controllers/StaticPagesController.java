package ru.informpr.cartridges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.informpr.cartridges.models.CartridgesElements;
import ru.informpr.cartridges.models.CartridgesSections;
import ru.informpr.cartridges.repo.CartridgesElementsRepository;
import ru.informpr.cartridges.repo.CartridgesSectionsRepository;

import java.util.List;
import java.util.Objects;

@Controller
public class StaticPagesController {

    @Autowired
    private CartridgesElementsRepository cartridgesElementsRepository;
    @Autowired
    private CartridgesSectionsRepository cartridgesSectionsRepository;

    @GetMapping("/")
    public String mainPage(
        Model model,
       @RequestParam(required = false, defaultValue = "") String q,
       @RequestParam(required = false, defaultValue = "") String props
    ) {
        if (!q.isEmpty()) {
            List<CartridgesElements> cartridges;

            if (Objects.equals(props, "Y")) {
                cartridges = cartridgesElementsRepository.findByModelOrPrefixOrPrintersOrPropertiesContaining(q, q, q, q);
            } else {
                cartridges = cartridgesElementsRepository.findByModelOrPrefixOrPrintersContaining(q, q, q);
            }

            model.addAttribute("title", "Поиск - " + q);
            model.addAttribute("query", q);
            model.addAttribute("props", props);
            model.addAttribute("cartridges", cartridges);
        } else {
            model.addAttribute("title", "Главная");
        }

        model.addAttribute("side_sections", cartridgesSectionsRepository.findAll());
        return "pages/static/main";
    }

    @GetMapping("/about-printers")
    public String aboutPrinters(Model model) {
        model.addAttribute("title", "О принтерах");
        model.addAttribute("side_sections", cartridgesSectionsRepository.findAll());
        return "pages/static/about-printers";
    }

    @GetMapping("/about-cartridges")
    public String aboutCartridges(Model model) {
        model.addAttribute("title", "О картриджах");
        model.addAttribute("side_sections", cartridgesSectionsRepository.findAll());
        return "pages/static/about-cartridges";
    }

    @GetMapping("/feedback")
    public String feedback(Model model) {
        model.addAttribute("title", "Обратная связь");
        model.addAttribute("side_sections", cartridgesSectionsRepository.findAll());
        return "pages/static/feedback";
    }
}
