package ru.example.tacocloud.data;

import ru.example.tacocloud.domain.Taco;

public interface TacoRepository {

    Taco save(Taco design);
}
