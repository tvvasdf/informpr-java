package ru.informpr.cartridges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.informpr.cartridges.models.Cartridges;
import ru.informpr.cartridges.repo.CartridgesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class PagesController {

    @Autowired
    private CartridgesRepository cartridgesRepo;

    @GetMapping("/")
    public String mainPage(
        Model model,
       @RequestParam(required = false, defaultValue = "") String q,
       @RequestParam(required = false, defaultValue = "") String props
    ) {
        if (!q.isEmpty()) {
            List<Cartridges> cartridges;

            if (Objects.equals(props, "Y")) {
                cartridges = cartridgesRepo.findByModelOrPrefixOrPrintersOrPropertiesContaining(q, q, q, q);
            } else {
                cartridges = cartridgesRepo.findByModelOrPrefixOrPrintersContaining(q, q, q);
            }

            model.addAttribute("title", "Поиск - " + q);
            model.addAttribute("query", q);
            model.addAttribute("props", props);
            model.addAttribute("cartridges", cartridges);
        } else {
            model.addAttribute("title", "Главная");
        }

        return "main";
    }

    @GetMapping({"/about-printers", "/about-printers/"})
    public static String aboutPrinters(Model model) {
        model.addAttribute("title", "О принтерах");
        return "about-printers";
    }

    @GetMapping({"/about-cartridges", "/about-cartridges/"})
    public static String aboutCartridges(Model model) {
        model.addAttribute("title", "О картриджах");
        return "about-cartridges";
    }

    @GetMapping({"/feedback", "/feedback/"})
    public static String feedback(Model model) {
        model.addAttribute("title", "Обратная связь");
        return "feedback";
    }
}
