package com.example.rest.repository;

import com.example.rest.common.HotelType;
import com.example.rest.domain.Hotel;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
@Primary
@AllArgsConstructor
public class HotelRepositoryJDBC implements HotelRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SQL = "SELECT id, name, address, type FROM hotels";
    private static final String FIND_BY_ID_SQL = "SELECT id, name, address, type FROM hotels WHERE id = ?";
    private static final String INSERT_SQL = "INSERT INTO hotels (name, address, type) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE hotels SET name = ?, address = ?, type = ? WHERE id = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM hotels WHERE id = ?";

    private static final RowMapper<Hotel> ROW_MAPPER = (rs, rowNum) -> new Hotel(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("address"),
            HotelType.valueOf(rs.getString("type").toUpperCase())
    );

    @Override
    public Optional<Hotel> findById(Long id) {
        try {
            Hotel hotel = jdbcTemplate.queryForObject(FIND_BY_ID_SQL, ROW_MAPPER, id);
            return Optional.ofNullable(hotel);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Hotel hotel) {
        if (hotel.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, hotel.getName());
                ps.setString(2, hotel.getAddress());
                ps.setString(3, hotel.getType().name());
                return ps;
            }, keyHolder);

            Long generatedId = (Long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
            hotel.setId(generatedId);
        } else {
            jdbcTemplate.update(UPDATE_SQL, hotel.getName(), hotel.getAddress(), hotel.getType().name(), hotel.getId());
        }
    }

    @Override
    public List<Hotel> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, ROW_MAPPER);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_BY_ID_SQL, id);
    }
}
