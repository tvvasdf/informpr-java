package ru.informpr.cartridges.repo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.informpr.cartridges.models.Cartridges;

import java.util.List;

public interface CartridgesRepository extends CrudRepository<Cartridges, Integer> {
    List<Cartridges> findByModelOrPrefixOrPrintersContaining(String model, String prefix, String printers);
    List<Cartridges> findByModelOrPrefixOrPrintersOrPropertiesContaining(String model, String prefix, String printers, String properties);

}
