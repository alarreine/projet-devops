package bean.reponse;

import bean.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alarreine on 12/04/2017.
 */
public class ReponseListClient extends ReponseBasic {

    private List<Client> listClient;

    public ReponseListClient(List<Client> listClient) {
        super();
        this.listClient = listClient;
    }

    public ReponseListClient() {
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
