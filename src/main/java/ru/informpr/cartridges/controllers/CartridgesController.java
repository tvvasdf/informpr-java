package ru.informpr.cartridges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.informpr.cartridges.lib.Cartridges;
import ru.informpr.cartridges.lib.Result;
import ru.informpr.cartridges.models.CartridgesElements;
import ru.informpr.cartridges.repo.CartridgesElementsRepository;

import java.util.List;

@Controller
public class CartridgesController {

    @Autowired
    private CartridgesElementsRepository cartridgesRepo;

    @GetMapping("/cartridges")
    public String redirect() {
        return "redirect:/";
    }

    @GetMapping("/cartridges/{section}")
    public String readSection(Model view, @PathVariable String section) {
        List<CartridgesElements> items = cartridgesRepo.findBySection(section);

        if (items.isEmpty()) {
            return "redirect:/";
        }

        view.addAttribute("cartridges", items);
        return "pages/cartridges/list";
    }

    @GetMapping("/cartridges/{section}/{code}")
    public String readElement(Model view, @PathVariable String section, @PathVariable String code) {
        CartridgesElements item = cartridgesRepo.findBySectionAndCode(section, code);

        if (item.getId() == null) {
            return "redirect:/";
        }

        view.addAttribute("cartridges", item);
        return "pages/cartridges/element";
    }

    @PostMapping(value = "/cartridges/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Result createElement(
            @RequestParam(defaultValue = "1", required = false) Byte access,
            @RequestParam(defaultValue = "", required = false) String prefix,
            @RequestParam String model,
            @RequestParam String section,
            @RequestParam(value = "propertiesNames[]", required = false) String[] propertiesNames,
            @RequestParam(value = "properties[]", required = false) String[] properties,
            @RequestParam(value = "printers[]", required = false) String[] printers
    ) {

        return Cartridges.create(access, prefix, model, section, propertiesNames, properties, printers);
    }

    @PostMapping(value = "/cartridges/{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Result updateElement(
            @PathVariable @RequestParam Integer id,
            @RequestParam(defaultValue = "1", required = false) Byte access,
            @RequestParam(defaultValue = "", required = false) String prefix,
            @RequestParam String model,
            @RequestParam String section,
            @RequestParam(value = "propertiesNames[]", required = false) String[] propertiesNames,
            @RequestParam(value = "properties[]", required = false) String[] properties,
            @RequestParam(value = "printers[]", required = false) String[] printers
    ) {

        return Cartridges.update(id, access, prefix, model, section, propertiesNames, properties, printers);
    }

    @PostMapping(value = "/cartridges/{id}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Result deleteElement(@PathVariable @RequestParam Integer id) {
        return Cartridges.delete(id);
    }
}
