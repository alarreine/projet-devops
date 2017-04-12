package bean.requete;

import bean.Client;

/**
 * Created by alarreine on 12/04/2017.
 */
public class RequeteEffacer {

    private Client client;
    private String key;

    public RequeteEffacer(Client client, String key) {
        this.client = client;
        this.key = key;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
