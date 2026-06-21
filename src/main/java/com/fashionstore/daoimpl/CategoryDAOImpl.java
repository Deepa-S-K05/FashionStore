package com.fashionstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.model.Category;
import com.fashionstore.util.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM categories";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setCategoryImage(rs.getString("category_image"));

                categories.add(category);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category getCategoryById(int categoryId) {

        Category category = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM categories WHERE category_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setCategoryImage(rs.getString("category_image"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return category;
    }
}