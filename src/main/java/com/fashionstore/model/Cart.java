package com.fashionstore.model;

public class Cart {

    @Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", variantId=" + variantId + ", quantity=" + quantity
				+ "]";
	}

	private int cartId;
    private int userId;
    private int variantId;
    private int quantity;

    public Cart() {
    }

    public Cart(int cartId, int userId,
                int variantId, int quantity) {

        this.cartId = cartId;
        this.userId = userId;
        this.variantId = variantId;
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}