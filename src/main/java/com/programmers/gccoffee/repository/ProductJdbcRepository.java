package com.programmers.gccoffee.repository;

import static com.programmers.gccoffee.repository.JdbcUtils.toLocalDateTime;
import static com.programmers.gccoffee.repository.JdbcUtils.toUUID;

import com.programmers.gccoffee.model.Category;
import com.programmers.gccoffee.model.Product;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
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

    public ProductJdbcRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Product insert(Product product) {
        String sql = "insert into products(product_id, product_name, category, price, description, created_at, updated_at) "
                + "values(UUID_TO_BIN(:productId), :productName, :category, :price, :description, :createdAt, :updatedAt)";
        int updateCounts = template.update(sql, toParamMap(product));

        if (updateCounts < 1) {
            throw new RuntimeException("Nothing was inserted.");
        }

        return product;
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

    private Map<String, Object> toParamMap(Product product) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("productId", product.getProductId().toString().getBytes());
        paramMap.put("productName", product.getProductName());
        paramMap.put("category", product.getCategory().toString());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("createdAt", product.getCreatedAt());
        paramMap.put("updatedAt", product.getUpdatedAt());

        return paramMap;
    }
}
