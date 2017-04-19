package bean.requete;

/**
 * Created by alarreine on 12/04/2017.
 */
public class Auth {
    private String user;
    private String password;

    public Auth(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Auth() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
