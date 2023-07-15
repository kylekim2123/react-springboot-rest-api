package com.programmers.gccoffee.repository;

import com.programmers.gccoffee.model.Order;
import com.programmers.gccoffee.model.OrderItem;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate template;

    public OrderJdbcRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public Order insert(Order order) {
        String orderSql = "insert into orders(order_id, email, address, postcode, order_status, created_at, updated_at)"
                + "values(:orderId, :email, :address, :postcode, :orderStatus, :createdAt, :updatedAt)";

        String orderItemSql = "insert into order_items(order_id, product_id, category, price, quantity, created_at, updated_at)"
                + "values(:orderId, :productId, :category, :price, :quantity, :createdAt, :updatedAt)";

        template.update(orderSql, toOrderParamMap(order));

        order.getOrderItems()
                .forEach(item -> template.update(orderItemSql, toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(), item)));

        return order;
    }

    private Map<String, Object> toOrderParamMap(Order order) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("orderId", order.getOrderId());
        paramMap.put("email", order.getEmail().getAddress());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());

        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt, LocalDateTime updatedAt, OrderItem orderItem) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("orderId", orderId);
        paramMap.put("productId", orderItem.getProductId());
        paramMap.put("category", orderItem.getCategory().toString());
        paramMap.put("price", orderItem.getPrice());
        paramMap.put("quantity", orderItem.getQuantity());
        paramMap.put("createdAt", createdAt);
        paramMap.put("updatedAt", updatedAt);

        return paramMap;
    }
}
