package ru.informpr.cartridges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
            @RequestParam String prefix,
            @RequestParam String model,
            @RequestParam String section,
            @RequestParam(value = "propertiesNames[]") String[] propertiesNames,
            @RequestParam(value = "properties[]") String[] properties,
            @RequestParam(value = "printers[]") String[] printers
    ) {

        Cartridges item = new Cartridges();
        Map<String, String> props = new HashMap<>();

        for (int i = 0; i < propertiesNames.length; i++) {
            if (propertiesNames[i].isEmpty()) {
                continue;
            }

            props.put(propertiesNames[i], properties[i]);
        }

        item.setModel(model);
        item.setPrefix(prefix);
        item.setSection(section);

        if (properties.length > 0) {
            item.setProperties(props);
        }

        if (printers.length > 0) {
            item.setPrinters(printers);
        }

        cartridgesRepo.save(item);
        return "{\"success\": true}";
    }
}
