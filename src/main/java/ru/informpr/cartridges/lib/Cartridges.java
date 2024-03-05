package ru.informpr.cartridges.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.informpr.cartridges.models.CartridgesElements;
import ru.informpr.cartridges.repo.CartridgesElementsRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class Cartridges {

    @Autowired
    private static CartridgesElementsRepository cartridgesRepo;

    public static Result create(
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

        return new Result(true, cartridgesRepo.save(item).getId().toString());
    }

    public static Result update(
            Integer id,
            Byte access,
            String prefix,
            String model,
            String section,
            String[] propertiesNames,
            String[] properties,
            String[] printers
    ) {


        if (cartridgesRepo.findById(id).isEmpty()) {
            return new Result(false, "Element with " + id + " not found");
        }

        CartridgesElements item = cartridgesRepo.findById(id).get();
        Map<String, String> props = new HashMap<>();

        //todo if (user.getAccess < access) return Access Denied

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
            cartridgesRepo.save(item);
        } catch (Exception e) {
            return new Result(false, e.toString());
        }

        return new Result(true);
    }

    public static Result delete(Integer id) {
        //todo if (user.getAccess < access) return Access Denied

        cartridgesRepo.deleteById(id);
        return new Result(true);
    }

}
