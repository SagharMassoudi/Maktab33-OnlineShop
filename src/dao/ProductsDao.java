package dao;

import dto.Products;
import dto.ShoppingBasket;

import java.sql.*;

public class ProductsDao {
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

    public String showProductList() {
        try {
            Connection connection = getConnection();
            String query = ("select * from product");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            System.out.println("***List Of Products***");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                String producer = resultSet.getString("producer");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                System.out.println("___________________________" + "\n" + "ID:" +
                        id + "\n" + "Name:" + name + "\n" + "Model:" + model +
                        "\n" + "Producer:" + producer + "\n" + "Price:" + price + "\n" +
                        "Stock:" + stock);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update() {
        try {
            Connection connection = getConnection();
            String query = " update product p join" +
                    " shopping_basket b on p.id=b.product_id set " +
                    "p.stock=p.stock-b.quantity";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
