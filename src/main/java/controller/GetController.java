package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import bean.reponse.Information;
import bean.reponse.ClientList;
import bean.requete.SetInformation;
import com.google.gson.Gson;
import controller.exception.KeyNotFoundException;
import enumerate.StatusReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red2controler.Serveur;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alarreine on 15/04/2017.
 */
@RestController
@RequestMapping(method = RequestMethod.GET)
public class GetController {


    @RequestMapping(value = "/{client}/key/{k}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Information>  getInformationByKey(@PathVariable String client, @PathVariable String k,HttpServletRequest request) {

        Client cli = new Client(client,request.getRemoteAddr());
        Gson gson = new Gson();

        String result = Application.getServer().demanderInformation(cli,k);
        Information reponseInformation = gson.fromJson(result,Information.class);


        reponseInformation.setStatus(StatusReponse.KEY_FOUND);

        return new ResponseEntity<Information>(reponseInformation,HttpStatus.ACCEPTED);
    }

//    @RequestMapping(value = "/{client}/listclient/")
//    @ResponseStatus(HttpStatus.OK)
//    public Basic getListClientConnected(@PathVariable String client,HttpServletRequest request) {
//        ClientList reponse = new ClientList();
//        reponse.getListClient().add(new Client("Malala","local"));
//        reponse.getListClient().add(new Client("Fred","local"));
//        return reponse;
//    }

    @RequestMapping(value = "/{client}/exist/{k}")
    @ResponseStatus(HttpStatus.OK)
    public Basic existKey(@PathVariable String client, @PathVariable String k,HttpServletRequest request) {
        Client cli = new Client(client,request.getRemoteAddr());
        Gson gson = new Gson();

        boolean result = Application.getServer().exists(cli,k);
        Basic reponse = new Basic();
        reponse.setStatus(StatusReponse.OK);
        return reponse;
    }
}
