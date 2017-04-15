package controller;

import bean.Client;
import bean.reponse.ReponseBasic;
import bean.reponse.ReponseInformation;
import bean.reponse.ReponseListClient;
import controller.exception.KeyNotFoundException;
import enumerate.StatusReponse;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alarreine on 15/04/2017.
 */
@RestController
public class GetController {
    @RequestMapping(method = RequestMethod.GET, value = "/key/{k}")
    public ReponseBasic getInformationByKey(@PathVariable String k) {

        ReponseInformation reponseInformation = new ReponseInformation();
        reponseInformation.getInformation().add("ouiiii");
        reponseInformation.getInformation().add("ouiiiiiiiiii");
        reponseInformation.setStatus(StatusReponse.OK);
        if(k=="3"){
            throw new KeyNotFoundException(k);
        }

        return reponseInformation;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listclient")
    public ReponseBasic getListClientConnected() {
        ReponseListClient reponse = new ReponseListClient();
        reponse.getListClient().add(new Client("Malala","local"));
        reponse.getListClient().add(new Client("Fred","local"));
        return reponse;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/exist")
    public ReponseBasic existKey(@RequestParam(value="id", defaultValue="0") String key) {
        ReponseBasic reponse = new ReponseBasic();
        reponse.setStatus(StatusReponse.OK);
        return reponse;
    }
}
