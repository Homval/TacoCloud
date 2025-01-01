package ru.example.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.example.tacocloud.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
