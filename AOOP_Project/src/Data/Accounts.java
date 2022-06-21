package Data;

public class Accounts {
    private Integer accountID;
    private String username;
    private String password;
    
    public Accounts(Integer accountID, String username, String password) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
