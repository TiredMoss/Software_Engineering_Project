import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class ShoppingPage extends JPanel {
    public ShoppingPage(CardLayout pages, JPanel mainPanel){
        setLayout(new BorderLayout());

        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Shopping Page");
        title.setFont(new Font("Sans", Font.BOLD, 24));
        JLabel message = new JLabel("Welcome to the shopping page! Please browse our products.");

        headingPanel.add(title);
        headingPanel.add(message);
        add(headingPanel, BorderLayout.NORTH);

        JPanel productPanel = new JPanel(new GridLayout(0, 3, 15, 15));
        productPanel.setBackground(new Color(58, 75, 100));
        productPanel.setPreferredSize(new Dimension(750, 1500));

        productPanel.add(createProductCard("Grapes", 6.99, "https://images.albertsons-media.com/is/image/ABS/960564035-C1N1?$ng-ecom-pdp-desktop$&defaultImage=Not_Available"));
        productPanel.add(createProductCard("Fruity Pebbles", 4.29, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSoEjGCnoRWCN1MtEoQUz-verecQlteN5YvLd4w6XH7-A&s=10"));
        productPanel.add(createProductCard("Eggs", 2.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGOXJohINXPKzAxZyhTntDNYYabdUUpXTqFSwTqtfrGQ&s=10"));
        productPanel.add(createProductCard("Roses", 12.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdpOPJ0IWcJJJ5Wp5Gx6jvhSKtdQHFV064tLuZqZ3Rgw&s=10"));
        productPanel.add(createProductCard("Whole Milk", 3.39, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5-t5Ryti91fmysh2rTLu4HZdRkRemc0kGfDk3hSuaAw&s=10"));
        productPanel.add(createProductCard("Carrots", 2.49, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4JWc5ehxbZczdUrxiwECFjoB3xBPgUzJjXlrTkYjs7g&s=10"));
        productPanel.add(createProductCard("Coffee Ice Cream", 9.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxpTQGquebxktLZmWSIO9eLBYlETmeFhuQIdIpkNF2pQ&s=10"));
        productPanel.add(createProductCard("Pancakes", 2.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmucw5kzMYsIU8LkPwCbvgWFfkj_r1Gg8AqOHCYSUPfw&s=10"));
        productPanel.add(createProductCard("Eggos", 3.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1OqjqUl5z26Jqut5-LsAmxCJUtkxFfRlPgNOr7KlCeg&s"));
        productPanel.add(createProductCard("Strawberries", 3.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfhwIKFF2arsTlUVDjv1tNHtbtjpvWBiHpXV-oVchJYw&s=10"));
        productPanel.add(createProductCard("Raspberries", 7.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvjwl3tMg1NluZVCmcim00rwrotywfYIuel2BVLdMxoA&s=10"));
        productPanel.add(createProductCard("Smart Water", 22.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmouf1s0cGJyNIZapWf5O8C9FD4Doc901RHv2FKSanhA&s=10"));
        productPanel.add(createProductCard("Sunny D Orange Juice", 2.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGk2gncjVXVrOlyDNDoT-8y-8h41eGj_bn0H-Qoe2i7w&s=10"));
        productPanel.add(createProductCard("Kraft Mac and Chesse", 5.49, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1E7sgm5bshY8DazoTKgjXHtQQB6Hi7bxeotQOCkfI_g&s=10"));
        productPanel.add(createProductCard("Chocolate Croissant", 5.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2jd7PmIy1P4qFiHCvG3kwgBVgDgniqPyDLPp-fAPGDg&s=10"));
        productPanel.add(createProductCard("Apple Juice", 4.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2I-a5ukG90VtoJa5COA1eBmrogDs3RCEjzlBbhbCseQ&s=10"));
        productPanel.add(createProductCard("Cream Cheese", 4.99, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQInkZNiaGdGxbVisPKktlquoe51Y2cnfP7LndysjndQ&s=10"));
        productPanel.add(createProductCard("Everything Bagels", 5.49, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDKZSZFlqlgwP6f_OV6pcgATKF-dOfUWVNeCa0Y45CLw&s=10"));

        JScrollPane scroll = new JScrollPane(productPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        System.out.println("Product Panel Components: " + productPanel.getComponentCount());
        System.out.println("Scroll Pane Size: " + scroll.getSize());

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setPreferredSize(new Dimension(50, 30));
        checkoutButton.setFont(new Font("Sans", Font.BOLD, 18));
        checkoutButton.setBackground(new Color(182, 214, 255));
        checkoutButton.addActionListener(e -> {
            pages.show(mainPanel, "Checkout");
        });

        headingPanel.add(checkoutButton);
        
        JButton back = new JButton("Back to Home");
        back.setPreferredSize(new Dimension(50, 30));
        back.setFont(new Font("Sans", Font.BOLD, 18));
        back.setBackground(new Color(182, 214, 255));
        back.addActionListener(e -> {
            pages.show(mainPanel, "Home");
        });

        headingPanel.add(back);
    }

    private Map<String, Integer> cart = new HashMap<>();
    private JPanel createProductCard(String name, double price, String imageUrl){
        System.out.println("Creating card for: " + name);
        JPanel card = new JPanel();
        
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(186, 221, 252));

        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2), 
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        ImageIcon image = new ImageIcon();
        try {
            image = new ImageIcon(new java.net.URL(imageUrl));
            Image scaledImage = image.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
            image = new ImageIcon(scaledImage);
        } catch (Exception e){
            System.out.println("Could not load image for " + name);
        }

        JLabel productName = new JLabel(name);
        JLabel productPrice = new JLabel(String.format("$%.2f", price));
        JLabel productImage = new JLabel(image);
        JButton addButton = new JButton("Add to cart");
        
        JPanel quantityPanel = new JPanel();
        JButton minusButton = new JButton("-");
        JLabel quantityLabel = new JLabel("1");
        JButton plusButton = new JButton("+");
        quantityPanel.add(minusButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plusButton);
        quantityPanel.setVisible(false);

        addButton.addActionListener(e -> {
            cart.put(name, 1);
            quantityLabel.setText("1");
            addButton.setVisible(false);
            quantityPanel.setVisible(true);
        });

        plusButton.addActionListener(e -> {
            int quantity = cart.get(name) + 1;
            cart.put(name, quantity);
            quantityLabel.setText("" + quantity);
        });

        minusButton.addActionListener(e -> {
            int quantity = cart.get(name) - 1;
            if (quantity <= 0){
                cart.remove(name);
                quantityPanel.setVisible(false);
                addButton.setVisible(true);
            } else {
                cart.put(name, quantity);
                quantityLabel.setText("" + quantity);
            }
        });

        card.add(productName);
        card.add(productImage);
        card.add(productPrice);
        card.add(addButton);
        card.add(quantityPanel);

        return card;
    }
}
