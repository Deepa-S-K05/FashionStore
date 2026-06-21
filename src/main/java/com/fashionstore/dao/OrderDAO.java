package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;

public interface OrderDAO {


int placeOrder(Order order);

List<Order> getOrdersByUser(int userId);

Order getOrderById(int orderId);
boolean addOrderItem(
        int orderId,
        int variantId,
        int quantity,
        double price);

boolean updateStock(
        int variantId,
        int quantity);
List<OrderItem> getOrderItemsByOrderId(
        int orderId);

}
