import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class ShoppingPage extends JPanel {
    public ShoppingPage(CardLayout pages, JPanel mainPanel){
        setLayout(new BorderLayout());
        JPanel shoppingPage = new JPanel();
        JLabel title = new JLabel("Shopping Page");
        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(50, 30));
        back.setFont(new Font("Sans", Font.BOLD, 18));
        back.setBackground(new Color(182, 214, 255));

        shoppingPage.add(title);
        shoppingPage.add(back);


        back.addActionListener(e -> {
            pages.show(mainPanel, "Home");
        });
    }
}