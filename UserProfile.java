
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class UserProfile extends JPanel {

    public UserProfile(CardLayout pages, JPanel userProfile) {

        setLayout(new BorderLayout());

        // Heading panel
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(
                new BoxLayout(headingPanel, BoxLayout.Y_AXIS)
        );

        JLabel title = new JLabel("User Profile");
        title.setFont(new Font("Sans", Font.BOLD, 24));

        JLabel message = new JLabel("Welcome to the User Page! Please set up your profile here!");

        // 1. Center horizontally (cross-axis)
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);


        headingPanel.add(title);
        headingPanel.add(message);

        add(headingPanel, BorderLayout.NORTH);

        // Back button
        JButton back = new JButton("Back to Home");
        back.setPreferredSize(new Dimension(150, 30));
        back.setFont(new Font("Sans", Font.BOLD, 18));
        back.setBackground(new Color(182, 214, 255));

        back.addActionListener(e -> {
            pages.show(userProfile, "Home");
        });

        headingPanel.add(back);


// Profile form panel
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(
                new BoxLayout(profilePanel, BoxLayout.Y_AXIS)
        );

// Editable profile fields
        JTextField nameField = new JTextField(12);
        JTextField phoneField = new JTextField(12);
        JTextField emailField = new JTextField(12);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

// Add labels and fields
        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameField);

        emailPanel.add(new JLabel("Email:"));
        emailPanel.add(emailField);

        phonePanel.add(new JLabel("Phone Number:"));
        phonePanel.add(phoneField);

// Add spacing and panels
        profilePanel.add(Box.createVerticalStrut(15));
        profilePanel.add(namePanel);
        profilePanel.add(emailPanel);
        profilePanel.add(phonePanel);

        add(profilePanel, BorderLayout.CENTER);

        // Save button
        JButton saveButton = new JButton("Save Profile");

        // Display saved information
        JLabel savedMessage = new JLabel(" ");

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(
                bottomPanel, BoxLayout.Y_AXIS
        ));

        bottomPanel.add(saveButton);
        bottomPanel.add(savedMessage);

        add(bottomPanel, BorderLayout.SOUTH);

        // Save profile information
        saveButton.addActionListener(e -> {

            String name = nameField.getText();
            String phonenumber = phoneField.getText();
            String email = emailField.getText();

            savedMessage.setText(
                    "Profile saved! Welcome, " + name + "!"
            );

            // Store profile information in a HashMap
            Map<String, String> profile = new HashMap<>();

            profile.put("Name", name);
            profile.put("Phone Number", phonenumber);
            profile.put("Email", email);

        });
    }
}
