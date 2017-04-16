package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import bean.reponse.Information;
import bean.requete.Increase;
import bean.requete.RenameKey;
import bean.requete.SetInformation;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlleur pour les méthodes qui font de mise-a-jour des informations stockées
 */
@RestController
@RequestMapping(method = RequestMethod.PUT)
public class PutController {

    @RequestMapping(value = "/{client}/rename/{renameKey}")
    @ResponseStatus(HttpStatus.OK)

    public Basic renameKey(@PathVariable String client, @RequestBody RenameKey renomeCles,HttpServletRequest request) {
        Client cli = new Client(client,request.getRemoteAddr());
        String result = Application.getServer().renomeCle(cli,renomeCles.getKey(),renomeCles.getNewKey());
        Gson gson = new Gson();
        Information reponseInformation = gson.fromJson(result,Information.class);
        return  reponseInformation;
    }

    @RequestMapping(value = "/{client}/increase/{increase}")
    @ResponseStatus(HttpStatus.OK)

    public Basic increaseKey(@PathVariable String client, @RequestBody Increase iK, HttpServletRequest request){
        Client cli = new Client(client,request.getRemoteAddr());
        String result = Application.getServer().incrementerInformation(cli, iK.getKey());
        Gson gson = new Gson();
        Information reponseInformation = gson.fromJson(result,Information.class);
        return  reponseInformation;
    }


    @RequestMapping(value = "/{client}/addlist/{setInformation}")
    @ResponseStatus(HttpStatus.OK)
    public Basic addListe(@PathVariable String client, @RequestBody SetInformation sI, HttpServletRequest request){
        Client cli = new Client(client,request.getRemoteAddr());
        String result = Application.getServer().setInformation(cli, sI.getKey(),sI.getListInfo().getInfo().);
        Gson gson = new Gson();
        Information reponseInformation = gson.fromJson(result,Information.class);
        return  reponseInformation;
    }

}
