package ru.pravvich;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemStorageTest {

    @Test
    public void whenItemSaveThenItemContainInDB() {
        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("jdbc-context.xml");

        final ItemStorage storage = context.getBean(ItemStorage.class);

        final Item item = new Item();
        item.setName("test");
        item.setDescription("test");

        final Item result = storage.save(item);
        assertThat(storage.getAll().contains(result), is(true));
    }
}