package ru.informpr.cartridges.repo;
import org.springframework.data.repository.CrudRepository;
import ru.informpr.cartridges.models.Cartridges;

public interface CartridgesRepository extends CrudRepository<Cartridges, Integer> {
}
