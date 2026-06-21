package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.Product;
import com.fashionstore.model.ProductImage;
import com.fashionstore.model.ProductVariant;

public interface ProductDAO {

    List<Product> getAllProducts();

    Product getProductById(int productId);

    List<Product> getProductsByCategory(int categoryId);

    List<ProductImage> getProductImages(int productId);

    List<ProductVariant> getProductVariants(int productId);
    List<Product> getFeaturedProducts(int limit);
    String getPrimaryImage(int productId);
    List<Product> getAllProductsWithImages();

    List<Product> getProductsByCategoryWithImages(int categoryId);
    List<Product> searchProducts(String keyword);
}