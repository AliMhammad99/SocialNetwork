package oopii.project;

import DB.DataBaseConnection;
import GUI.*;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        DataBaseConnection.conn = DataBaseConnection.connect(DataBaseConnection.DB_NAME,
                DataBaseConnection.DB_USER_NAME, DataBaseConnection.DB_PASSWORD);
        LogIn logIn = new LogIn();
        logIn.setVisible(true);
    }
}
