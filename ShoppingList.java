import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShoppingList extends JPanel {
    public ShoppingList(CardLayout pages, JPanel mainPanel){
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(182, 214, 255));

        JLabel title = new JLabel("My Shopping List", SwingConstants.CENTER);
        title.setFont(new Font("Sans", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        JTextField itemInput = new JTextField(20);
        JButton addButton = new JButton("Add to List");

        inputPanel.add(itemInput);
        inputPanel.add(addButton);

        DefaultListModel<String> list = new DefaultListModel<>();
        JList<String> itemList = new JList<>(list);
        itemList.setFont(new Font("Sans", Font.PLAIN, 18));
        itemList.setBackground(Color.white);

        JScrollPane scroll = new JScrollPane(itemList);

        addButton.addActionListener(e -> {
            String item = itemInput.getText().trim();
            if (!item.isEmpty()){
                list.addElement("• " + item);
                itemInput.setText("");
            }
        });

        itemInput.addActionListener(e -> addButton.doClick());

        itemList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int index = itemList.locationToIndex(e.getPoint());
                if (index != -1 && itemList.getCellBounds(index, index).contains(e.getPoint())){
                    String item = list.getElementAt(index);
                    if (e.getClickCount() == 2){
                        list.remove(index);
                    }else if(e.getClickCount() == 1){
                        if (item.startsWith("✓ ")){
                            item = item.substring(2);
                        }else{
                            item = "✓ " + item;
                        }

                        list.setElementAt(item, index);
                    }
                }
            }
        });

        JButton clearButton = new JButton("Clear List");
        clearButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear the list?", "Clear List", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION){
                list.clear();
            }
        });

        JButton back = new JButton("Back to Home");
        back.addActionListener(e -> {
            pages.show(mainPanel, "Home");
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(back, BorderLayout.SOUTH);
        bottomPanel.add(clearButton, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(scroll, BorderLayout.CENTER);
    }
}