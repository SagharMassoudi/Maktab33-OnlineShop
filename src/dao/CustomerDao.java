package dao;

import dto.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDao {
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection;

            connection = DriverManager.getConnection("jdbc:mysql://localhost:" +
                    "3306/online shop", "root", null);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void register(Customer customer) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert " +
                    "into customer(id,first_name,last_name,user_name,password" +
                    ",Email_Address,phone_number,address,postal_code) values (?,?,?" +
                    ",?,?,?,?,?,?)");
            preparedStatement.setInt(1,customer.getId());
            preparedStatement.setString(2,customer.getFirstName());
            preparedStatement.setString(3,customer.getLastName());
            preparedStatement.setString(4,customer.getUserName());
            preparedStatement.setString(5,customer.getPassword());
            preparedStatement.setString(6,customer.getEmailAddress());
            preparedStatement.setInt(7,customer.getPhoneNumber());
            preparedStatement.setString(8,customer.getAddress());
            preparedStatement.setInt(9,customer.getPostalCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
