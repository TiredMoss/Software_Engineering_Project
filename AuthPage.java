import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;

public class AuthPage extends JPanel {

    public interface OnLogin {
        void accept(Database.Account account) throws SQLException;
    }

    private final JTextField loginEmail = new JTextField(20);
    private final JPasswordField loginPassword = new JPasswordField(20);

    private final JTextField signupName = new JTextField(20);
    private final JTextField signupEmail = new JTextField(20);
    private final JPasswordField signupPassword = new JPasswordField(20);
    private final JPasswordField confirmPassword = new JPasswordField(20);

    private final JTabbedPane tabs = new JTabbedPane();
    private final OnLogin onLogin;

    public AuthPage(OnLogin onLogin) {
        this.onLogin = onLogin;
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Shopping List App", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        tabs.addTab("Log In", makeLoginForm());
        tabs.addTab("Sign Up", makeSignupForm());
        add(tabs, BorderLayout.CENTER);
    }

    // Create the login form
    private JPanel makeLoginForm() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel fields = new JPanel(new GridLayout(2, 2, 10, 10));

        fields.add(new JLabel("Email:"));
        fields.add(loginEmail);
        fields.add(new JLabel("Password:"));
        fields.add(loginPassword);

        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(e -> logIn());

        panel.add(fields, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.SOUTH);
        return panel;
    }

    // Create the signup form
    private JPanel makeSignupForm() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel fields = new JPanel(new GridLayout(4, 2, 10, 10));

        fields.add(new JLabel("Name:"));
        fields.add(signupName);
        fields.add(new JLabel("Email:"));
        fields.add(signupEmail);
        fields.add(new JLabel("Password (at least 8 characters):"));
        fields.add(signupPassword);
        fields.add(new JLabel("Confirm password:"));
        fields.add(confirmPassword);

        JButton signupButton = new JButton("Create Account");
        signupButton.addActionListener(e -> signUp());

        panel.add(fields, BorderLayout.CENTER);
        panel.add(signupButton, BorderLayout.SOUTH);
        return panel;
    }

    // Check login details in the database
    private void logIn() {
        char[] password = loginPassword.getPassword();
        try {
            Database.Account account = Database.logIn(loginEmail.getText(), password);

            if (account == null) {
                JOptionPane.showMessageDialog(this, "Incorrect email or password.");
                return;
            }

            onLogin.accept(account);
            clearFields();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } finally {
            Arrays.fill(password, '\0');
            loginPassword.setText("");
        }
    }

    // Register a new user in the database
    private void signUp() {
        char[] password = signupPassword.getPassword();
        char[] confirm = confirmPassword.getPassword();

        try {
            if (!Arrays.equals(password, confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.");
                return;
            }

            Database.Account account = Database.signUp(
                    signupName.getText(), signupEmail.getText(), password);

            onLogin.accept(account);
            clearFields();
            JOptionPane.showMessageDialog(this, "Account created successfully!");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());

        } catch (SQLException ex) {
            if ("23000".equals(ex.getSQLState()) || "23505".equals(ex.getSQLState())) {
                JOptionPane.showMessageDialog(this, "This email is already registered.");
            } else {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }
        } finally {
            Arrays.fill(password, '\0');
            Arrays.fill(confirm, '\0');
            signupPassword.setText("");
            confirmPassword.setText("");
        }
    }

    // Clear fields after login or logout
    public void clearFields() {
        loginEmail.setText("");
        loginPassword.setText("");
        signupName.setText("");
        signupEmail.setText("");
        signupPassword.setText("");
        confirmPassword.setText("");
        tabs.setSelectedIndex(0);
    }
}