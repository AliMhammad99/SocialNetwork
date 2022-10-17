package GUI.pages;

import DB.Account;
import DB.DataBaseConnection;
import static DB.DataBaseConnection.*;
import design.Design;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class People extends JPanel {

    private JPanel peoplePanel;
    private JPanel notificationsPanel;

    private JScrollPane peopleScrollPane;
    private JScrollPane notificationsScrollPane;

    private ArrayList<Person> persons;
    private ArrayList<Notification> notifications;

    private JFrame personProfile;

    public People() throws SQLException {
        build();
    }

    private void build() throws SQLException {
        this.setLayout(new GridLayout(1, 2, 5, 5));

        this.peoplePanel = new JPanel();
        this.peoplePanel.setLayout(new BoxLayout(this.peoplePanel, BoxLayout.Y_AXIS));
        this.peoplePanel.setOpaque(false);

        this.notificationsPanel = new JPanel();
        this.notificationsPanel.setLayout(new BoxLayout(this.notificationsPanel, BoxLayout.Y_AXIS));
        this.notificationsPanel.setOpaque(false);

        this.peopleScrollPane = new JScrollPane(this.peoplePanel);
        this.peopleScrollPane.setOpaque(false);
        this.peopleScrollPane.getViewport().setOpaque(false);
        this.peopleScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.peopleScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Peoples you may know", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));

        this.notificationsScrollPane = new JScrollPane(this.notificationsPanel);
        this.notificationsScrollPane.setOpaque(false);
        this.notificationsScrollPane.getViewport().setOpaque(false);
        this.notificationsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.notificationsScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Notifications", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));

        this.persons = new ArrayList<>();
        this.notifications = new ArrayList<>();

        this.personProfile = new JFrame();
        this.personProfile.setTitle("Friend Profile");
        this.personProfile.setSize(400, 400);
        this.personProfile.setResizable(false);
        this.personProfile.setLocationRelativeTo(this);
        this.personProfile.setResizable(false);
        this.personProfile.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.personProfile.setLayout(new BoxLayout(this.personProfile.getContentPane(), BoxLayout.Y_AXIS));
        this.personProfile.setVisible(true);
        this.personProfile.getContentPane().setBackground(Design.BACK_COLOR);
        this.personProfile.setVisible(false);

        this.add(this.peopleScrollPane);
        this.add(this.notificationsScrollPane);

        fillPersons();
        fillNotifications();
    }

    private void fillPersons() throws SQLException {

        ArrayList<Account> accounts = getPersons(DataBaseConnection.SESSION);
        accounts.forEach(account -> {

            boolean requestIsSent = false;
            try {
                requestIsSent = requestIsSentTo(account.getAccountId());
            } catch (SQLException ex) {
                Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
            }

            Person person = new Person(account.getAccountId(), requestIsSent, account.getProfilePicture(),
                    account.getFirstName() + " " + account.getLastName());
            person.setAlignmentX(Component.LEFT_ALIGNMENT);
            persons.add(person);
            this.peoplePanel.add(person);
            this.peoplePanel.add(Box.createVerticalStrut(10));
        });
    }

    private void fillNotifications() throws SQLException {

        ArrayList<Integer> accountsIds = getNotifications(DataBaseConnection.SESSION);
        accountsIds.forEach(accountId -> {
            Account account = null;
            try {
                account = getAccount(accountId);
            } catch (SQLException ex) {
                Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (account != null) {
                Notification notification = new Notification(account.getAccountId(),
                        account.getProfilePicture(), account.getFirstName() + " " + account.getLastName());
                notification.setAlignmentX(Component.LEFT_ALIGNMENT);
                this.notifications.add(notification);
                this.notificationsPanel.add(notification);
                this.notificationsPanel.add(Box.createVerticalStrut(10));
            }
        });
    }

    private People getPeoples() {
        return this;
    }

    public void refreshPeoples() throws SQLException {
        this.removeAll();
        this.build();
        this.revalidate();
        this.repaint();
    }

    public void changeTheme() {
        this.peopleScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Peoples you may know", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        this.notificationsScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Notifications", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));
        for (int i = 0; i < this.persons.size(); i++) {
            this.persons.get(i).changeTheme();
        }
        for (int i = 0; i < this.notifications.size(); i++) {
            this.notifications.get(i).changeTheme();
        }
    }

    private class Person extends JPanel {

        private final int personId;
        private boolean requestIsSent;
        private final JLabel profilePic;
        private final JLabel personName;
        private final JButton showProfileBtn;
        private final JButton addFriendBtn;

        public Person(int personId, boolean requestIsSent, String profilePic, String personName) {
            this.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Design.FONT_COLOR));
            this.setOpaque(false);
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            this.personId = personId;
            this.requestIsSent = requestIsSent;
            this.profilePic = new JLabel();
            this.profilePic.setSize(50, 50);
            ImageIcon icon = new ImageIcon("images/" + profilePic);
            Image img = icon.getImage();
            int ratio = icon.getIconHeight() / this.profilePic.getHeight();

            Image imgScale = img.getScaledInstance(icon.getIconWidth() / ratio, icon.getIconHeight() / ratio, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            this.profilePic.setIcon(scaledIcon);

            this.add(Box.createHorizontalStrut(10));
            this.add(this.profilePic);

            this.personName = new JLabel(personName);
            this.personName.setForeground(Design.FONT_COLOR);
            this.personName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
            this.add(Box.createHorizontalStrut(10));
            this.add(this.personName);

            this.showProfileBtn = new JButton("Show Profile");
            this.showProfileBtn.setForeground(Design.BACK_COLOR);
            this.showProfileBtn.setBackground(Design.FONT_COLOR);
            this.showProfileBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.showProfileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.showProfileBtn.addActionListener(new PersonAction());
            this.add(Box.createHorizontalStrut(50));
            this.add(this.showProfileBtn);

            this.addFriendBtn = new JButton("Add Friend");
            if (requestIsSent) {
                this.addFriendBtn.setText("Cancel Request");
            }
            this.addFriendBtn.setForeground(Design.BACK_COLOR);
            this.addFriendBtn.setBackground(Design.FONT_COLOR);
            this.addFriendBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.addFriendBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.addFriendBtn.addActionListener(new PersonAction());
            this.add(Box.createHorizontalStrut(10));
            this.add(this.addFriendBtn);

            this.add(Box.createHorizontalStrut(10));
        }

        private void showProfile() throws SQLException {
            personProfile.getContentPane().removeAll();
            personProfile.getContentPane().setBackground(Design.BACK_COLOR);

            JLabel profilePic1 = new JLabel();
            profilePic1.setSize(100, 100);
            profilePic1.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel firstName = new JLabel();
            firstName.setAlignmentX(Component.CENTER_ALIGNMENT);
            firstName.setForeground(Design.FONT_COLOR);
            firstName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            JLabel lastName = new JLabel();
            lastName.setAlignmentX(Component.CENTER_ALIGNMENT);
            lastName.setForeground(Design.FONT_COLOR);
            lastName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            JLabel gender = new JLabel();
            gender.setAlignmentX(Component.CENTER_ALIGNMENT);
            gender.setForeground(Design.FONT_COLOR);
            gender.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            JLabel dob = new JLabel();
            dob.setAlignmentX(Component.CENTER_ALIGNMENT);
            dob.setForeground(Design.FONT_COLOR);
            dob.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            JLabel creationDate = new JLabel();
            creationDate.setAlignmentX(Component.CENTER_ALIGNMENT);
            creationDate.setForeground(Design.FONT_COLOR);
            creationDate.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            Account account = getAccount(this.personId);
            ImageIcon icon = new ImageIcon("images/" + account.getProfilePicture());
            Image img = icon.getImage();
            int ratio = icon.getIconHeight() / profilePic1.getHeight();
            Image imgScale = img.getScaledInstance(icon.getIconWidth() / ratio, icon.getIconHeight() / ratio, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            profilePic1.setIcon(scaledIcon);

            firstName.setText("First Name: " + account.getFirstName());
            lastName.setText("Last Name: " + account.getLastName());
            gender.setText("Gender: " + account.getGender());
            dob.setText("Birthday: " + account.getDateOfBirth());
            creationDate.setText("Join Date: " + account.getCreationDateTime());

            personProfile.setTitle(account.getFirstName() + "'s" + " Profile");
            personProfile.add(Box.createVerticalStrut(50));
            personProfile.add(profilePic1);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(firstName);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(lastName);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(gender);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(dob);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(creationDate);

            personProfile.repaint();
            personProfile.setVisible(true);
        }

        private void friendRequest() throws SQLException {
            insertFriendRequest(DataBaseConnection.SESSION, this.personId);
            this.requestIsSent = true;
            this.addFriendBtn.setText("Cancel Request");
        }

        private void cancelRequest() throws SQLException {
            deleteFriendRequest(DataBaseConnection.SESSION, this.personId);
            this.requestIsSent = false;
            this.addFriendBtn.setText("Add Friend");
        }

        private void changeTheme() {
            this.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Design.FONT_COLOR));
            this.personName.setForeground(Design.FONT_COLOR);
            this.personName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
            this.showProfileBtn.setForeground(Design.BACK_COLOR);
            this.showProfileBtn.setBackground(Design.FONT_COLOR);
            this.showProfileBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.showProfileBtn.setForeground(Design.BACK_COLOR);
            this.showProfileBtn.setBackground(Design.FONT_COLOR);
            this.showProfileBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.addFriendBtn.setForeground(Design.BACK_COLOR);
            this.addFriendBtn.setBackground(Design.FONT_COLOR);
            this.addFriendBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        }

        private class PersonAction implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == showProfileBtn) {
                    try {
                        showProfile();
                    } catch (SQLException ex) {
                        Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getSource() == addFriendBtn) {
                    try {
                        if (!requestIsSent) {
                            friendRequest();
                        } else {
                            cancelRequest();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private class Notification extends JPanel {

        private final int personId;
        private final JLabel profilePic;
        private final JLabel personName;
        private final JButton showProfileBtn;
        private final JButton acceptBtn;
        private final JButton rejectBtn;

        public Notification(int personId, String profilePic, String personName) {
            this.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Design.FONT_COLOR));
            this.setOpaque(false);
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            this.personId = personId;
            this.profilePic = new JLabel();
            this.profilePic.setSize(50, 50);
            ImageIcon icon = new ImageIcon("images/" + profilePic);
            Image img = icon.getImage();
            int ratio = icon.getIconHeight() / this.profilePic.getHeight();

            Image imgScale = img.getScaledInstance(icon.getIconWidth() / ratio, icon.getIconHeight() / ratio, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            this.profilePic.setIcon(scaledIcon);

            this.add(Box.createHorizontalStrut(10));
            this.add(this.profilePic);

            this.personName = new JLabel(personName);
            this.personName.setForeground(Design.FONT_COLOR);
            this.personName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
            this.add(Box.createHorizontalStrut(10));
            this.add(this.personName);

            this.showProfileBtn = new JButton("Show Profile");
            this.showProfileBtn.setForeground(Design.BACK_COLOR);
            this.showProfileBtn.setBackground(Design.FONT_COLOR);
            this.showProfileBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.showProfileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.showProfileBtn.addActionListener(new NotificationAction());
            this.add(Box.createHorizontalStrut(50));
            this.add(this.showProfileBtn);

            this.acceptBtn = new JButton("Accept");
            this.acceptBtn.setForeground(Design.BACK_COLOR);
            this.acceptBtn.setBackground(Design.FONT_COLOR);
            this.acceptBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.acceptBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.acceptBtn.addActionListener(new NotificationAction());
            this.add(Box.createHorizontalStrut(10));
            this.add(this.acceptBtn);

            this.rejectBtn = new JButton("Not Now");
            this.rejectBtn.setForeground(Design.BACK_COLOR);
            this.rejectBtn.setBackground(Design.FONT_COLOR);
            this.rejectBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.rejectBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.rejectBtn.addActionListener(new NotificationAction());
            this.add(Box.createHorizontalStrut(10));
            this.add(this.rejectBtn);

            this.add(Box.createHorizontalStrut(10));
        }

        private void showProfile() throws SQLException {
            personProfile.getContentPane().removeAll();
            personProfile.getContentPane().setBackground(Design.BACK_COLOR);

            JLabel profilePic1 = new JLabel();
            profilePic1.setSize(100, 100);
            profilePic1.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel firstName = new JLabel();
            firstName.setAlignmentX(Component.CENTER_ALIGNMENT);
            firstName.setForeground(Design.FONT_COLOR);
            firstName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            JLabel lastName = new JLabel();
            lastName.setAlignmentX(Component.CENTER_ALIGNMENT);
            lastName.setForeground(Design.FONT_COLOR);
            lastName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            JLabel gender = new JLabel();
            gender.setAlignmentX(Component.CENTER_ALIGNMENT);
            gender.setForeground(Design.FONT_COLOR);
            gender.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            JLabel dob = new JLabel();
            dob.setAlignmentX(Component.CENTER_ALIGNMENT);
            dob.setForeground(Design.FONT_COLOR);
            dob.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            JLabel creationDate = new JLabel();
            creationDate.setAlignmentX(Component.CENTER_ALIGNMENT);
            creationDate.setForeground(Design.FONT_COLOR);
            creationDate.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            Account account = getAccount(this.personId);
            ImageIcon icon = new ImageIcon("images/" + account.getProfilePicture());
            Image img = icon.getImage();
            int ratio = icon.getIconHeight() / profilePic1.getHeight();
            Image imgScale = img.getScaledInstance(icon.getIconWidth() / ratio, icon.getIconHeight() / ratio, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            profilePic1.setIcon(scaledIcon);

            firstName.setText("First Name: " + account.getFirstName());
            lastName.setText("Last Name: " + account.getLastName());
            gender.setText("Gender: " + account.getGender());
            dob.setText("Birthday: " + account.getDateOfBirth());
            creationDate.setText("Join Date: " + account.getCreationDateTime());

            personProfile.setTitle(account.getFirstName() + "'s" + " Profile");
            personProfile.add(Box.createVerticalStrut(50));
            personProfile.add(profilePic1);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(firstName);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(lastName);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(gender);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(dob);
            personProfile.add(Box.createVerticalStrut(10));
            personProfile.add(creationDate);

            personProfile.repaint();
            personProfile.setVisible(true);
        }

        private void acceptRequest() throws SQLException {
            insertAcceptRequest(this.personId, DataBaseConnection.SESSION);
            Thread refresh = new Thread() {
                @Override
                public void run() {
                    try {
                        getPeoples().refreshPeoples();
                    } catch (SQLException ex) {
                        Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            refresh.start();
        }

        private void rejectRequest() throws SQLException {
            insertRejectRequest(this.personId, DataBaseConnection.SESSION);
            getPeoples().refreshPeoples();
            Thread refresh = new Thread() {
                @Override
                public void run() {
                    try {
                        getPeoples().refreshPeoples();
                    } catch (SQLException ex) {
                        Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            refresh.start();
        }

        private void changeTheme() {
            this.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Design.FONT_COLOR));
            this.personName.setForeground(Design.FONT_COLOR);
            this.personName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
            this.showProfileBtn.setForeground(Design.BACK_COLOR);
            this.showProfileBtn.setBackground(Design.FONT_COLOR);
            this.showProfileBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.acceptBtn.setForeground(Design.BACK_COLOR);
            this.acceptBtn.setBackground(Design.FONT_COLOR);
            this.acceptBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.rejectBtn.setForeground(Design.BACK_COLOR);
            this.rejectBtn.setBackground(Design.FONT_COLOR);
            this.rejectBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        }

        private class NotificationAction implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == showProfileBtn) {
                    try {
                        showProfile();
                    } catch (SQLException ex) {
                        Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getSource() == acceptBtn) {
                    try {
                        acceptRequest();
                    } catch (SQLException ex) {
                        Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getSource() == rejectBtn) {
                    try {
                        rejectRequest();
                    } catch (SQLException ex) {
                        Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
