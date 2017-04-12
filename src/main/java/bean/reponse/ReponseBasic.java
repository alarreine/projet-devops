package bean.reponse;

import enumerate.StatusReponse;

/**
 * Created by alarreine on 12/04/2017.
 */
public  class ReponseBasic {
    private StatusReponse status;

    public ReponseBasic(StatusReponse status) {
        this.status = status;
    }

    public ReponseBasic() {

    }

    public StatusReponse getStatus() {
        return status;
    }

    public void setStatus(StatusReponse status) {
        this.status = status;
    }
}
