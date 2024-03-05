package ru.informpr.cartridges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.informpr.cartridges.models.CartridgesElements;
import ru.informpr.cartridges.repo.CartridgesElementsRepository;

import java.util.List;
import java.util.Objects;

@Controller
public class StaticPagesController {

    @Autowired
    private CartridgesElementsRepository cartridgesRepo;

    @GetMapping("/")
    public String mainPage(
        Model model,
       @RequestParam(required = false, defaultValue = "") String q,
       @RequestParam(required = false, defaultValue = "") String props
    ) {
        if (!q.isEmpty()) {
            List<CartridgesElements> cartridges;

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

        return "pages/static/main";
    }

    @GetMapping("/about-printers")
    public static String aboutPrinters(Model model) {
        model.addAttribute("title", "О принтерах");
        return "pages/static/about-printers";
    }

    @GetMapping("/about-cartridges")
    public static String aboutCartridges(Model model) {
        model.addAttribute("title", "О картриджах");
        return "pages/static/about-cartridges";
    }

    @GetMapping("/feedback")
    public static String feedback(Model model) {
        model.addAttribute("title", "Обратная связь");
        return "pages/static/feedback";
    }
}
