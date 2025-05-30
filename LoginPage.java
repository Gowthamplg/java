import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {

    private JLabel lblUsername, lblPassword, lblMessage;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private Connection conn;

    public LoginPage() {
        super("Login Page");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(20);

        lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField(20);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);

        lblMessage = new JLabel();
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblUsername, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnLogin, gbc);

        gbc.gridy = 3;
        panel.add(lblMessage, gbc);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);

        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/login_db";
            String user = "darkseid";
            String password = "gowtham";

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected.");
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e);
            lblMessage.setText("Database connection failed.");
            lblMessage.setForeground(Color.RED);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = txtUsername.getText();
        // Using getPassword for security
        String password = new String(txtPassword.getPassword());

        if (authenticate(username, password)) {
            lblMessage.setText("Login Successful!");
            lblMessage.setForeground(new Color(0, 128, 0)); // dark green
        } else {
            lblMessage.setText("Invalid credentials.");
            lblMessage.setForeground(Color.RED);
        }
    }

    private boolean authenticate(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Authentication error: " + e);
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}
