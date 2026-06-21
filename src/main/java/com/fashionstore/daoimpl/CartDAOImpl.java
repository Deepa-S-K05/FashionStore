package com.fashionstore.daoimpl;
import com.fashionstore.model.CartItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.Cart;
import com.fashionstore.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    @Override
    public boolean addToCart(Cart cart) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO cart(" +
                    "user_id, variant_id, quantity) " +
                    "VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    cart.getUserId());

            ps.setInt(
                    2,
                    cart.getVariantId());

            ps.setInt(
                    3,
                    cart.getQuantity());

            status =
                    ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<Cart> getCartItems(
            int userId) {

        List<Cart> cartItems =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM cart " +
                    "WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Cart cart =
                        new Cart();

                cart.setCartId(
                        rs.getInt(
                                "cart_id"));

                cart.setUserId(
                        rs.getInt(
                                "user_id"));

                cart.setVariantId(
                        rs.getInt(
                                "variant_id"));

                cart.setQuantity(
                        rs.getInt(
                                "quantity"));

                cartItems.add(cart);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return cartItems;
    }

    @Override
    public boolean updateCartQuantity(
            int cartId,
            int quantity) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE cart " +
                    "SET quantity=? " +
                    "WHERE cart_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, quantity);

            ps.setInt(2, cartId);

            status =
                    ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean removeCartItem(
            int cartId) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM cart " +
                    "WHERE cart_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, cartId);

            status =
                    ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public void clearCart(
            int userId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM cart " +
                    "WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public Cart getCartItem(
            int userId,
            int variantId) {

        Cart cart = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM cart " +
                    "WHERE user_id=? " +
                    "AND variant_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ps.setInt(2, variantId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                cart = new Cart();

                cart.setCartId(
                        rs.getInt(
                                "cart_id"));

                cart.setUserId(
                        rs.getInt(
                                "user_id"));

                cart.setVariantId(
                        rs.getInt(
                                "variant_id"));

                cart.setQuantity(
                        rs.getInt(
                                "quantity"));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return cart;
    }
    @Override
    public List<CartItem> getCartDetails(int userId) {

        List<CartItem> items =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT " +
                    "c.cart_id, " +
                    "c.quantity, " +
                    "pv.variant_id, " +
                    "pv.size, " +
                    "p.product_id, " +
                    "p.product_name, " +
                    "p.brand, " +
                    "p.price, " +
                    "MIN(pi.image_url) AS image_url " +
                    "FROM cart c " +
                    "INNER JOIN product_variants pv " +
                    "ON c.variant_id = pv.variant_id " +
                    "INNER JOIN products p " +
                    "ON pv.product_id = p.product_id " +
                    "LEFT JOIN product_images pi " +
                    "ON p.product_id = pi.product_id " +
                    "WHERE c.user_id=? " +
                    "GROUP BY c.cart_id";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                CartItem item =
                        new CartItem();

                item.setCartId(
                        rs.getInt("cart_id"));

                item.setVariantId(
                        rs.getInt("variant_id"));

                item.setProductId(
                        rs.getInt("product_id"));

                item.setProductName(
                        rs.getString("product_name"));

                item.setBrand(
                        rs.getString("brand"));

                item.setPrice(
                        rs.getDouble("price"));

                item.setImageUrl(
                        rs.getString("image_url"));

                item.setSize(
                        rs.getString("size"));

                item.setQuantity(
                        rs.getInt("quantity"));

                items.add(item);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return items;
    }
    @Override
    public int getStockByVariantId(int variantId) {

        int stock = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT stock_quantity " +
                    "FROM product_variants " +
                    "WHERE variant_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, variantId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                stock =
                    rs.getInt("stock_quantity");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return stock;
    }
    @Override
    public Cart getCartById(int cartId) {

        Cart cart = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM cart " +
                    "WHERE cart_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, cartId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                cart = new Cart();

                cart.setCartId(
                        rs.getInt("cart_id"));

                cart.setUserId(
                        rs.getInt("user_id"));

                cart.setVariantId(
                        rs.getInt("variant_id"));

                cart.setQuantity(
                        rs.getInt("quantity"));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return cart;
    }
}