package ru.informpr.cartridges.controllers;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.informpr.cartridges.lib.Cartridges;
import ru.informpr.cartridges.models.CartridgesElements;
import ru.informpr.cartridges.repo.CartridgesElementsRepository;
import ru.informpr.cartridges.repo.CartridgesSectionsRepository;

import java.util.List;

@Controller
public class CartridgesController {

    @Autowired
    private CartridgesElementsRepository cartridgesElementsRepository;
    @Autowired
    private CartridgesSectionsRepository cartridgesSectionsRepository;
    @Autowired
    private Cartridges cartridges;

    @GetMapping("/cartridges")
    public String redirect() {
        return "redirect:/";
    }

    @GetMapping("/cartridges/{section}")
    public String readSection(Model view, @PathVariable String section) {
        List<CartridgesElements> items = cartridgesElementsRepository.findBySection(section);
        String sectionName = cartridgesSectionsRepository.findByCode(section).getName();

        if (items.isEmpty()) {
            //todo 404 or Sections is empty
            return "redirect:/";
        }

        view.addAttribute("title", sectionName);
        view.addAttribute("cartridges", items);
        return "pages/cartridges/list";
    }

    @GetMapping("/cartridges/{section}/{code}")
    public String readElement(Model view, @PathVariable String section, @PathVariable String code) {
        CartridgesElements item = cartridgesElementsRepository.findBySectionAndCode(section, code);

        if (item.getId() == null) {
            //todo 404
            return "redirect:/";
        }

        view.addAttribute("cartridge", item);
        return "pages/cartridges/element";
    }

    @PostMapping(value = "/cartridges/section/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createSection(
            @RequestParam(defaultValue = "1", required = false) Byte access,
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam String code,
            @RequestParam String category
    ) {

        JSONObject result = new JSONObject(cartridges.createSection(access, name, code, category));
        return result.toString();
    }

    @PostMapping(value = "/cartridges/section/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateSection(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "1", required = false) Byte access,
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam String code,
            @RequestParam String category
    ) {
        JSONObject result = new JSONObject(cartridges.updateSection(id, access, name, code, category));
        return result.toString();
    }

    @PostMapping(value = "/cartridges/section/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deleteSection(@PathVariable Integer id) {
        JSONObject result = new JSONObject(cartridges.deleteSection(id));
        return result.toString();
    }

    @PostMapping(value = "/cartridges/element/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createElement(
            @RequestParam(defaultValue = "1", required = false) Byte access,
            @RequestParam(defaultValue = "", required = false) String prefix,
            @RequestParam String model,
            @RequestParam String section,
            @RequestParam(value = "propertiesNames[]", required = false) String[] propertiesNames,
            @RequestParam(value = "properties[]", required = false) String[] properties,
            @RequestParam(value = "printers[]", required = false) String[] printers
    ) {
        JSONObject result = new JSONObject(
                cartridges.create(access, prefix, model, section, propertiesNames, properties, printers)
        );
        return result.toString();
    }

    @PostMapping(value = "/cartridges/element/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateElement(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "1", required = false) Byte access,
            @RequestParam(defaultValue = "", required = false) String prefix,
            @RequestParam String model,
            @RequestParam String section,
            @RequestParam(value = "propertiesNames[]", required = false) String[] propertiesNames,
            @RequestParam(value = "properties[]", required = false) String[] properties,
            @RequestParam(value = "printers[]", required = false) String[] printers
    ) {
        JSONObject result = new JSONObject(
                cartridges.update(id, access, prefix, model, section, propertiesNames, properties, printers)
        );
        return result.toString();
    }

    @PostMapping(value = "/cartridges/element/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deleteElement(@PathVariable Integer id) {
        JSONObject result = new JSONObject(cartridges.delete(id));
        return result.toString();
    }
}
