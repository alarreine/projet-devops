package bean.reponse;

import bean.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alarreine on 12/04/2017.
 */
public class ClientList extends Basic {

    private List<Client> listClient;

    public ClientList(List<Client> listClient) {
        super();
        this.listClient = listClient;
    }

    public ClientList() {
        super();
        this.listClient = new ArrayList<>();
    }

    public List<Client> getListClient() {
        return listClient;
    }

    public void setListClient(List<Client> listClient) {
        this.listClient = listClient;
    }


}
