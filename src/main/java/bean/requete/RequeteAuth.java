package bean.requete;

/**
 * Created by alarreine on 12/04/2017.
 */
public class RequeteAuth {
    private String user;
    private String password;

    public RequeteAuth(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public RequeteAuth() {
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
