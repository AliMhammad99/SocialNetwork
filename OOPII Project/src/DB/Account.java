package DB;

import java.sql.Date;
import org.apache.commons.net.ntp.TimeStamp;

public class Account {

    private int accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private Date dateOfBirth;
    private String creationDateTime;
    private String profilePicture;
    private int gameHighScore;

    public Account(int accountId, String firstName, String lastName, String email, String password, String gender, Date dateOfBirth, String creationDateTime, String profilePicture, int gameHighScore) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.creationDateTime = creationDateTime;
        this.profilePicture = profilePicture;
        this.gameHighScore = gameHighScore;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setScore(int gameHighScore) {
        this.gameHighScore = gameHighScore;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public int getScore() {
        return gameHighScore;
    }


}
