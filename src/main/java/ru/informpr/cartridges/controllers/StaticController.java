package ru.informpr.cartridges.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
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
