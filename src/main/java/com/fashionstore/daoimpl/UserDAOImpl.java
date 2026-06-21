package com.fashionstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.model.User;
import com.fashionstore.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean registerUser(User user) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO users(" +
                    "first_name, last_name, email, password, " +
                    "phone, gender, " +
                    "address_line1, address_line2, " +
                    "city, state, pincode, country) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1,
                    user.getFirstName());

            ps.setString(2,
                    user.getLastName());

            ps.setString(3,
                    user.getEmail());

            ps.setString(4,
                    user.getPassword());

            ps.setString(5,
                    user.getPhone());

            ps.setString(6,
                    user.getGender());

            ps.setString(7,
                    user.getAddressLine1());

            ps.setString(8,
                    user.getAddressLine2());

            ps.setString(9,
                    user.getCity());

            ps.setString(10,
                    user.getState());

            ps.setString(11,
                    user.getPincode());

            ps.setString(12,
                    user.getCountry());

            status = ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public User loginUser(
            String email,
            String password) {

        User user = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users " +
                    "WHERE email=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                user = extractUser(rs);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserById(int userId) {

        User user = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users " +
                    "WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                user = extractUser(rs);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean updateUser(User user) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE users SET " +
                    "first_name=?, " +
                    "last_name=?, " +
                    "email=?, " +
                    "phone=?, " +
                    "gender=?, " +
                    "address_line1=?, " +
                    "address_line2=?, " +
                    "city=?, " +
                    "state=?, " +
                    "pincode=?, " +
                    "country=? " +
                    "WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1,
                    user.getFirstName());

            ps.setString(2,
                    user.getLastName());

            ps.setString(3,
                    user.getEmail());

            ps.setString(4,
                    user.getPhone());

            ps.setString(5,
                    user.getGender());

            ps.setString(6,
                    user.getAddressLine1());

            ps.setString(7,
                    user.getAddressLine2());

            ps.setString(8,
                    user.getCity());

            ps.setString(9,
                    user.getState());

            ps.setString(10,
                    user.getPincode());

            ps.setString(11,
                    user.getCountry());

            ps.setInt(12,
                    user.getUserId());

            status = ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean emailExists(String email) {

        boolean exists = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT user_id " +
                    "FROM users " +
                    "WHERE email=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs =
                    ps.executeQuery();

            exists = rs.next();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return exists;
    }

    private User extractUser(
            ResultSet rs) throws Exception {

        User user = new User();

        user.setUserId(
                rs.getInt("user_id"));

        user.setFirstName(
                rs.getString("first_name"));

        user.setLastName(
                rs.getString("last_name"));

        user.setEmail(
                rs.getString("email"));

        user.setPassword(
                rs.getString("password"));

        user.setPhone(
                rs.getString("phone"));

        user.setGender(
                rs.getString("gender"));

        user.setAddressLine1(
                rs.getString("address_line1"));

        user.setAddressLine2(
                rs.getString("address_line2"));

        user.setCity(
                rs.getString("city"));

        user.setState(
                rs.getString("state"));

        user.setPincode(
                rs.getString("pincode"));

        user.setCountry(
                rs.getString("country"));

        user.setCreatedAt(
                rs.getTimestamp("created_at"));

        return user;
    }
}