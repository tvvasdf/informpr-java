package ru.informpr.cartridges.repo;
import org.springframework.data.repository.CrudRepository;
import ru.informpr.cartridges.models.CartridgesSections;

import java.util.List;

public interface CartridgesSectionsRepository extends CrudRepository<CartridgesSections, Integer> {
    CartridgesSections findByCode(String code);
}
