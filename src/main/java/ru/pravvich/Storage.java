package ru.pravvich;

import java.util.List;

public interface Storage {
    Item save(Item item);
    List<Item> getAll();
}
