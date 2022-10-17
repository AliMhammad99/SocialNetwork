package GUI;

import GUI.pages.*;
import design.Design;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

public class Core extends JFrame {

    private final LogIn login;
    //The container of all other components
    Container mainContainer;
    //The navigation panel which contains those 5 buttons
    private JPanel navPanel;
    private JButton newsButton;
    private JButton profileButton;
    private JButton peopleButton;
    private JButton gameButton;
    private JButton themeButton;
    private JButton logoutButton;

    //The 4 pages of our application
    private News newsPanel;
    private Profile profilePanel;
    private People peoplePanel;
    private Game gamePanel;

    //Pages names used to navigate between them using CardLayout
    private final String NEWS_PANEL = "News Card";
    private final String PROFILE_PANEL = "Profile Card";
    private final String PEOPLES_PANEL = "People Card";
    private final String GAME_PANEL = "Game Card";

    //Pages(cards) container 
    private JPanel pages;

    public Core(LogIn login) throws SQLException, IOException {
        this.login = login;
        build();
    }

    //Building the frame
    private void build() throws SQLException, IOException {
        //Configuring the Frame
        this.setTitle("Social Media");
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Outer Border
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Design.FONT_COLOR));
        //Start with maximized window
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Setting the Frame icon
        this.setIconImage(Design.ICON.getImage());

        //Main container
        mainContainer = new Container();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBackground(Design.BACK_COLOR);

        //Navigation panel
        navPanel = new JPanel();
        navPanel.setBackground(Design.FONT_COLOR);
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Navigation buttons
        newsButton = new JButton("News");
        profileButton = new JButton("Profile");
        peopleButton = new JButton("People");
        gameButton = new JButton("Game");
        themeButton = new JButton("Theme");
        logoutButton = new JButton("Log Out");

        //Navigation buttons design
        newsButton.setBackground(Design.BACK_COLOR);
        newsButton.setForeground(Design.FONT_COLOR);
        newsButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        profileButton.setBackground(Design.BACK_COLOR);
        profileButton.setForeground(Design.FONT_COLOR);
        profileButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        peopleButton.setBackground(Design.BACK_COLOR);
        peopleButton.setForeground(Design.FONT_COLOR);
        peopleButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        gameButton.setBackground(Design.BACK_COLOR);
        gameButton.setForeground(Design.FONT_COLOR);
        gameButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        themeButton.setBackground(Design.BACK_COLOR);
        themeButton.setForeground(Design.FONT_COLOR);
        themeButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        logoutButton.setBackground(Design.BACK_COLOR);
        logoutButton.setForeground(Design.FONT_COLOR);
        logoutButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));

        //Navigation buttons click handlers
        newsButton.addActionListener(new Navigation());
        profileButton.addActionListener(new Navigation());
        peopleButton.addActionListener(new Navigation());
        gameButton.addActionListener(new Navigation());
        themeButton.addActionListener(new Navigation());
        logoutButton.addActionListener(new Navigation());

        //Cursor pointer on hover
        newsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        peopleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        themeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //Adding buttons to navigation panel
        navPanel.add(newsButton);
        navPanel.add(profileButton);
        navPanel.add(peopleButton);
        navPanel.add(gameButton);
        navPanel.add(themeButton);
        navPanel.add(logoutButton);

        //Adding navigatin panel to mainContainer
        mainContainer.add(navPanel, BorderLayout.NORTH);

        //Pages panels
        newsPanel = new News();
        profilePanel = new Profile();
        peoplePanel = new People();
        gamePanel = new Game();

        //Setting borders with labels to pages panels
        newsPanel.setBorder(BorderFactory.createTitledBorder(null, "News", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        profilePanel.setBorder(BorderFactory.createTitledBorder(null, "Profile", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        peoplePanel.setBorder(BorderFactory.createTitledBorder(null, "Peoples", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        gamePanel.setBorder(BorderFactory.createTitledBorder(null, "Game", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));

        //Adding Background color to pages panels
        newsPanel.setBackground(Design.BACK_COLOR);
        profilePanel.setBackground(Design.BACK_COLOR);
        peoplePanel.setBackground(Design.BACK_COLOR);
        gamePanel.setBackground(Design.BACK_COLOR);

        //Adding pages to pages CardLayout Panel 
        pages = new JPanel(new CardLayout());
        pages.add(newsPanel, NEWS_PANEL);
        pages.add(profilePanel, PROFILE_PANEL);
        pages.add(peoplePanel, PEOPLES_PANEL);
        pages.add(gamePanel, GAME_PANEL);

        //Adding pages to mainContainer
        mainContainer.add(pages, BorderLayout.CENTER);

        //Adding mainContainer to the Frame
        super.add(mainContainer);
    }

    // This procedure is use to rechange fore/background colors after swaping 
    // font and back colors in ChangeTheme inner class below
    private void changeTheme() {
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Design.FONT_COLOR));
        navPanel.setBackground(Design.FONT_COLOR);

        newsButton.setBackground(Design.BACK_COLOR);
        newsButton.setForeground(Design.FONT_COLOR);
        profileButton.setBackground(Design.BACK_COLOR);
        profileButton.setForeground(Design.FONT_COLOR);
        peopleButton.setBackground(Design.BACK_COLOR);
        peopleButton.setForeground(Design.FONT_COLOR);
        gameButton.setBackground(Design.BACK_COLOR);
        gameButton.setForeground(Design.FONT_COLOR);
        themeButton.setBackground(Design.BACK_COLOR);
        themeButton.setForeground(Design.FONT_COLOR);
        logoutButton.setBackground(Design.BACK_COLOR);
        logoutButton.setForeground(Design.FONT_COLOR);
        logoutButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));

        newsPanel.setBorder(BorderFactory.createTitledBorder(null, "News", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        profilePanel.setBorder(BorderFactory.createTitledBorder(null, "Profile", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        peoplePanel.setBorder(BorderFactory.createTitledBorder(null, "Peoples", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        gamePanel.setBorder(BorderFactory.createTitledBorder(null, "Game", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));

        newsPanel.setBackground(Design.BACK_COLOR);
        profilePanel.setBackground(Design.BACK_COLOR);
        peoplePanel.setBackground(Design.BACK_COLOR);
        gamePanel.setBackground(Design.BACK_COLOR);

        newsPanel.changeTheme();
        profilePanel.changeTheme();
        peoplePanel.changeTheme();
        gamePanel.changeTheme();
    }

    public void refreshCore() throws SQLException, IOException {
        this.newsPanel.refreshNews();
        this.profilePanel.refreshProfile();
        this.peoplePanel.refreshPeoples();
    }

    private void logout() {
        this.setVisible(false);
        this.login.setVisible(true);
    }

    //Navigation listener class swaps between pages(cards) according to the clicked button
    private class Navigation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) (pages.getLayout());
            if (e.getSource() == newsButton) {
                try {
                    newsPanel.refreshNews();
                } catch (SQLException ex) {
                    Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
                }
                cl.show(pages, NEWS_PANEL);
            } else if (e.getSource() == profileButton) {
                try {
                    profilePanel.refreshProfile();
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
                }
                cl.show(pages, PROFILE_PANEL);
            } else if (e.getSource() == peopleButton) {
                try {
                    peoplePanel.refreshPeoples();
                } catch (SQLException ex) {
                    Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
                }
                cl.show(pages, PEOPLES_PANEL);
            } else if (e.getSource() == gameButton) {
                cl.show(pages, GAME_PANEL);
                gamePanel.requestFocus();
            } else if (e.getSource() == themeButton) {
                Color temp = Design.BACK_COLOR;
                Design.BACK_COLOR = Design.FONT_COLOR;
                Design.FONT_COLOR = temp;
                changeTheme();
            } else if (e.getSource() == logoutButton) {
                logout();
            }
        }
    }
}
