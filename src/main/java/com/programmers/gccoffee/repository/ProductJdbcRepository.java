package com.programmers.gccoffee.repository;

import static com.programmers.gccoffee.repository.JdbcUtils.toLocalDateTime;
import static com.programmers.gccoffee.repository.JdbcUtils.toUUID;

import com.programmers.gccoffee.model.Category;
import com.programmers.gccoffee.model.Product;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ProductJdbcRepository implements ProductRepository {

    private static final RowMapper<Product> productRowMapper = ((rs, rowNum) -> {
        UUID productId = toUUID(rs.getBytes("product_id"));
        String productName = rs.getString("product_name");
        Category category = Category.valueOf(rs.getString("category"));
        long price = rs.getLong("price");
        String description = rs.getString("description");
        LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp("updated_at"));

        return new Product(productId, productName, category, price, description, createdAt, updatedAt);
    });

    private final NamedParameterJdbcTemplate template;

    public ProductJdbcRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Product insert(Product product) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from products";

        return template.query(sql, productRowMapper);
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> findByName(String productName) {
        return Optional.empty();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
