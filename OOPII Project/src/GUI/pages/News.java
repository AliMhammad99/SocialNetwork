package GUI.pages;

import DB.DataBaseConnection;
import DB.Status;
import design.Design;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class News extends JPanel {

    Component[] createRigidArea;
    private JButton addStatus;
    private JButton[] deleteStatus;
    private JButton[] editStatus;
    private JButton[] submit;
    private String[] friendName;
    private int[] statusId;
    private JTextArea[] status;
    private JLabel[] userName;
    private JLabel[] dateTime;
    private JPanel[] postPanel;
    private JPanel[] namePanel;
    private java.sql.Date[] sqlDate;
    private java.sql.Time[] sqlTime;
    private JPanel[] statusPanel;
    private JScrollPane sp;
    private JPanel mainPanel;
    private JPanel postsContainer;
    private JPanel insertPost;
    private JTextArea myPost;
    private JLabel typePost;
    private InsertStatus inStatus;
    private EditMyStatus editMyStatus;
    private DeleteMyStatus deleteMyStatus;
    private SubmitMyStatus submitMyStatus;
    private JLabel[] profilePicture;
    private int rowNumber;
    ArrayList<Status> posts;

    public News() throws SQLException {
        build();
        buildNewsFeed();
    }

    private void build() throws SQLException {
        inStatus = new InsertStatus();

        this.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(2, 2));

        insertPost = new JPanel();
        insertPost.setLayout(new FlowLayout());
        insertPost.setBackground(Design.BACK_COLOR);

        myPost = new JTextArea();
        myPost.setPreferredSize(new Dimension(500, 100));
        myPost.setLineWrap(true);
        myPost.setBackground(Design.FONT_COLOR);
        myPost.setForeground(Design.BACK_COLOR);

        typePost = new JLabel("Type your post:");
        typePost.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
        typePost.setForeground(Design.FONT_COLOR);

        //Declaring Add Status Button
        addStatus = new JButton("Add Status");
        addStatus.setForeground(Design.BACK_COLOR);
        addStatus.setBackground(Design.FONT_COLOR);
        addStatus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addStatus.addActionListener(inStatus);

        insertPost.add(typePost);
        insertPost.add(myPost);
        insertPost.add(addStatus);

        mainPanel.add(insertPost, BorderLayout.NORTH);

        mainPanel.setOpaque(false);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void buildNewsFeed() throws SQLException {
        posts = new ArrayList<>();
        
        sp = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.sp.setOpaque(false);
        this.sp.getViewport().setOpaque(false);
        this.sp.getVerticalScrollBar().setUnitIncrement(16);

        postsContainer = new JPanel();
        postsContainer.setOpaque(false);
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        
        posts = DataBaseConnection.getStatus();
        rowNumber = posts.size();
        
        createRigidArea = new Component[rowNumber];
        editStatus = new JButton[rowNumber];
        deleteStatus = new JButton[rowNumber];
        submit = new JButton[rowNumber];
        status = new JTextArea[rowNumber];
        userName = new JLabel[rowNumber];
        dateTime = new JLabel[rowNumber];
        postPanel = new JPanel[rowNumber];
        namePanel = new JPanel[rowNumber];
        sqlDate = new java.sql.Date[rowNumber];
        sqlTime = new java.sql.Time[rowNumber];
        statusPanel = new JPanel[rowNumber];
        friendName = new String[rowNumber];
        statusId = new int[rowNumber];
        profilePicture = new JLabel[rowNumber];
        
        for(int i=0; i<rowNumber; i++){
            statusId[i] = posts.get(i).getStatusId();
            friendName[i] = DataBaseConnection.getFriendName(posts.get(i).getUserAccountId());
            
            profilePicture[i] = new JLabel();
            profilePicture[i].setSize(50, 50);
            ImageIcon icon = new ImageIcon("images/" + DataBaseConnection.getProfilePicture(posts.get(i).getUserAccountId()));
            Image img = icon.getImage();
            int ratio = icon.getIconHeight() / this.profilePicture[i].getHeight();
            Image imgScale = img.getScaledInstance(icon.getIconWidth() / ratio, icon.getIconHeight() / ratio, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            profilePicture[i].setIcon(scaledIcon);

            sqlDate[i] = posts.get(i).getStatusDate();
            sqlTime[i] = posts.get(i).getStatusTime();
            dateTime[i] = new JLabel();
            dateTime[i].setText(new SimpleDateFormat("yyyy-MM-dd").format(sqlDate[i]) + " " + new SimpleDateFormat("HH:mm:ss").format(sqlTime[i]));
            dateTime[i].setForeground(Design.FONT_COLOR);
            dateTime[i].setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));

            postPanel[i] = new JPanel();
            postPanel[i].setLayout(new BorderLayout());
            postPanel[i].setOpaque(false);
            postPanel[i].setPreferredSize(new Dimension(400, 150));

            namePanel[i] = new JPanel();
            namePanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            namePanel[i].setBackground(Design.BACK_COLOR);
            namePanel[i].setSize(new Dimension(100, 30));

            userName[i] = new JLabel(friendName[i]);
            userName[i].setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));
            userName[i].setForeground(Design.FONT_COLOR);

            namePanel[i].add(profilePicture[i]);
            namePanel[i].add(userName[i]);
            namePanel[i].add(dateTime[i]);
            if (posts.get(i).getUserAccountId() == DataBaseConnection.SESSION) {
                //Declaring Edit Status Button
                editStatus[i] = new JButton("Edit Status");
                editStatus[i].setForeground(Design.BACK_COLOR);
                editStatus[i].setBackground(Design.FONT_COLOR);
                editStatus[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                editMyStatus = new EditMyStatus(i);
                editStatus[i].addActionListener(editMyStatus);
                namePanel[i].add(editStatus[i]);

                submit[i] = new JButton("Submit Status");
                submit[i].setForeground(Design.BACK_COLOR);
                submit[i].setBackground(Design.FONT_COLOR);
                submit[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                submitMyStatus = new SubmitMyStatus(i);
                submit[i].setVisible(false);
                submit[i].addActionListener(submitMyStatus);
                namePanel[i].add(submit[i]);

                deleteStatus[i] = new JButton("Delete Status");
                deleteStatus[i].setForeground(Design.BACK_COLOR);
                deleteStatus[i].setBackground(Design.FONT_COLOR);
                deleteStatus[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                deleteMyStatus = new DeleteMyStatus(i);
                deleteStatus[i].addActionListener(deleteMyStatus);
                namePanel[i].add(deleteStatus[i]);
            }

            status[i] = new JTextArea(4, 90);
            status[i].setLineWrap(true);
            status[i].setText(posts.get(i).getStatusText());
            status[i].setEditable(false);
            status[i].setBackground(Design.BACK_COLOR);
            status[i].setForeground(Design.FONT_COLOR);
            status[i].setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Design.FONT_COLOR));
            status[i].setCaretColor(Design.FONT_COLOR);
            status[i].setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_MEDIUM));

            statusPanel[i] = new JPanel();
            statusPanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            statusPanel[i].setOpaque(false);
            statusPanel[i].setPreferredSize(new Dimension(400, 150));
            statusPanel[i].add(status[i]);

            postPanel[i].add(namePanel[i], BorderLayout.NORTH);
            postPanel[i].add(statusPanel[i], BorderLayout.CENTER);

            createRigidArea[i] = Box.createRigidArea(new Dimension(0, 10));
            postsContainer.add(createRigidArea[i]);
            postsContainer.add(postPanel[i]);
            
            postsContainer.add(Box.createVerticalStrut(20));
        }
                
        mainPanel.add(postsContainer, BorderLayout.CENTER);
        this.add(sp);
    }

    public News getNews() {
        return this;
    }

    public void refreshNews() throws SQLException {
        this.removeAll();
        this.build();
        this.buildNewsFeed();
        this.revalidate();
        this.repaint();
    }

    public void changeTheme() {
        insertPost.setBackground(Design.BACK_COLOR);

        myPost.setBackground(Design.FONT_COLOR);
        myPost.setForeground(Design.BACK_COLOR);

        typePost.setForeground(Design.FONT_COLOR);

        addStatus.setForeground(Design.BACK_COLOR);
        addStatus.setBackground(Design.FONT_COLOR);

        for (int i = 0; i < rowNumber; i++) {
            dateTime[i].setForeground(Design.FONT_COLOR);

            namePanel[i].setBackground(Design.BACK_COLOR);

            userName[i].setForeground(Design.FONT_COLOR);

            if (editStatus[i] != null) {
                editStatus[i].setForeground(Design.BACK_COLOR);
                editStatus[i].setBackground(Design.FONT_COLOR);

                submit[i].setForeground(Design.BACK_COLOR);
                submit[i].setBackground(Design.FONT_COLOR);

                deleteStatus[i].setForeground(Design.BACK_COLOR);
                deleteStatus[i].setBackground(Design.FONT_COLOR);
            }

            status[i].setBackground(Design.BACK_COLOR);
            status[i].setForeground(Design.FONT_COLOR);
            status[i].setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Design.FONT_COLOR));
            status[i].setCaretColor(Design.FONT_COLOR);
        }
    }

    public class InsertStatus implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (myPost.getText() == null || "".equals(myPost.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Type text of Post to add it!",
                            "ADD POST WARNING",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String myStatus = myPost.getText();
                    myStatus = myStatus.replaceAll("'", "");
                    int res2 = DataBaseConnection.insertToPosts(myStatus);
                    if (res2 > 0) {
                        getNews().refreshNews();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Problem occured while adding post!",
                                "ADD POST WARNING",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public class EditMyStatus implements ActionListener {

        int i;

        public EditMyStatus(int i) {
            this.i = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            status[i].setEditable(true);
            submit[i].setVisible(true);
        }

    }

    public class SubmitMyStatus implements ActionListener {

        int i;

        public SubmitMyStatus(int i) {
            this.i = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DataBaseConnection.editMyStatus(status[i].getText(),statusId[i]);
                status[i].setEditable(false);
                submit[i].setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public class DeleteMyStatus implements ActionListener {

        int i;

        public DeleteMyStatus(int i) {
            this.i = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int input = JOptionPane.showConfirmDialog(null,
                        "Do you want to delete this post?",
                        "Delete Post", JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == 0) {
                    int result = DataBaseConnection.deleteStatus(statusId[i]);
                    postsContainer.remove(createRigidArea[i]);
                    postsContainer.remove(postPanel[i]);
                    postsContainer.revalidate();
                    postsContainer.repaint();
                    if (result != 0) {
                        JOptionPane.showMessageDialog(null,
                                "Post Deleted!",
                                "Delete POST",
                                JOptionPane.INFORMATION_MESSAGE);
                        myPost.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Problem occured while deleting your post!",
                                "DELETE POST WARNING",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Post was not Deleted",
                            "Delete POST",
                            JOptionPane.INFORMATION_MESSAGE);
                    myPost.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
