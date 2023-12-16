package Final_Project;

public class Admin {
    private int adminID;
    private String name;
    private String loginCreds;

    // Constructor
    public Admin(int adminID, String name, String loginCreds) {
        this.adminID = adminID;
        this.name = name;
        this.loginCreds = loginCreds;
    }

    // Getters
    public int getAdminID() {
        return adminID;
    }

    public String getName() {
        return name;
    }

    public String getLoginCreds() {
        return loginCreds;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLoginCreds(String loginCreds) {
        this.loginCreds = loginCreds;
    }

}
