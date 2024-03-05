package ru.informpr.cartridges.repo;
import org.springframework.data.repository.CrudRepository;
import ru.informpr.cartridges.models.CartridgesElements;

import java.util.List;

public interface CartridgesElementsRepository extends CrudRepository<CartridgesElements, Integer> {
    List<CartridgesElements> findByModelOrPrefixOrPrintersContaining(String model, String prefix, String printers);
    List<CartridgesElements> findByModelOrPrefixOrPrintersOrPropertiesContaining(String model, String prefix, String printers, String properties);
    List<CartridgesElements> findBySection(String section);
    CartridgesElements findBySectionAndCode(String section, String code);

}
