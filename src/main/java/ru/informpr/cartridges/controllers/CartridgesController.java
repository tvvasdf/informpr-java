package ru.informpr.cartridges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.informpr.cartridges.helpers.MainHelper;
import ru.informpr.cartridges.models.Cartridges;
import ru.informpr.cartridges.repo.CartridgesRepository;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CartridgesController {
    @Autowired
    private CartridgesRepository cartridgesRepo;

    @PostMapping("/api/cartridges/add")
    public @ResponseBody String addElement(
        Model view,
        @RequestParam String prefix,
        @RequestParam String model,
        @RequestParam String section,
        @RequestParam(value = "propertiesNames[]") String[] propertiesNames,
        @RequestParam(value = "properties[]") String[] properties,
        @RequestParam(value = "printers[]") String[] printers
    ) {

        Cartridges item = new Cartridges();

        item.setModel(model);
        item.setPrefix(prefix);
        item.setSection(section);

        if (properties.length > 0) {
            item.setProperties(MainHelper.arraysToMap(propertiesNames, properties));
        }

        if (printers.length > 0) {
            item.setPrinters(printers);
        }

        item = cartridgesRepo.save(item);

        view.addAttribute("title", "Добавить новую запись");
        view.addAttribute("id", item.getId());

        return "id: " + item.getId();
    }
}
