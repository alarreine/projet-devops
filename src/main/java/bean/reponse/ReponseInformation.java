package bean.reponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alarreine on 11/04/2017.
 */


public class ReponseInformation extends ReponseBasic {

    private List<String> information;

    public ReponseInformation() {
        super();
        this.information = new ArrayList<>();
    }

    public List<String> getInformation() {
        return information;
    }

    public void setInformation(List<String> information) {
        this.information = information;
    }
}