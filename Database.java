import java.sql.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Database {

    // PostgreSQL connection details
    private static final String URL =
            "jdbc:postgresql://localhost:5432/shopping-list-app";
    private static final String USER = "postgres";
    private static final String PASSWORD = "abc";

    // Connect to PostgreSQL
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initialize() throws SQLException {
        try (Connection _ = connect()) {
            // Check database connection
        }
    }

    public static void shutdown() {
        // Connections close automatically
    }

    // User account information
    public static class Account {
        public final int id;
        public final String name;
        public final String email;

        public Account(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
    }

    // Saved address or payment method
    public static class SavedEntry {
        public final int id;
        public final String label;

        public SavedEntry(int id, String label) {
            this.id = id;
            this.label = label;
        }
    }

    // SIGN UP
    public static Account signUp(String name, String email,
                                 char[] password) throws SQLException {

        name = name == null ? "" : name.trim();
        email = email == null ? "" :
                email.trim().toLowerCase(Locale.ROOT);

        if (name.isEmpty() || name.length() > 100)
            throw new IllegalArgumentException("Please enter a valid name.");

        if (!email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")
                || email.length() > 255)
            throw new IllegalArgumentException("Please enter a valid email.");

        if (password == null || password.length < 8 ||
                new String(password).getBytes(StandardCharsets.UTF_8).length > 72)
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters (max 72 bytes).");

        String sql = "INSERT INTO users (name, email, password_hash) "
                   + "VALUES (?, ?, crypt(?, gen_salt('bf', 10))) "
                   + "RETURNING user_id";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, new String(password));

            try (ResultSet result = statement.executeQuery()) {
                result.next();
                return new Account(result.getInt(1), name, email);
            }
        }
    }

    // LOG IN
    public static Account logIn(String email, char[] password)
            throws SQLException {

        if (email == null || password == null)
            return null;

        String sql = "SELECT user_id, name, email FROM users "
                   + "WHERE email = ? "
                   + "AND password_hash = crypt(?, password_hash)";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email.trim().toLowerCase(Locale.ROOT));
            statement.setString(2, new String(password));

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Account(
                            result.getInt(1),
                            result.getString(2),
                            result.getString(3));
                }
                return null;
            }
        }
    }

    // Load saved information
    private static List<SavedEntry> load(String sql, int userId)
            throws SQLException {

        List<SavedEntry> entries = new ArrayList<>();

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    entries.add(new SavedEntry(
                            result.getInt(1),
                            result.getString(2)));
                }
            }
        }

        return entries;
    }

    public static List<SavedEntry> loadAddresses(int userId)
            throws SQLException {
        return load(
                "SELECT address_id, address_text FROM saved_addresses "
                + "WHERE user_id = ? ORDER BY address_id", userId);
    }

    public static List<SavedEntry> loadPaymentLabels(int userId)
            throws SQLException {
        return load(
                "SELECT payment_id, payment_label FROM saved_payment_methods "
                + "WHERE user_id = ? ORDER BY payment_id", userId);
    }

    // Add saved information
    private static void add(String sql, int userId, String value)
            throws SQLException {

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setString(2, value);
            statement.executeUpdate();
        }
    }

    public static void addAddress(int userId, String address)
            throws SQLException {
        add("INSERT INTO saved_addresses (user_id, address_text) "
                + "VALUES (?, ?)", userId, address);
    }

    public static void addPaymentLabel(int userId, String label)
            throws SQLException {
        add("INSERT INTO saved_payment_methods (user_id, payment_label) "
                + "VALUES (?, ?)", userId, label);
    }

    // Remove saved information
    private static void remove(String sql, int userId, int id)
            throws SQLException {

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    public static void removeAddress(int userId, int addressId)
            throws SQLException {
        remove("DELETE FROM saved_addresses "
                + "WHERE user_id = ? AND address_id = ?",
                userId, addressId);
    }

    public static void removePaymentLabel(int userId, int paymentId)
            throws SQLException {
        remove("DELETE FROM saved_payment_methods "
                + "WHERE user_id = ? AND payment_id = ?",
                userId, paymentId);
    }
}