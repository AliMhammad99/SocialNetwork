package GUI.pages;

import DB.Account;
import DB.DataBaseConnection;
import static DB.DataBaseConnection.*;
import design.Design;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Game extends JPanel {

    private static int CANVAS_WIDTH;
    private static int CANVAS_HEIGHT;

    private static final int UPDATE_INTERVAL = 30; // milliseconds

    // Attributes of moving object
    private int x = 20;     // top-left (x, y)
    private int y = 20;
    private int size = 100;  // width and height
    private float xSpeed = 0;  // moving speed in x and y directions
    private float ySpeed = 0;  // displacement per step in x and y
    private float gravity = 2;
    private float friction = 0.7f;
    private int xPoint = 400;     // top-left (x, y)
    private int yPoint = 400;
    private final int sizePoint = 50;
    private int xDamage = 700;
    private int yDamage = 900;
    private final int sizeDamage = 50;
    private int score = 0;
    private Boolean start = false;

    private final ScorePanel highScores;

    public Game() throws SQLException {
        this.setSize(new Dimension(500, 500));
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new Controller());
        this.highScores = new ScorePanel("Friends' Highscores");

        // Create a new thread to run update at regular interval
        Thread updateThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (start == true) {
                        try {
                            update();   // update the (x, y) position
                        } catch (SQLException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();  // Refresh the JFrame. Called back paintComponent()
                    }
                    try {
                        Thread.sleep(UPDATE_INTERVAL); // Delay and give other thread a chance to run
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }
            }
        };

        updateThread.start();
    }

    // Update the (x, y) position of the moving object
    private void update() throws SQLException {
        CANVAS_WIDTH = this.getWidth();
        CANVAS_HEIGHT = this.getHeight();
        x += xSpeed;
        y += ySpeed;
        ySpeed += gravity;

        if (collideLeft() || collideRight()) {
            xSpeed = -xSpeed;
        }
        if (collideUp()) {
            y = 0;
        }
        if (collideDown()) {
            ySpeed = ySpeed * friction;
            friction = friction - friction * 0.1f;
            if (ySpeed > 0f && ySpeed < 1f) {
                ySpeed = 0;
                friction = 0;
                gravity = 0;
                xSpeed = 0;
            }
            ySpeed = -ySpeed;
            y = CANVAS_HEIGHT - size;
        }
        if (pointCollision()) {
            score += 1;
            this.setBorder(BorderFactory.createTitledBorder(null, "Score: " + score, TitledBorder.CENTER,
                    TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
            replacePoint();
            replaceDamage();
            size += 10;
            if (size > 200) {
                size = 100;
            }
        }
        if (damageCollision()) {
            size = 100;
            start = false;
            x = 20;
            y = 20;
            JOptionPane.showMessageDialog(null,
                    "Game Over: Your Score is " + score,
                    "Game Result",
                    JOptionPane.WARNING_MESSAGE);
            updateHighScore(score);
            highScores.refreshHighScores();
            highScores.setVisible(true);
            score = 0;
            this.setBorder(BorderFactory.createTitledBorder(null, "Score: " + score, TitledBorder.CENTER,
                    TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        }
    }

    private void replacePoint() {
        int max;
        int min;
        int range;
        do {
            max = CANVAS_WIDTH - sizePoint - size;
            min = sizePoint + size;
            range = (max - min);
            xPoint = (int) ((Math.random() * range) + min);
            max = CANVAS_HEIGHT - sizePoint - size;
            min = sizePoint + size;
            range = (max - min);
            yPoint = (int) ((Math.random() * range) + min);
        } while (x - xPoint <= 100 && x - xPoint >= -100
                && y - yPoint <= 100 && y - yPoint >= -100);
    }

    private void replaceDamage() {
        int max;
        int min;
        int range;
        do {
            max = CANVAS_WIDTH - sizePoint - size;
            min = sizePoint + size;
            range = (max - min);
            xDamage = (int) ((Math.random() * range) + min);
            max = CANVAS_HEIGHT - sizePoint - size - 200;
            min = sizePoint;
            range = (max - min);
            yDamage = (int) ((Math.random() * range) + min);
        } while (x - xDamage <= 100 && x - xDamage >= -100
                && y - yDamage <= 100 && y - yDamage >= -100);
    }

    private boolean collideUp() {
        return y <= 0;
    }

    private boolean collideDown() {
        return y >= CANVAS_HEIGHT - size;
    }

    private boolean collideRight() {
        return x >= CANVAS_WIDTH - size;
    }

    private boolean collideLeft() {
        return x <= 0;
    }

    private void moveRight() {
        if (xSpeed <= 0 && !collideDown() && !collideRight()) {
            xSpeed = 10;
        }
    }

    private void moveLeft() {
        if (xSpeed >= 0 && !collideDown() && !collideLeft()) {
            xSpeed = -10;
        }
    }

    private void jump() {
        ySpeed = 45;
        gravity = 2;
        friction = 0.7f;
    }

    private Boolean pointCollision() {
        Rectangle rect = new Rectangle(new Point(xPoint, yPoint), new Dimension(sizePoint, sizePoint));
        Rectangle result = SwingUtilities.computeIntersection(x + 20, y + 20, size - 20, size - 20, rect);
        return result.getWidth() > 0 && result.getHeight() > 0;
    }

    private Boolean damageCollision() {
        Rectangle rect = new Rectangle(new Point(xDamage, yDamage), new Dimension(sizeDamage, sizeDamage));
        Rectangle result = SwingUtilities.computeIntersection(x + 20, y + 20, size - 20, size - 20, rect);
        return result.getWidth() > 0 && result.getHeight() > 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // paint parent's background
        setBackground(Design.BACK_COLOR);
        g.setColor(Design.FONT_COLOR);
        g.fillOval(x, y, size, size);  // draw the Ball
        g.setColor(Color.green);
        g.fillOval(xPoint, yPoint, sizePoint, sizePoint);  // draw a Point
        g.setColor(Color.red);
        g.fillOval(xDamage, yDamage, sizeDamage, sizeDamage);  // draw a Damage
    }

    private class Controller implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    moveRight();
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft();
                    break;
                case KeyEvent.VK_SPACE:
                    start = true;
                    jump();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public void changeTheme() {
        this.highScores.changeTheme();
    }

    private class ScorePanel extends JFrame {

        private JScrollPane scoresScrollPane;
        private JPanel scoresPanel;

        private ScorePanel(String title) throws SQLException {
            super(title);
            buildFrame();
            buildPanel();
        }

        private void buildFrame() throws SQLException {
            this.setSize(720, 480);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.getContentPane().setBackground(Design.BACK_COLOR);
        }

        private void buildPanel() throws SQLException {
            this.scoresPanel = new JPanel();
            this.scoresPanel.setLayout(new BoxLayout(this.scoresPanel, BoxLayout.Y_AXIS));
            this.scoresPanel.setOpaque(false);

            this.scoresScrollPane = new JScrollPane(this.scoresPanel);
            this.scoresScrollPane.setOpaque(false);
            this.scoresScrollPane.getViewport().setOpaque(false);
            this.scoresScrollPane.getVerticalScrollBar().setUnitIncrement(16);
            this.scoresScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Your friends High Scores", TitledBorder.CENTER,
                    TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));

            fillHighScores();

            this.add(scoresScrollPane);
        }

        private void fillHighScores() throws SQLException {

            ArrayList<Account> friendsScores = getFriendsScores();
            friendsScores.forEach(friendScore -> {

                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Design.FONT_COLOR));
                if (friendScore.getAccountId() == DataBaseConnection.SESSION) {
                    panel.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.green));
                }
                panel.setOpaque(false);
                JLabel name = new JLabel(friendScore.getFirstName() + " " + friendScore.getLastName());
                name.setForeground(Design.FONT_COLOR);
                name.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
                JLabel score = new JLabel(friendScore.getScore() + "");
                score.setForeground(Design.FONT_COLOR);
                score.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
                JLabel profilePic = new JLabel();
                profilePic.setSize(50, 50);
                ImageIcon icon = new ImageIcon("images/" + friendScore.getProfilePicture());
                Image img = icon.getImage();
                int ratio = icon.getIconHeight() / profilePic.getHeight();
                Image imgScale = img.getScaledInstance(icon.getIconWidth() / ratio, icon.getIconHeight() / ratio, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(imgScale);
                profilePic.setIcon(scaledIcon);
                panel.add(profilePic);
                panel.add(name);
                panel.add(score);
                this.scoresPanel.add(panel);
            });
        }

        public void changeTheme() {
            this.getContentPane().setBackground(Design.BACK_COLOR);
            this.scoresScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Your friends High Scores", TitledBorder.CENTER,
                    TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        }

        public void refreshHighScores() throws SQLException {
            this.scoresPanel.removeAll();
            this.buildPanel();
        }

    }
}
