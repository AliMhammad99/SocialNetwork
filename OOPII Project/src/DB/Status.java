package DB;

import java.sql.Date;
import java.sql.Time;

public class Status {
    private int statusId;
    private String statusText;
    private java.sql.Date statusDate;
    private java.sql.Time statusTime;
    private int userAccountId;

    public Status(int statusId, String statusText, Date statusDate, Time statusTime, int userStatusId) {
        this.statusId = statusId;
        this.statusText = statusText;
        this.statusDate = statusDate;
        this.statusTime = statusTime;
        this.userAccountId = userStatusId;
    }

    public Time getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Time statusTime) {
        this.statusTime = statusTime;
    }
    
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userStatusId) {
        this.userAccountId = userStatusId;
    }
    
}
