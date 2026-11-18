import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class shoppingHomePage{
    public static void main(String args[]){
        JFrame homeFrame = new JFrame("Shopping App Home Page");
        CardLayout pages = new CardLayout();
        JPanel mainPanel = new JPanel(pages);
        JPanel homePage = new JPanel();
        JPanel shoppingList = new ShoppingList(pages, mainPanel);
        ShoppingPage shoppingPage = new ShoppingPage(pages, mainPanel);
        JPanel userProfile = new UserProfile(pages, mainPanel);
        JPanel checkout = new Checkout(pages, mainPanel);

        JLabel title = new JLabel("Shopping App");
        JButton editList = new JButton("Edit Shopping List");
        JButton goShop = new JButton("Go Shopping");
        JButton userP = new JButton("User Profile");

        homePage.add(title);
        homePage.add(editList);
        homePage.add(goShop);
        homePage.add(userP);
        
        homePage.setBackground(new Color(182, 214, 255));

        shoppingList.add(new JLabel("My Shopping List"));
        userProfile.add(new JLabel("My User Profile"));
        checkout.add(new JLabel("My Checkout Page"));

        mainPanel.add(homePage, "Home");
        mainPanel.add(shoppingList, "Shopping List");
        mainPanel.add(shoppingPage, "Shopping Page");
        mainPanel.add(userProfile, "User Profile");
        mainPanel.add(checkout, "Checkout");

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

        editList.addActionListener(e -> {
            pages.show(mainPanel, "Shopping List");
        });
        goShop.addActionListener(e -> {
            pages.show(mainPanel, "Shopping Page");
        });
        userP.addActionListener(e -> {
            pages.show(mainPanel, "User Profile");
        });
        
        homeFrame.add(mainPanel);
        homeFrame.setSize(800, 700);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
    }
}