package bean.reponse;

import enumerate.StatusReponse;

/**
 * Created by alarreine on 12/04/2017.
 */
public  class Basic {
    private StatusReponse status;

    public Basic(StatusReponse status) {
        this.status = status;
    }

    public Basic() {

    }

    public StatusReponse getStatus() {
        return status;
    }

    public void setStatus(StatusReponse status) {
        this.status = status;
    }
}
