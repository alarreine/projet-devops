package bean.reponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alarreine on 11/04/2017.
 */


public class Information extends Basic {

    private List<String> info;

    public Information() {
        super();
        this.info = new ArrayList<>();
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }
}
