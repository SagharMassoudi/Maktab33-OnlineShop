package Main;

import dao.*;
import dto.Customer;
import java.util.Scanner;

public class OnlineShop {
    public static void main(String[] args) {
        try {
            System.out.println("Hello.welcome to our online shop.Inorder to start " +
                    ", first of all you need to register." +
                    "type \"register\" if you want to create an account.");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            if (input.equals("register")) {
                CustomerDao customerDao = new CustomerDao();
                Customer customer = new Customer();
                ProductsDao productsDao = new ProductsDao();
                ShoppingBasketDao shoppingBasketDao = new ShoppingBasketDao();
                int choice, productId, quantity, counter = 0;
                System.out.println("Now you are able to register in our online " +
                        "shop.Remember to follow the instruction carefully.For " +
                        "first step enter your first name : ");
                customer.setFirstName(scan.next());
                System.out.println("Enter your last name : ");
                customer.setLastName(scan.next());
                System.out.println("Enter your user name : ");
                customer.setUserName(scan.next());
                System.out.println("Enter a password : ");
                customer.setPassword(scan.next());
                System.out.println("Enter your Email address : ");
                customer.setEmailAddress(scan.next());
                System.out.println("Enter your phone number : ");
                customer.setPhoneNumber(scan.nextInt());
                System.out.println("Enter your address : ");
                customer.setAddress(scan.next());
                System.out.println("Enter your postal code : ");
                customer.setPostalCode(scan.nextInt());
                customerDao.register(customer);
                System.out.println("You are able to buy any of products from" +
                        " following list.");
                productsDao.showProductList();
                do {
                    System.out.println("___________________________" + "\n" + "Possible" +
                            " activities(Enter a number):" + "\n" +
                            "1-Buy products" + "\n" + "2-Remove items" +
                            "\n" + "3-Print items of shopping basket" +
                            "\n" + "4-Print total price" + "\n" + "5-Confirm" +
                            " (End shopping process)");
                    choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter the product's" +
                                    " id  and quantity of product which you want to buy and" +
                                    " remember that, while you don't need " +
                                    "any other product enter 0:");

                            buy:
                            do {
                                System.out.println("Product's id:");
                                productId = scan.nextInt();
                                if (productId == 0) {
                                    System.out.println("Back to menu successfully.");
                                    break buy;
                                }
                                System.out.println("Quantity:");
                                quantity = scan.nextInt();
                                counter = counter + quantity;
                                if (counter >= 5) {
                                    System.out.println("Your shopping" +
                                            " basket is already full.If you " +
                                            "want to add any other" +
                                            " product you need " +
                                            "to stop buying process" +
                                            " remove at least one product" +
                                            " then restart buying process.");
                                }
                                shoppingBasketDao.buy(productId, quantity);
                            } while (productId == 0 || counter < 5);
                            break;
                        case 2:
                            System.out.println("Enter product's id which you" +
                                    " want to remove from your shopping basket" +
                                    " and remember that,while you don't " +
                                    "want to remove any other product enter 0:");

                            remove:
                            do {
                                productId = scan.nextInt();
                                if (productId == 0) {
                                    System.out.println("Back to menu successfully.");
                                    break remove;
                                }
                                shoppingBasketDao.remove(productId);
                                counter = counter - 1;
                                shoppingBasketDao.clearJunk(productId);
                                System.out.println("Current item removed successfully." +
                                        "Enter product id which you want to remove " +
                                        "from your shopping basket or enter 0:");
                            } while (productId == 0 || counter < 5);
                            break;
                        case 3:
                            System.out.println("****BOUGHT ITEMS****");
                            shoppingBasketDao.printItems();
                            break;


                        case 4:
                            shoppingBasketDao.printPrice();
                            break;


                        case 5:
                            productsDao.update();
                            shoppingBasketDao.emptyBasket();
                            System.out.println("Thank you for shopping with us.");
                            break;
                    }
                } while (choice != 5);

            } else
                System.out.println("Invalid input");
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}
