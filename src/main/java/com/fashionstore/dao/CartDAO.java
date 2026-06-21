package com.fashionstore.dao;
import com.fashionstore.model.CartItem;
import java.util.List;

import com.fashionstore.model.Cart;

public interface CartDAO {

    boolean addToCart(Cart cart);

    List<Cart> getCartItems(int userId);

    boolean updateCartQuantity(
            int cartId,
            int quantity);

    boolean removeCartItem(
            int cartId);

    void clearCart(int userId);

    Cart getCartItem(
            int userId,
            int variantId);
    List<CartItem> getCartDetails(int userId);
    int getStockByVariantId(int variantId);
    Cart getCartById(int cartId);
}