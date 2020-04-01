package dao;

import dto.ShoppingBasket;

import java.sql.*;

public class ShoppingBasketDao {

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

    public void buy(Integer productId, Integer quantity) {
        try {
            Connection connection = getConnection();
            String query = "insert into shopping_basket(product_id,quantity) values" +
                    "(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, quantity);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Integer productId) {
        try {
            Connection connection = getConnection();
            String query = "update shopping_basket set " +
                    "quantity=quantity-1 where product_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearJunk(Integer productId) {
        try {
            Connection connection = getConnection();
            String query = "delete from shopping_basket where product_id=? and quantity<=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,productId);
            preparedStatement.setInt(2,0);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printItems() {
        try {
            Connection connection = getConnection();
            String query = ("select" +
                    " p.name,b.quantity from product p join" +
                    " shopping_basket b where p.id=b.product_id ");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                String productName = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                System.out.println("___________________________" + "\n" + "Name:" + productName
                        + "\n" + "Quantity:" + quantity);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printPrice() {
        try {
            double totalPrice = 0.0;
            Connection connection = getConnection();
            String query = "select p.price,b.quantity from product p join" +
                    " shopping_basket b where p.id=b.product_id ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                totalPrice = totalPrice + (price * quantity);
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Total price:" + totalPrice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emptyBasket() {
        try {
            Connection connection = getConnection();
            String query = "delete from shopping_basket";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
