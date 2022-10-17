package GUI.pages;

import DB.Account;
import DB.DataBaseConnection;
import static DB.DataBaseConnection.*;
import GUI.EditProfile;
import design.Design;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Profile extends JPanel {

    private JPanel topPanel;
    private JButton editProfile;
    private JLabel profilePicture;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel email;
    private JLabel gender;
    private JLabel dob;
    private JPanel friendsPanel;
    private ArrayList<Friend> friends;
    private JScrollPane friendsScrollPane;
    private JFrame friendProfile;
    private JFrame editProfileFrame;

    public Profile() throws SQLException, IOException {
        build();
    }

    private void build() throws SQLException, IOException {

        this.setLayout(new BorderLayout());

        this.profilePicture = new JLabel();
        this.profilePicture.setSize(100, 100);

        this.editProfile = new JButton("Edit Profile");
        this.editProfile.setForeground(Design.BACK_COLOR);
        this.editProfile.setBackground(Design.FONT_COLOR);
        this.editProfile.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.editProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.editProfile.addActionListener(new EditProfileListener());

        this.firstName = new JLabel("First Name: ");
        this.firstName.setForeground(Design.FONT_COLOR);
        this.firstName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

        this.lastName = new JLabel("Last Name: ");
        this.lastName.setForeground(Design.FONT_COLOR);
        this.lastName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

        this.email = new JLabel("Email: ");
        this.email.setForeground(Design.FONT_COLOR);
        this.email.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

        this.gender = new JLabel("Gender: ");
        this.gender.setForeground(Design.FONT_COLOR);
        this.gender.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

        this.dob = new JLabel("Birthday: ");
        this.dob.setForeground(Design.FONT_COLOR);
        this.dob.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

        this.topPanel = new JPanel();
        this.topPanel.setLayout(new BoxLayout(this.topPanel, BoxLayout.Y_AXIS));
        this.topPanel.setOpaque(false);

        this.topPanel.add(Box.createVerticalStrut(10));
        this.topPanel.add(this.profilePicture);
        this.topPanel.add(Box.createVerticalStrut(10));
        this.topPanel.add(this.editProfile);
        this.topPanel.add(Box.createVerticalStrut(10));
        this.topPanel.add(Box.createVerticalStrut(10));
        this.topPanel.add(this.firstName);
        this.topPanel.add(Box.createVerticalStrut(10));
        this.topPanel.add(this.lastName);
        this.topPanel.add(Box.createVerticalStrut(10));
        this.topPanel.add(this.email);
        this.topPanel.add(Box.createVerticalStrut(10));
        this.topPanel.add(this.gender);
        this.topPanel.add(Box.createVerticalStrut(10));
        this.topPanel.add(this.dob);
        this.topPanel.add(Box.createVerticalStrut(10));
        this.topPanel.add(Box.createHorizontalStrut(3));

        this.add(this.topPanel, BorderLayout.NORTH);

        this.friendsPanel = new JPanel();
        this.friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        this.friendsPanel.setOpaque(false);

        this.friends = new ArrayList<>();

        this.friendsScrollPane = new JScrollPane(friendsPanel);
        this.friendsScrollPane.setOpaque(false);
        this.friendsScrollPane.getViewport().setOpaque(false);
        this.friendsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.friendsScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Friends", TitledBorder.LEFT,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM), Design.FONT_COLOR));

        this.add(this.friendsScrollPane, BorderLayout.CENTER);

        this.friendProfile = new JFrame();
        this.friendProfile.setTitle("Friend Profile");
        this.friendProfile.setSize(400, 400);
        this.friendProfile.setResizable(false);
        this.friendProfile.setLocationRelativeTo(this);
        this.friendProfile.setResizable(false);
        this.friendProfile.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.friendProfile.setLayout(new BoxLayout(this.friendProfile.getContentPane(), BoxLayout.Y_AXIS));
        this.friendProfile.setVisible(true);
        this.friendProfile.getContentPane().setBackground(Design.BACK_COLOR);
        this.friendProfile.setVisible(false);

        this.editProfileFrame = new EditProfile(this);
        this.editProfileFrame.setLocationRelativeTo(this);
        this.editProfileFrame.setVisible(false);

        fillData();
        fillFriends();
    }

    private void fillData() throws SQLException {
        Account account = getAccount(DataBaseConnection.SESSION);

        this.firstName.setText("First Name: " + account.getFirstName());
        this.lastName.setText("Last Name: " + account.getLastName());
        this.email.setText("Email: " + account.getEmail());
        this.gender.setText("Gender: " + account.getGender());
        this.dob.setText("Birthday: " + account.getDateOfBirth());
        ImageIcon icon = new ImageIcon("images/" + account.getProfilePicture());

        Image img = icon.getImage();
        if (this.profilePicture.getHeight() != 0) {
            int ratio = icon.getIconHeight() / this.profilePicture.getHeight();
            Image imgScale = img.getScaledInstance(icon.getIconWidth() / ratio, icon.getIconHeight() / ratio, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            this.profilePicture.setIcon(scaledIcon);
        }
    }

    private void fillFriends() throws SQLException {

        ArrayList<Integer> friendsIds = getFriends(DataBaseConnection.SESSION);

        for (int i = 0; i < friendsIds.size(); i++) {
            Account account = getAccount(friendsIds.get(i));
            Friend friend = new Friend(account.getAccountId(), account.getProfilePicture(),
                    account.getFirstName() + " " + account.getLastName());
            friend.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.friendsPanel.add(friend);
            this.friendsPanel.add(Box.createVerticalStrut(10));
            this.friends.add(friend);
        }
    }

    public void changeTheme() {
        this.editProfile.setForeground(Design.BACK_COLOR);
        this.editProfile.setBackground(Design.FONT_COLOR);
        this.firstName.setForeground(Design.FONT_COLOR);
        this.lastName.setForeground(Design.FONT_COLOR);
        this.email.setForeground(Design.FONT_COLOR);
        this.gender.setForeground(Design.FONT_COLOR);
        this.dob.setForeground(Design.FONT_COLOR);
        this.friendsScrollPane.setBorder(BorderFactory.createTitledBorder(null, "Friends", TitledBorder.LEFT,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM), Design.FONT_COLOR));
        for (int i = 0; i < this.friends.size(); i++) {
            this.friends.get(i).changeTheme();
        }
    }

    private Profile getProfile() {
        return this;
    }

    public void refreshProfile() throws SQLException, IOException {
        this.editProfileFrame.dispatchEvent(new WindowEvent(this.editProfileFrame, WindowEvent.WINDOW_CLOSING));
        this.removeAll();
        this.build();
        this.revalidate();
        this.repaint();
    }

    private class EditProfileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getProfile().editProfileFrame.setVisible(!getProfile().editProfileFrame.isVisible());
        }

    }

    private class Friend extends JPanel {

        private final int friendId;
        private final JLabel profilePic;
        private final JLabel friendName;
        private final JButton unfriendBtn;
        private final JButton showProfileBtn;

        public Friend(int friendId, String profilePic, String friendName) {
            this.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Design.FONT_COLOR));
            this.setOpaque(false);
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            this.friendId = friendId;

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

            this.friendName = new JLabel(friendName);
            this.friendName.setForeground(Design.FONT_COLOR);
            this.friendName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
            this.add(Box.createHorizontalStrut(10));
            this.add(this.friendName);

            this.showProfileBtn = new JButton("Show Profile");
            this.showProfileBtn.setForeground(Design.BACK_COLOR);
            this.showProfileBtn.setBackground(Design.FONT_COLOR);
            this.showProfileBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.showProfileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.showProfileBtn.addActionListener(new FriendAction());
            this.add(Box.createHorizontalStrut(50));
            this.add(this.showProfileBtn);

            this.unfriendBtn = new JButton("Unfriend");
            this.unfriendBtn.setForeground(Design.BACK_COLOR);
            this.unfriendBtn.setBackground(Design.FONT_COLOR);
            this.unfriendBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.unfriendBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.unfriendBtn.addActionListener(new FriendAction());
            this.add(Box.createHorizontalStrut(10));
            this.add(this.unfriendBtn);

            this.add(Box.createHorizontalStrut(10));
        }

        private void showProfile() throws SQLException {
            friendProfile.getContentPane().removeAll();
            friendProfile.getContentPane().setBackground(Design.BACK_COLOR);

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

            Account account = getAccount(this.friendId);

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

            friendProfile.setTitle(account.getFirstName() + "'s" + " Profile");
            friendProfile.add(Box.createVerticalStrut(50));
            friendProfile.add(profilePic1);
            friendProfile.add(Box.createVerticalStrut(10));
            friendProfile.add(firstName);
            friendProfile.add(Box.createVerticalStrut(10));
            friendProfile.add(lastName);
            friendProfile.add(Box.createVerticalStrut(10));
            friendProfile.add(gender);
            friendProfile.add(Box.createVerticalStrut(10));
            friendProfile.add(dob);
            friendProfile.add(Box.createVerticalStrut(10));
            friendProfile.add(creationDate);

            friendProfile.repaint();
            friendProfile.setVisible(true);
        }

        private void unfriend() throws SQLException {
            int input = JOptionPane.showConfirmDialog(null, "Do you want to unfriend " + this.friendName.getText() + " ?",
                    "Unfriend", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (input == 0) {
                deleteFriend(this.friendId);
            }
            friendsPanel.removeAll();
            getProfile().fillFriends();
            friendsPanel.revalidate();
            getProfile().repaint();
        }

        private void changeTheme() {
            this.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Design.FONT_COLOR));
            this.friendName.setForeground(Design.FONT_COLOR);
            this.friendName.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
            this.showProfileBtn.setForeground(Design.BACK_COLOR);
            this.showProfileBtn.setBackground(Design.FONT_COLOR);
            this.showProfileBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.showProfileBtn.setForeground(Design.BACK_COLOR);
            this.showProfileBtn.setBackground(Design.FONT_COLOR);
            this.showProfileBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
            this.unfriendBtn.setForeground(Design.BACK_COLOR);
            this.unfriendBtn.setBackground(Design.FONT_COLOR);
            this.unfriendBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        }

        private class FriendAction implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == showProfileBtn) {
                    try {
                        showProfile();
                    } catch (SQLException ex) {
                        Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getSource() == unfriendBtn) {
                    try {
                        unfriend();
                    } catch (SQLException ex) {
                        Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
