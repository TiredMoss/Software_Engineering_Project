import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Checkout extends JPanel {
    private Map<String, Integer> cart;
    private Map<String, Double> prices; 
    private JPanel itemsPanel;
    private JLabel totalLabel;
    
    public Checkout(CardLayout pages, JPanel mainPanel, Map<String, Integer> cart, Map<String, Double> prices){
        this.cart = cart;
        this.prices = prices;
        setLayout(new BorderLayout());
        JLabel title = new JLabel("My Checkout Page");
        title.setFont(new Font("Sans", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(itemsPanel);
        add(scroll, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Sans", Font.BOLD, 20));

        JButton back = new JButton("Back to Shopping");
        back.setBackground(new Color(182, 214, 255));
        back.addActionListener(e -> {
            pages.show(mainPanel, "Shopping Page");
        });
        JButton pay = new JButton("Pay Now");
        pay.setFont(new Font("Sans", Font.BOLD, 16));
        pay.setBackground(new Color(186, 221, 252));
        pay.addActionListener(e -> {
            showPaymentForm(pages, mainPanel);
        });

        bottomPanel.add(totalLabel);
        bottomPanel.add(back);
        bottomPanel.add(pay);
        add(bottomPanel, BorderLayout.SOUTH);
        refreshCart();
    }

    public void refreshCart(){
        itemsPanel.removeAll();
        double total = 0;

        if (cart.isEmpty()){
            itemsPanel.add(new JLabel("Your cart is empty! Feel free to browse the items."));
        }

        for (String name : cart.keySet()){
            int quantity = cart.get(name);
            double price = prices.get(name);
            double itemTotal = price * quantity;
            total += itemTotal;

            JLabel item = new JLabel("- " + name + " | Quantity: " + quantity + " | Price: $" + String.format("%.2f", price) + 
            " | Subtotal: $" + String.format("%.2f", itemTotal));
            item.setFont(new Font("Sans", Font.PLAIN, 16));
            item.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            itemsPanel.add(item);
        }

        totalLabel.setText("Total: $" + String.format("%.2f", total));
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private void showPaymentForm(CardLayout pages, JPanel mainPanel){
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JLabel title = new JLabel("Payment Information");
        title.setFont(new Font("Sans", Font.BOLD, 22));

        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField zipField = new JTextField();
        JTextField cardField = new JTextField();
        JTextField expirationField = new JTextField();
        JPasswordField cvvField = new JPasswordField();

        paymentPanel.add(title);
        paymentPanel.add(new JLabel(""));
        paymentPanel.add(new JLabel("Full Name:"));
        paymentPanel.add(nameField);
        paymentPanel.add(new JLabel("Street Address:"));
        paymentPanel.add(addressField);
        paymentPanel.add(new JLabel("City:"));
        paymentPanel.add(cityField);
        paymentPanel.add(new JLabel("Zip Code:"));
        paymentPanel.add(zipField);
        paymentPanel.add(new JLabel("Card Number:"));
        paymentPanel.add(cardField);
        paymentPanel.add(new JLabel("Expiration Date:"));
        paymentPanel.add(expirationField);
        paymentPanel.add(new JLabel("CVV:"));
        paymentPanel.add(cvvField);

        JButton pay = new JButton("Confirm Pay");
        JButton back = new JButton("Back to Checkout");
        paymentPanel.add(back);
        paymentPanel.add(pay);
        back.addActionListener(e -> {
            pages.show(mainPanel, "Checkout");
        });
        pay.addActionListener(e -> {
            if (nameField.getText().trim().isEmpty() || 
                addressField.getText().trim().isEmpty() || 
                cityField.getText().trim().isEmpty() || 
                zipField.getText().trim().isEmpty() || 
                cardField.getText().trim().isEmpty() || 
                expirationField.getText().trim().isEmpty() || 
                cvvField.getPassword().length == 0){
                JOptionPane.showMessageDialog(paymentPanel, "Please fill in all the blanks.");
            }else{
                JOptionPane.showMessageDialog(paymentPanel, "Your order has been completed!");
                cart.clear();
                pages.show(mainPanel, "Home");
            }
        });

        mainPanel.add(paymentPanel, "Payment");
        pages.show(mainPanel, "Payment");
    }
}