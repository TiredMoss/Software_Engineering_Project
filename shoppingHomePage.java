import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class shoppingHomePage{
    public static void main(String args[]) {
        // Connect to the database
        try {
            Database.initialize();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Database connection failed: " + ex.getMessage());
            return;
        }
        JFrame homeFrame = new JFrame("Shopping App Home Page");
        CardLayout pages = new CardLayout();
        JPanel mainPanel = new JPanel(pages);
        JPanel homePage = new JPanel();
        Map<String, Integer> cart = new HashMap<>();
        Map<String, Double> prices = new HashMap<>();
        JPanel shoppingList = new ShoppingList(pages, mainPanel);
        Checkout checkout = new Checkout(pages, mainPanel, cart, prices);
        ShoppingPage shoppingPage = new ShoppingPage(pages, mainPanel, cart, prices, checkout);
        JPanel userProfile = new UserProfile(pages, mainPanel);

        JLabel title = new JLabel("Shopping App");
        JButton editList = new JButton("Edit Shopping List");
        JButton goShop = new JButton("Go Shopping");
        JButton userP = new JButton("User Profile");
        JButton checkoutButton = new JButton("Checkout");

        homePage.add(title);
        homePage.add(editList);
        homePage.add(goShop);
        homePage.add(userP);
        homePage.add(checkoutButton);

        homePage.setBackground(new Color(182, 214, 255));
        // Login and signup page
        AuthPage auth = new AuthPage(account -> {
            title.setText("Welcome, " + account.name + "!");
            pages.show(mainPanel, "Home");
        });


        mainPanel.add(auth, "Auth");
        mainPanel.add(homePage, "Home");
        mainPanel.add(shoppingList, "Shopping List");
        mainPanel.add(shoppingPage, "Shopping Page");
        mainPanel.add(userProfile, "User Profile");
        mainPanel.add(checkout, "Checkout");
// Button styling
        title.setFont(new Font("Sans", Font.BOLD, 20));
        editList.setPreferredSize(new Dimension(200, 50));
        editList.setFont(new Font("Sans", Font.BOLD, 18));
        editList.setBackground(new Color(255, 182, 193));
        goShop.setPreferredSize(new Dimension(200, 50));
        goShop.setFont(new Font("Sans", Font.BOLD, 18));
        goShop.setBackground(new Color(58, 75, 100));
        userP.setPreferredSize(new Dimension(200, 50));
        userP.setFont(new Font("Sans", Font.BOLD, 18));
        userP.setBackground(new Color(224, 149, 251));
        checkoutButton.setPreferredSize(new Dimension(200, 50));
        checkoutButton.setFont(new Font("Sans", Font.BOLD, 18));
        checkoutButton.setBackground(new Color(255, 105, 180));
// Navigation
        editList.addActionListener(e -> {
            pages.show(mainPanel, "Shopping List");
        });
        goShop.addActionListener(e -> {
            pages.show(mainPanel, "Shopping Page");
        });
        userP.addActionListener(e -> {
            pages.show(mainPanel, "User Profile");
        });

        checkoutButton.addActionListener(e -> {
            pages.show(mainPanel, "Checkout");
        });

        homeFrame.add(mainPanel);
        homeFrame.setSize(800, 700);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);

        pages.show(mainPanel, "Auth");
        homeFrame.setVisible(true);

    }
}