package com.fashionstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductImage;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {


@Override
public List<Product> getAllProducts() {

    List<Product> products = new ArrayList<>();

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM products";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            Product product = new Product();

            product.setProductId(rs.getInt("product_id"));
            product.setCategoryId(rs.getInt("category_id"));
            product.setProductName(rs.getString("product_name"));
            product.setDescription(rs.getString("description"));
            product.setBrand(rs.getString("brand"));
            product.setPrice(rs.getDouble("price"));

            products.add(product);
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return products;
}

@Override
public Product getProductById(int productId) {

    Product product = null;

    try {

        Connection con = DBConnection.getConnection();

        String sql =
                "SELECT * FROM products WHERE product_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, productId);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            product = new Product();

            product.setProductId(rs.getInt("product_id"));
            product.setCategoryId(rs.getInt("category_id"));
            product.setProductName(rs.getString("product_name"));
            product.setDescription(rs.getString("description"));
            product.setBrand(rs.getString("brand"));
            product.setPrice(rs.getDouble("price"));
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return product;
}

@Override
public List<Product> getProductsByCategory(int categoryId) {

    List<Product> products = new ArrayList<>();

    try {

        Connection con = DBConnection.getConnection();

        String sql =
                "SELECT * FROM products WHERE category_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, categoryId);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            Product product = new Product();

            product.setProductId(rs.getInt("product_id"));
            product.setCategoryId(rs.getInt("category_id"));
            product.setProductName(rs.getString("product_name"));
            product.setDescription(rs.getString("description"));
            product.setBrand(rs.getString("brand"));
            product.setPrice(rs.getDouble("price"));

            products.add(product);
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return products;
}

@Override
public List<ProductImage> getProductImages(int productId) {

    List<ProductImage> images = new ArrayList<>();

    try {

        Connection con = DBConnection.getConnection();

        String sql =
                "SELECT * FROM product_images WHERE product_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, productId);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            ProductImage image = new ProductImage();

            image.setImageId(rs.getInt("image_id"));
            image.setProductId(rs.getInt("product_id"));
            image.setImageUrl(rs.getString("image_url"));

            images.add(image);
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return images;
}

@Override
public List<ProductVariant> getProductVariants(int productId) {

    List<ProductVariant> variants = new ArrayList<>();

    try {

        Connection con = DBConnection.getConnection();

        String sql =
                "SELECT * FROM product_variants WHERE product_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, productId);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            ProductVariant variant = new ProductVariant();

            variant.setVariantId(rs.getInt("variant_id"));
            variant.setProductId(rs.getInt("product_id"));
            variant.setSize(rs.getString("size"));
            variant.setStockQuantity(
                    rs.getInt("stock_quantity")
            );

            variants.add(variant);
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return variants;
}
@Override
public List<Product> getFeaturedProducts(int limit) {

    List<Product> products =
            new ArrayList<>();

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT * FROM products LIMIT ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, limit);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()) {

            Product product =
                    new Product();

            product.setProductId(
                    rs.getInt("product_id"));

            product.setCategoryId(
                    rs.getInt("category_id"));

            product.setProductName(
                    rs.getString("product_name"));

            product.setDescription(
                    rs.getString("description"));

            product.setBrand(
                    rs.getString("brand"));

            product.setPrice(
                    rs.getDouble("price"));

            products.add(product);
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return products;
}
@Override
public String getPrimaryImage(int productId) {

    String imageUrl = null;

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT image_url "
              + "FROM product_images "
              + "WHERE product_id = ? "
              + "LIMIT 1";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, productId);

        ResultSet rs =
                ps.executeQuery();

        if(rs.next()) {

            imageUrl =
                    rs.getString("image_url");
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return imageUrl;
}
@Override
public List<Product> getAllProductsWithImages() {

    List<Product> products = new ArrayList<>();

    try {

        Connection con = DBConnection.getConnection();

        String sql =
        	    "SELECT p.product_id, " +
        	    "p.category_id, " +
        	    "p.product_name, " +
        	    "p.description, " +
        	    "p.brand, " +
        	    "p.price, " +
        	    "MIN(pi.image_url) AS image_url " +
        	    "FROM products p " +
        	    "INNER JOIN product_images pi " +
        	    "ON p.product_id = pi.product_id " +
        	    "GROUP BY p.product_id, p.category_id, p.product_name, p.description, p.brand, p.price";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()) {

            Product product = new Product();

            product.setProductId(
                    rs.getInt("product_id"));

            product.setCategoryId(
                    rs.getInt("category_id"));

            product.setProductName(
                    rs.getString("product_name"));

            product.setDescription(
                    rs.getString("description"));

            product.setBrand(
                    rs.getString("brand"));

            product.setPrice(
                    rs.getDouble("price"));

            product.setImageUrl(
                    rs.getString("image_url"));

            products.add(product);
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return products;
}
@Override
public List<Product> getProductsByCategoryWithImages(
        int categoryId) {

    List<Product> products =
            new ArrayList<>();

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT p.product_id, " +
                "p.category_id, " +
                "p.product_name, " +
                "p.description, " +
                "p.brand, " +
                "p.price, " +
                "MIN(pi.image_url) AS image_url " +
                "FROM products p " +
                "INNER JOIN product_images pi " +
                "ON p.product_id = pi.product_id " +
                "WHERE p.category_id = ? " +
                "GROUP BY p.product_id, " +
                "p.category_id, " +
                "p.product_name, " +
                "p.description, " +
                "p.brand, " +
                "p.price";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, categoryId);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()) {

            Product product =
                    new Product();

            product.setProductId(
                    rs.getInt("product_id"));

            product.setCategoryId(
                    rs.getInt("category_id"));

            product.setProductName(
                    rs.getString("product_name"));

            product.setDescription(
                    rs.getString("description"));

            product.setBrand(
                    rs.getString("brand"));

            product.setPrice(
                    rs.getDouble("price"));

            product.setImageUrl(
                    rs.getString("image_url"));

            products.add(product);
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return products;
}
@Override
public List<Product> searchProducts(
        String keyword) {

    List<Product> products =
            new ArrayList<>();

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT p.product_id, " +
                "p.category_id, " +
                "p.product_name, " +
                "p.description, " +
                "p.brand, " +
                "p.price, " +
                "MIN(pi.image_url) AS image_url " +
                "FROM products p " +
                "INNER JOIN product_images pi " +
                "ON p.product_id = pi.product_id " +
                "WHERE p.product_name LIKE ? " +
                "OR p.brand LIKE ? " +
                "GROUP BY p.product_id, " +
                "p.category_id, " +
                "p.product_name, " +
                "p.description, " +
                "p.brand, " +
                "p.price";

        PreparedStatement ps =
                con.prepareStatement(sql);

        String search =
                "%" + keyword + "%";

        ps.setString(1, search);
        ps.setString(2, search);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()) {

            Product product =
                    new Product();

            product.setProductId(
                    rs.getInt("product_id"));

            product.setCategoryId(
                    rs.getInt("category_id"));

            product.setProductName(
                    rs.getString("product_name"));

            product.setDescription(
                    rs.getString("description"));

            product.setBrand(
                    rs.getString("brand"));

            product.setPrice(
                    rs.getDouble("price"));

            product.setImageUrl(
                    rs.getString("image_url"));

            products.add(product);
        }

    } catch(Exception e) {

        e.printStackTrace();
    }

    return products;
}
}
