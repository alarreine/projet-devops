package bean.requete;

/**
 * Created by alarreine on 12/04/2017.
 */
public class RequeteAuth {
    private String password;

    public RequeteAuth(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
