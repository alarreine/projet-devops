package bean.requete;

import bean.Client;

/**
 * Created by alarreine on 12/04/2017.
 */
public class RequeteRenomeCle {

    private String key;
    private String newKey;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNewKey() {
        return newKey;
    }

    public void setNewKey(String newKey) {
        this.newKey = newKey;
    }

    public RequeteRenomeCle(String key, String newKey) {

        this.key = key;
        this.newKey = newKey;
    }
}
