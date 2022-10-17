package GUI;

import DB.*;
import design.Design;
import java.awt.*;
import static java.awt.Color.red;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import org.apache.commons.net.util.Base64;

public class LogIn extends JFrame {

    //Login Frame Components
    private JLabel loginTitle;
    //Fields Labels
    private JLabel emailLabel;
    private JLabel passwordLabel;
    //Error Labels
    private JLabel emailError;
    private JLabel passwordError;
    private JLabel loginError;
    //Iput fields
    private JTextField email;
    private JPasswordField password;
    //Buttons
    private JButton loginBtn;
    private JButton signUpBtn;
    //SignUp frame
    private JFrame signUpFrame;
    //Application core
    private Core core = null;

    public LogIn() throws ClassNotFoundException, SQLException {
        buildFrame();
        buildForm();
    }

    private void buildFrame() {
        super.setTitle("Login");
        super.setSize(720, 720);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Maximize the Window
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void buildForm() throws ClassNotFoundException, SQLException {

        //Creating loginTitle Label
        this.loginTitle = new JLabel("Login");
        this.loginTitle.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_LARGE));
        this.loginTitle.setForeground(Design.BACK_COLOR);

        //Creating the top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Design.FONT_COLOR);
        topPanel.add(loginTitle);

        //Creating Email Label
        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
        this.emailLabel.setForeground(Design.FONT_COLOR);
        //Creating Email Error Label
        this.emailError = new JLabel("");
        this.emailError.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
        this.emailError.setForeground(red);
        //Creating Login Error Label
        this.loginError = new JLabel("");
        this.loginError.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
        this.loginError.setForeground(red);
        this.loginError.setOpaque(false);

        //Creating Password Label
        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
        this.passwordLabel.setForeground(Design.FONT_COLOR);
        //Creating Password Error Label
        this.passwordError = new JLabel("");
        this.passwordError.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
        this.passwordError.setForeground(red);
        this.loginError.setOpaque(false);

        //Creating Input Fields
        this.email = new JTextField();
        this.password = new JPasswordField();

        //Creating formContainer with GridBagLayout
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 10, 0);

        //Creating Email Panel which contains all email related components
        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(Box.createHorizontalStrut(50));
        emailPanel.add(email);
        emailPanel.add(Box.createHorizontalStrut(10));
        emailPanel.add(emailError);

        email.setPreferredSize(new Dimension(150, 25));
        email.addKeyListener(new RemoveErrors());
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.setOpaque(false);

        //Creating Password Panel which contains all password related components
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createHorizontalStrut(15));
        passwordPanel.add(password);
        passwordPanel.add(Box.createHorizontalStrut(10));
        passwordPanel.add(passwordError);

        password.setPreferredSize(new Dimension(150, 25));
        password.addKeyListener(new RemoveErrors());
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.setOpaque(false);

        //Creating login error panel
        JPanel loginErrorPanel = new JPanel();
        loginErrorPanel.add(loginError);
        loginErrorPanel.setOpaque(false);

        //Adding Panels to formContainer
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 1;
        formContainer.add(emailPanel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        formContainer.add(passwordPanel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        formContainer.add(loginErrorPanel, constraints);
        //Adding title border to formContainer
        formContainer.setBorder(BorderFactory.createTitledBorder(null, "Login", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        formContainer.setBackground(Design.BACK_COLOR);

        //Creating Login button
        loginBtn = new JButton("Login");
        loginBtn.setBackground(Design.BACK_COLOR);
        loginBtn.setForeground(Design.FONT_COLOR);
        loginBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //Adding a listener to login button to validate credentials
        loginBtn.addActionListener(new ValidateLogIn());

        //Creating Sign up button
        signUpBtn = new JButton("Sign up");
        signUpBtn.setBackground(Design.BACK_COLOR);
        signUpBtn.setForeground(Design.FONT_COLOR);
        signUpBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        signUpBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //Adding a listener to sign up button to show sign up frame on click
        signUpBtn.addActionListener(new ToggleSignUp());

        //Creating button container
        JPanel btnContainer = new JPanel();
        btnContainer.setBackground(Design.FONT_COLOR);
        btnContainer.add(loginBtn);
        btnContainer.add(signUpBtn);

        //Adding Panels to this frame
        super.add(topPanel, BorderLayout.NORTH);
        super.add(formContainer, BorderLayout.CENTER);
        super.add(btnContainer, BorderLayout.SOUTH);

        //Creating the sign up frame which is hidden at first
        this.signUpFrame = new SignUp();
        this.signUpFrame.setLocationRelativeTo(this);

        //Setting the Frame icon
        this.setIconImage(Design.ICON.getImage());
    }

    //This function return this frame. It is used inside the innser classes 
    //to access the outer class
    private LogIn getLogIn() {
        return this;
    }

    //This class is responsible for credentials validation
    private class ValidateLogIn implements ActionListener {

        @Override
        @SuppressWarnings("empty-statement")
        public void actionPerformed(ActionEvent e) {

            if (validateForm()) {
                try {//If login is valid we close the connection and the frame
                    if (validateLogin()) {
                        System.out.println("* Login Success");
                    }
                } catch (ClassNotFoundException | SQLException | InterruptedException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //Email validation
    private boolean validateEmail() {
        if (email.getText().length() < 1) {
            email.setBackground(Color.red);
            emailError.setText("Enter your Email!");
            return false;
        } else {
            email.setBackground(Color.white);
            emailError.setText("");
            return true;
        }
    }

    //Password validation
    private boolean validatePassword() {
        if (password.getText().length() < 1) {
            password.setBackground(Color.red);
            passwordError.setText("Enter your Password!");
            return false;
        } else {
            password.setBackground(Color.white);
            passwordError.setText("");
            return true;
        }
    }

    //Form validation
    private boolean validateForm() {
        return validateEmail() && validatePassword();
    }

    //Encryption function
    public static String encrypt(String texto) {
        return new String(Base64.encodeBase64(texto.getBytes()));
    }

    //Credentials validation
    private boolean validateLogin() throws ClassNotFoundException, SQLException, InterruptedException {
        Account account = DataBaseConnection.getAccount(email.getText(), encrypt(password.getText()));
        if (account == null) {
            this.loginError.setForeground(Color.RED);
            this.loginError.setText("Incorrect email or password");
            return false;
        } else {
            this.loginError.setForeground(Color.GREEN);
            this.loginError.setText("Welcome " + account.getFirstName() + " " + account.getLastName());
            this.loginError.updateUI();
            DataBaseConnection.SESSION = account.getAccountId();
            System.out.println("* Session Created: " + DataBaseConnection.SESSION);
            Thread closeLogIn = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        getLogIn().setVisible(false);
                        signUpFrame.setVisible(false);
                        //getLogIn().dispatchEvent(new WindowEvent(getLogIn(), WindowEvent.WINDOW_CLOSING));
                        if (core == null) {
                            core = new Core(getLogIn());
                        } else {
                            core.refreshCore();
                        }
                        core.setVisible(true);
                    } catch (InterruptedException | SQLException | IOException ex) {
                        Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            closeLogIn.start();
            return true;
        }
    }

//This listener removes error messages on key press
    private class RemoveErrors implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            getLogIn().email.setBackground(Color.WHITE);
            getLogIn().emailError.setText("");
            getLogIn().password.setBackground(Color.WHITE);
            getLogIn().passwordError.setText("");
            getLogIn().loginError.setText("");
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

//This listener shows/hides sign up frame
    private class ToggleSignUp implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            signUpFrame.setVisible(!signUpFrame.isVisible());
        }

    }
}
