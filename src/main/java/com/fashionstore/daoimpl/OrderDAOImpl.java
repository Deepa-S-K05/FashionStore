package com.fashionstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.model.Order;
import com.fashionstore.util.DBConnection;

import com.fashionstore.model.OrderItem;


public class OrderDAOImpl
implements OrderDAO {


@Override
public int placeOrder(
        Order order) {

    int orderId = 0;

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "INSERT INTO orders(" +
                "user_id," +
                "total_amount," +
                "order_status) " +
                "VALUES(?,?,?)";

        PreparedStatement ps =
                con.prepareStatement(
                        sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setInt(
                1,
                order.getUserId());

        ps.setDouble(
                2,
                order.getTotalAmount());

        ps.setString(
                3,
                order.getOrderStatus());

        int rows =
                ps.executeUpdate();

        if(rows > 0) {

            ResultSet rs =
                    ps.getGeneratedKeys();

            if(rs.next()) {

                orderId =
                        rs.getInt(1);
            }
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return orderId;
}

@Override
public List<Order> getOrdersByUser(
int userId) {


List<Order> orders =
        new ArrayList<>();

try {

    Connection con =
            DBConnection.getConnection();

    String sql =
            "SELECT * FROM orders " +
            "WHERE user_id=? " +
            "ORDER BY order_date DESC";

    PreparedStatement ps =
            con.prepareStatement(sql);

    ps.setInt(
            1,
            userId);

    ResultSet rs =
            ps.executeQuery();

    while(rs.next()) {

        Order order =
                new Order();

        order.setOrderId(
                rs.getInt(
                        "order_id"));

        order.setUserId(
                rs.getInt(
                        "user_id"));

        order.setTotalAmount(
                rs.getDouble(
                        "total_amount"));

        order.setOrderStatus(
                rs.getString(
                        "order_status"));

        order.setOrderDate(
                rs.getTimestamp(
                        "order_date"));

        orders.add(order);
    }

} catch(Exception e) {

    e.printStackTrace();
}

return orders;


}


@Override
public Order getOrderById(
int orderId) {

Order order = null;

try {

    Connection con =
            DBConnection.getConnection();

    String sql =
            "SELECT * FROM orders " +
            "WHERE order_id=?";

    PreparedStatement ps =
            con.prepareStatement(sql);

    ps.setInt(
            1,
            orderId);

    ResultSet rs =
            ps.executeQuery();

    if(rs.next()) {

        order =
                new Order();

        order.setOrderId(
                rs.getInt(
                        "order_id"));

        order.setUserId(
                rs.getInt(
                        "user_id"));

        order.setTotalAmount(
                rs.getDouble(
                        "total_amount"));

        order.setOrderStatus(
                rs.getString(
                        "order_status"));

        order.setOrderDate(
                rs.getTimestamp(
                        "order_date"));
    }

} catch(Exception e) {

    e.printStackTrace();
}

return order;


}

@Override
public boolean addOrderItem(
        int orderId,
        int variantId,
        int quantity,
        double price) {

    boolean status = false;

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "INSERT INTO order_items(" +
                "order_id," +
                "variant_id," +
                "quantity," +
                "price) " +
                "VALUES(?,?,?,?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(
                1,
                orderId);

        ps.setInt(
                2,
                variantId);

        ps.setInt(
                3,
                quantity);

        ps.setDouble(
                4,
                price);

        status =
                ps.executeUpdate() > 0;

    } catch(Exception e) {

        e.printStackTrace();
    }

    return status;
}
@Override
public boolean updateStock(
        int variantId,
        int quantity) {

    boolean status = false;

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "UPDATE product_variants " +
                "SET stock_quantity = " +
                "stock_quantity - ? " +
                "WHERE variant_id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(
                1,
                quantity);

        ps.setInt(
                2,
                variantId);

        status =
                ps.executeUpdate() > 0;

    } catch(Exception e) {

        e.printStackTrace();
    }

    return status;
}
@Override
public List<OrderItem> getOrderItemsByOrderId(
        int orderId) {

    List<OrderItem> items =
            new ArrayList<>();

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT " +
                "oi.order_item_id, " +
                "oi.order_id, " +
                "oi.variant_id, " +
                "oi.quantity, " +
                "oi.price, " +
                "p.product_name, " +
                "p.brand, " +
                "pv.size, " +
                "pi.image_url " +

                "FROM order_items oi " +

                "JOIN product_variants pv " +
                "ON oi.variant_id = pv.variant_id " +

                "JOIN products p " +
                "ON pv.product_id = p.product_id " +

                "LEFT JOIN product_images pi " +
                "ON pi.image_id = ( " +
                    "SELECT MIN(image_id) " +
                    "FROM product_images " +
                    "WHERE product_id = p.product_id " +
                ") " +

                "WHERE oi.order_id = ?";
        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, orderId);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()) {

            OrderItem item =
                    new OrderItem();

            item.setOrderItemId(
                    rs.getInt(
                            "order_item_id"));

            item.setOrderId(
                    rs.getInt(
                            "order_id"));

            item.setVariantId(
                    rs.getInt(
                            "variant_id"));

            item.setQuantity(
                    rs.getInt(
                            "quantity"));

            item.setPrice(
                    rs.getDouble(
                            "price"));
            item.setProductName(
                    rs.getString(
                            "product_name"));

            item.setBrand(
                    rs.getString(
                            "brand"));

            item.setSize(
                    rs.getString(
                            "size"));

            item.setImageUrl(
                    rs.getString(
                            "image_url"));

            items.add(item);
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return items;
}
}
