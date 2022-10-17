package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class DataBaseConnection {

    public static final String DB_NAME = "socialnetwork";
    public static final String DB_USER_NAME = "root";
    public static final String DB_PASSWORD = "password";

    public static Connection conn;
    public static int SESSION;

    public static Connection connect(String dbName, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/" + dbName;
        Connection con = DriverManager.getConnection(url, user, password);
        if (con == null) {
            System.out.println("* User " + user + " Does Not Exist");
        } else {
            System.out.println("* User " + user + " Exists, Connection Established with " + dbName);
        }
        return con;
    }

    public static Account getAccount(String email, String password) throws SQLException {
        Statement stmt = DataBaseConnection.conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from account "
                + "where email = '" + email + "' and password = '" + password + "'");

        if (res.next()) {
            Account account = new Account(res.getInt("accountId"),
                    res.getString("firstName"), res.getString("lastName"),
                    res.getString("email"), res.getString("password"),
                    res.getString("gender"), res.getDate("dateOfBirth"),
                    res.getString("creationDateTime"), res.getString("profilePicture"),
                    res.getInt("gameHighScore"));
            res.close();
            stmt.close();
            return account;
        } else {
            res.close();
            stmt.close();
            return null;
        }
    }

    public static Account getAccount(int accountId) throws SQLException {
        Statement stmt = DataBaseConnection.conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from account where accountId = " + accountId);

        if (res.next()) {
            Account account = new Account(res.getInt("accountId"),
                    res.getString("firstName"), res.getString("lastName"),
                    res.getString("email"), res.getString("password"),
                    res.getString("gender"), res.getDate("dateOfBirth"),
                    res.getString("creationDateTime"), res.getString("profilePicture"),
                    res.getInt("gameHighScore"));
            res.close();
            stmt.close();
            return account;
        } else {
            res.close();
            stmt.close();
            return null;
        }
    }

    public static boolean emailExists(String email) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement("select accountId from account where email = ?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            rs.close();
            stmt.close();
            return true;
        } else {
            rs.close();
            stmt.close();
            return false;
        }
    }

    public static boolean insertAccount(String firstName, String lastName, String email, String password,
            String gender, java.sql.Date dateOfBirth, String profilePicture) throws SQLException {
        Statement stmt = DataBaseConnection.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        int res = stmt.executeUpdate("insert into account (firstName, lastName, email, password, gender, dateOfBirth, profilePicture)"
                + " values ('" + firstName + "','" + lastName
                + "','" + email + "','" + password
                + "','" + gender + "','" + dateOfBirth
                + "','" + profilePicture + "')");
        if (res > 0) {
            stmt.close();
            return true;
        } else {
            stmt.close();
            return false;
        }
    }

    public static int getAccountId(String email) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement("select accountId from account where email = ?");
        stmt.setString(1, email);
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            int accountId = res.getInt("accountId");
            res.close();
            stmt.close();
            return accountId;
        } else {
            res.close();
            stmt.close();
            return -1;
        }
    }

    public static void updateFirstName(String firstName) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement("update account set firstName = ?"
                + " where accountId = ?");
        stmt.setString(1, firstName);
        stmt.setInt(2, DataBaseConnection.SESSION);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateLastName(String lastName) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement("update account set lastName = ?"
                + " where accountId = ?");
        stmt.setString(1, lastName);
        stmt.setInt(2, DataBaseConnection.SESSION);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateEmail(String email) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement("update account set email = ?"
                + " where accountId = ?");
        stmt.setString(1, email);
        stmt.setInt(2, DataBaseConnection.SESSION);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void updatePassword(String password) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement("update account set password = ?"
                + " where accountId = ?");
        stmt.setString(1, password);
        stmt.setInt(2, DataBaseConnection.SESSION);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateGender(String gender) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement("update account set gender = ?"
                + " where accountId = ?");
        stmt.setString(1, gender);
        stmt.setInt(2, DataBaseConnection.SESSION);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateDateOfBirth(java.sql.Date dob) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement("update account set dateOfBirth = ?"
                + " where accountId = ?");
        stmt.setDate(1, dob);
        stmt.setInt(2, DataBaseConnection.SESSION);
        stmt.executeUpdate();
        stmt.close();
    }

    public static String getProfilePicture(int accountId) throws SQLException {
        Statement stmt = DataBaseConnection.conn.createStatement();
        ResultSet res = stmt.executeQuery("select profilePicture from account where accountId = " + accountId);
        res.next();
        String profilePicture = res.getString("profilePicture");
        res.close();
        stmt.close();
        return profilePicture;
    }

    public static void updateProfilePicture(String profilePicture) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement("update account set profilePicture = ?"
                + " where accountId = ?");
        stmt.setString(1, profilePicture);
        stmt.setInt(2, DataBaseConnection.SESSION);
        stmt.executeUpdate();
        stmt.close();
    }

    public static ArrayList<Integer> getFriends(int accountId) throws SQLException {
        ArrayList<Integer> friendsIds = new ArrayList<>();
        Statement stmt = DataBaseConnection.conn.createStatement();
        ResultSet res1 = stmt.executeQuery("select accountIdReceiver from friend"
                + " where accountIdSender = " + accountId
                + " and accepted = 1");
        while (res1.next()) {
            friendsIds.add(res1.getInt("accountIdReceiver"));
        }
        res1.close();
        ResultSet res2 = stmt.executeQuery("select accountIdSender from friend"
                + " where accountIdReceiver = " + DataBaseConnection.SESSION
                + " and accepted = 1");
        while (res2.next()) {
            friendsIds.add(res2.getInt("accountIdSender"));
        }
        res2.close();
        stmt.close();
        Set<Integer> set = new LinkedHashSet<>();

        set.addAll(friendsIds);

        friendsIds.clear();

        friendsIds.addAll(set);

        return friendsIds;
    }

    private static boolean isFriend(int accountId) throws SQLException {
        boolean isFriend = false;
        Statement stmt = DataBaseConnection.conn.createStatement();
        ResultSet res1 = stmt.executeQuery("select * from friend"
                + " where accountIdSender = " + accountId
                + " and accountIdReceiver = " + DataBaseConnection.SESSION
                + " and accepted = 1");
        if (res1.next()) {
            isFriend = true;
        }
        res1.close();
        ResultSet res2 = stmt.executeQuery("select * from friend"
                + " where accountIdSender = " + DataBaseConnection.SESSION
                + " and accountIdReceiver = " + accountId
                + " and accepted = 1");
        if (res2.next()) {
            isFriend = true;
        }
        res2.close();
        stmt.close();
        return isFriend;
    }

    public static ArrayList<Account> getFriendsScores() throws SQLException {
        ArrayList<Account> scores = new ArrayList<>();
        Statement stmt = DataBaseConnection.conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from account order by gameHighScore desc");
        while (res.next()) {
            if (isFriend(res.getInt("accountId")) || res.getInt("accountId") == DataBaseConnection.SESSION) {
                Account account = new Account(res.getInt("accountId"),
                        res.getString("firstName"), res.getString("lastName"),
                        res.getString("email"), res.getString("password"),
                        res.getString("gender"), res.getDate("dateOfBirth"),
                        res.getString("creationDateTime"), res.getString("profilePicture"),
                        res.getInt("gameHighScore"));
                scores.add(account);
            }
        }
        res.close();
        stmt.close();
        return scores;
    }

    public static void updateHighScore(int newScore) throws SQLException {
        Statement stmt = DataBaseConnection.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        ResultSet res = stmt.executeQuery("select gameHighScore from account where accountId = " + DataBaseConnection.SESSION);
        res.next();
        if (res.getInt("gameHighScore") < newScore) {
            stmt.executeUpdate("update account set gameHighScore = "+newScore+" where accountId = " + DataBaseConnection.SESSION);
        }
        res.close();
        stmt.close();
    }

    public static void deleteFriend(int friendId) throws SQLException {
        try (Statement stmt = DataBaseConnection.conn.createStatement()) {
            stmt.executeUpdate("delete from friend where accountIdSender = "
                    + DataBaseConnection.SESSION + " and accountIdReceiver = " + friendId + " and accepted = 1");
            stmt.executeUpdate("delete from friend where accountIdReceiver = "
                    + DataBaseConnection.SESSION + " and accountIdSender = " + friendId + " and accepted = 1");
        }

    }

    public static ArrayList<Account> getPersons(int accountId) throws SQLException {
        ArrayList<Account> persons = new ArrayList<>();
        Statement stmt = DataBaseConnection.conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from account where accountId not in("
                + " select accountIdReceiver from friend where accountIdSender = " + accountId
                + " and accepted = 1"
                + " union "
                + " select accountIdSender from friend where accountIdReceiver = " + accountId
                + " and accepted = 1"
                + ") and accountId <> " + accountId);
        while (res.next()) {
            Account account = new Account(res.getInt("accountId"),
                    res.getString("firstName"), res.getString("lastName"),
                    res.getString("email"), res.getString("password"),
                    res.getString("gender"), res.getDate("dateOfBirth"),
                    res.getString("creationDateTime"), res.getString("profilePicture"),
                    res.getInt("gameHighScore"));
            persons.add(account);
        }
        res.close();
        stmt.close();
        return persons;
    }

    public static boolean requestIsSentTo(int personId) throws SQLException {
        Statement stmt = DataBaseConnection.conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from friend where accountIdReceiver = "
                + personId + " and accountIdSender = " + DataBaseConnection.SESSION
                + " and accepted is null");
        if (res.next()) {
            res.close();
            stmt.close();
            return true;
        } else {
            res.close();
            stmt.close();
            return false;
        }
    }

    public static ArrayList<Integer> getNotifications(int accountId) throws SQLException {
        ArrayList notifications = new ArrayList<>();
        Statement stmt = DataBaseConnection.conn.createStatement();
        ResultSet res = stmt.executeQuery("select accountIdSender from friend where accountIdReceiver = "
                + accountId + " and accepted is null");
        while (res.next()) {
            notifications.add(res.getInt("accountIdSender"));
        }
        res.close();
        stmt.close();
        return notifications;
    }

    public static void insertFriendRequest(int accountIdSender, int accountIdReceiver) throws SQLException {
        Statement stmt = DataBaseConnection.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        stmt.executeUpdate("insert into friend(accountIdSender, accountIdReceiver) values ("
                + accountIdSender + "," + accountIdReceiver + ")");
        stmt.close();
    }

    public static void deleteFriendRequest(int accountIdSender, int accountIdReceiver) throws SQLException {
        Statement stmt = DataBaseConnection.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        stmt.executeUpdate("delete from friend where accountIdSender = " + accountIdSender
                + " and accountIdReceiver = " + accountIdReceiver + " and accepted is null");
        stmt.close();
    }

    public static void insertAcceptRequest(int accountIdSender, int accountIdReceiver) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement(
                "update friend set accepted = 1, responseDateTime = ? where accountIdReceiver = ?"
                + " and accountIdSender = ? and accepted is null");
        stmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
        stmt.setInt(2, accountIdReceiver);
        stmt.setInt(3, accountIdSender);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void insertRejectRequest(int accountIdSender, int accountIdReceiver) throws SQLException {
        PreparedStatement stmt = DataBaseConnection.conn.prepareStatement(
                "update friend set accepted = 0, responseDateTime = ? where accountIdReceiver = ?"
                + " and accountIdSender = ? and accepted is null");
        stmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
        stmt.setInt(2, accountIdReceiver);
        stmt.setInt(3, accountIdSender);
        stmt.executeUpdate();
        stmt.close();
    }

    public static ArrayList<Status> getStatus() throws SQLException {
        ArrayList posts = new ArrayList<>();
        String query = "select distinct userAccountId, statusText, statusDateTime, statusId"
                + " from status, friend"
                + " where (accountIdReceiver = userAccountId or accountIdSender = userAccountId)"
                + " and accepted = true"
                + " and (accountIdSender = " + SESSION + " or accountIdReceiver = " + SESSION + ")"
                + " or userAccountId = " + SESSION
                + " order by statusDateTime desc";
        try (Statement stmt = DataBaseConnection.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet res = stmt.executeQuery(query)) {
            while (res.next()) {
                Status status = new Status(res.getInt(4), res.getString(2), res.getDate(3), res.getTime(3), res.getInt(1));
                posts.add(status);
            }
        }
        return posts;
    }

    public static String getFriendName(int id) throws SQLException {
        String nameQuery = "select firstName, lastName from account where accountId = " + id;
        String friendName;
        try (Statement stmt1 = DataBaseConnection.conn.createStatement(); ResultSet res1 = stmt1.executeQuery(nameQuery)) {
            res1.next();
            friendName = res1.getString(1) + " " + res1.getString(2);
        }
        return friendName;
    }

    public static int insertToPosts(String text) throws SQLException {
        int res2;
        String insertQuery = "insert into status (statusText,userAccountId)"
                + " values('" + text + "'," + DataBaseConnection.SESSION + ")";
        try (Statement stmt2 = DataBaseConnection.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            res2 = stmt2.executeUpdate(insertQuery);
        }
        return res2;
    }

    public static void editMyStatus(String text, int id) throws SQLException {
        String editQuery = "select statusText, statusId from status where statusId = " + id;
        try (Statement stmt = DataBaseConnection.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE); ResultSet rs = stmt.executeQuery(editQuery)) {
            while (rs.next()) {
                rs.updateString(1, text);
                rs.updateRow();
            }
        }
    }

    public static int deleteStatus(int id) throws SQLException {
        String deleteQuery = "delete from status where statusId = " + id;
        int rs;
        try (Statement stmt = DataBaseConnection.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
            rs = stmt.executeUpdate(deleteQuery);
        }
        return rs;
    }
}
