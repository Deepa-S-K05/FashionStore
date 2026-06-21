package com.fashionstore.util;

import java.util.List;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.daoimpl.CategoryDAOImpl;
import com.fashionstore.daoimpl.ProductDAOImpl;
import com.fashionstore.model.Category;

public class TestCategoryDAO {

    public static void main(String[] args) {

        CategoryDAO dao = new CategoryDAOImpl();

        List<Category> categories =
                dao.getAllCategories();

        for(Category c : categories) {

            System.out.println(
                    c.getCategoryId() + " "
                  + c.getCategoryName() + " "
                  + c.getCategoryImage()
            );
        }
        ProductDAO da = new ProductDAOImpl();

        System.out.println(
                da.getProductById(1)
        );
    }
}