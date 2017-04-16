package controller;

import bean.Client;
import bean.reponse.Basic;
import bean.reponse.Information;
import bean.reponse.ClientList;
import controller.exception.KeyNotFoundException;
import enumerate.StatusReponse;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alarreine on 15/04/2017.
 */
@RestController
public class GetController {
    @RequestMapping(method = RequestMethod.GET, value = "/key/{k}")
    public Basic getInformationByKey(@PathVariable String k) {

        Information reponseInformation = new Information();
        reponseInformation.getInformation().add("ouiiii");
        reponseInformation.getInformation().add("ouiiiiiiiiii");
        reponseInformation.setStatus(StatusReponse.OK);
        if(k=="3"){
            throw new KeyNotFoundException(k);
        }

        return reponseInformation;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listclient")
    public Basic getListClientConnected() {
        ClientList reponse = new ClientList();
        reponse.getListClient().add(new Client("Malala","local"));
        reponse.getListClient().add(new Client("Fred","local"));
        return reponse;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/exist")
    public Basic existKey(@RequestParam(value="id", defaultValue="0") String key) {
        Basic reponse = new Basic();
        reponse.setStatus(StatusReponse.OK);
        return reponse;
    }
}
