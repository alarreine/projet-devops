package controller;

/**
 * Created by alarreine on 11/04/2017.
 */

import bean.Client;
import bean.reponse.ReponseBasic;
import bean.reponse.ReponseInformation;
import bean.reponse.ReponseListClient;
import enumerate.StatusReponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemanderControleur {



    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public ReponseBasic getInformationByKey(@RequestParam(value="id", defaultValue="0") String key) {

        ReponseInformation reponseInformation = new ReponseInformation();
        reponseInformation.getInformation().add("ouiiii");
        reponseInformation.getInformation().add("ouiiiiiiiiii");
        reponseInformation.setStatus(StatusReponse.OK);
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

