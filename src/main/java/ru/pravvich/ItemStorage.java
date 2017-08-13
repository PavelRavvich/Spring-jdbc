package ru.pravvich;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : Pavel Ravvich.
 * Created : 13.08.17.
 */
@Component
public class ItemStorage implements Storage {

    /**
     * Template for JDBC.
     */
    private final JdbcTemplate template;

    @Autowired
    public ItemStorage(final JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public Item save(final Item item) {

        final String INSERT_SQL = "INSERT INTO items (id, name, description) VALUES (DEFAULT, (?), (?))";

        final KeyHolder keyHolder = new GeneratedKeyHolder();

        this.template.update(

                new PreparedStatementCreator() {

                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                        final PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                        ps.setString(1, item.getName());
                        ps.setString(2, item.getDescription());
                        return ps;
                    }
                },

                keyHolder);

        item.setId(keyHolder.getKey().intValue());

        return item;
    }

    @Override
    public List<Item> getAll() {
        return this.template.query("SELECT * FROM items", new BeanPropertyRowMapper<>(Item.class));
    }
}
