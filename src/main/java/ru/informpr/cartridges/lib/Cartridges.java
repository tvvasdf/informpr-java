package ru.informpr.cartridges.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.informpr.cartridges.models.CartridgesElements;
import ru.informpr.cartridges.models.CartridgesSections;
import ru.informpr.cartridges.repo.CartridgesElementsRepository;
import ru.informpr.cartridges.repo.CartridgesSectionsRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class Cartridges {

    @Autowired
    private CartridgesElementsRepository cartridgesElementsRepository;
    @Autowired
    private CartridgesSectionsRepository cartridgesSectionsRepository;

    public Map<String, Object> create(
            Byte access,
            String prefix,
            String model,
            String section,
            String[] propertiesNames,
            String[] properties,
            String[] printers
    ) {
        CartridgesElements item = new CartridgesElements();
        Map<String, String> props = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        item.setAccess(access);
        item.setModel(model);
        item.setSection(section);

        if (propertiesNames.length > 0) {
            for (int i = 0; i < propertiesNames.length; i++) {
                if (propertiesNames[i].isEmpty()) {
                    continue;
                }

                props.put(propertiesNames[i], properties[i]);
            }
        }

        if (!prefix.isEmpty()) {
            item.setPrefix(prefix);
        }

        if (propertiesNames.length > 0) {
            item.setProperties(props);
        }

        if (printers.length > 0) {
            item.setPrinters(printers);
        }

        try {
            item = cartridgesElementsRepository.save(item);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return result;
        }

        result.put("success", true);
        result.put("message", item.getId());
        return result;
    }

    public Map<String, Object> update(
            Integer id,
            Byte access,
            String prefix,
            String model,
            String section,
            String[] propertiesNames,
            String[] properties,
            String[] printers
    ) {

        Map<String, Object> result = new HashMap<>();

        if (cartridgesElementsRepository.findById(id).isEmpty()) {
            result.put("success", false);
            result.put("message", "Element with " + id + " not found");
            return result;
        }

        CartridgesElements item = cartridgesElementsRepository.findById(id).get();
        Map<String, String> props = new HashMap<>();

        //todo if (user.getAccess < access) return Access Denied
        //User.checkAccess(access)

        item.setAccess(access);
        item.setModel(model);
        item.setSection(section);

        if (propertiesNames.length > 0) {
            for (int i = 0; i < propertiesNames.length; i++) {
                if (propertiesNames[i].isEmpty()) {
                    continue;
                }

                props.put(propertiesNames[i], properties[i]);
            }
        }

        if (!prefix.isEmpty()) {
            item.setPrefix(prefix);
        }

        if (propertiesNames.length > 0) {
            item.setProperties(props);
        }

        if (printers.length > 0) {
            item.setPrinters(printers);
        }

        try {
            cartridgesElementsRepository.save(item);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return result;
        }

        result.put("success", true);
        return result;
    }

    public Map<String, Object> delete(Integer id) {
        //todo if (user.getAccess < access) return Access Denied
        //User.checkAccess(access)
        Map<String, Object> result = new HashMap<>();

        try {
            cartridgesElementsRepository.deleteById(id);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return result;
        }

        result.put("success", true);
        return result;
    }

    public Map<String, Object> createSection(
            Byte access,
            String name,
            String code,
            String category
    ) {
        CartridgesSections item = new CartridgesSections();
        Map<String, Object> result = new HashMap<>();

        item.setAccess(access);
        item.setName(name);
        item.setCode(code);
        item.setCategory(category);

        try {
            item = cartridgesSectionsRepository.save(item);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return result;
        }

        result.put("success", true);
        result.put("message", item.getId());
        return result;
    }

    public Map<String, Object> updateSection(
            Integer id,
            Byte access,
            String name,
            String code,
            String category
    ) {
        Map<String, Object> result = new HashMap<>();

        if (cartridgesSectionsRepository.findById(id).isEmpty()) {
            result.put("success", false);
            result.put("message", "Section with " + id + " not found");
            return result;
        }

        CartridgesSections item = cartridgesSectionsRepository.findById(id).get();

        item.setAccess(access);
        item.setName(name);
        item.setCode(code);
        item.setCategory(category);

        try {
            cartridgesSectionsRepository.save(item);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return result;
        }

        result.put("success", true);
        return result;
    }

    public Map<String, Object> deleteSection(Integer id) {
        Map<String, Object> result = new HashMap<>();

        try {
            cartridgesSectionsRepository.deleteById(id);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            return result;
        }

        result.put("success", true);
        return result;
    }

    public Map<String, ArrayList<CartridgesSections>> getSections() {
        Map<String, ArrayList<CartridgesSections>> result = new HashMap<>();
        Iterable<CartridgesSections> sections = cartridgesSectionsRepository.findAll();

        for (CartridgesSections section : sections) {
            String key = section.getCategory();
            ArrayList<CartridgesSections> value = new ArrayList<>();

            if (result.get(key) != null) {
                value = result.get(key);
            }

            value.add(section);
            result.put(key, value);
        }

        return result;
    }
}
