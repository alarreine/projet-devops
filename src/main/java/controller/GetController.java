package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import bean.reponse.Information;
import com.google.gson.Gson;
import controller.exception.KeyNotFoundException;
import enumerate.StatusReponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alarreine on 15/04/2017.
 */
@RestController
@RequestMapping(method = RequestMethod.GET)
public class GetController {


    /**
     * Récupérer l'information stockée par la clé .
     * @param client Nom de client avec lequel l'utilisateur s'est connecté
     * @param k La clé à saisir dans le stockage
     * @param request Paramètre interne pour savoir l'origine du messsage
     * @return une chaine de charactère en indiquant l'information de la clé saisie
     */

    @RequestMapping(value = "/{client}/key/{k}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Basic> getInformationByKey(@PathVariable String client, @PathVariable String k, HttpServletRequest request) {
        Client cli = new Client(client, request.getRemoteAddr());
        Gson gson = new Gson();
        Information reponseInformation;
        try{
            String result = Application.getServer().demanderInformation(cli, k);
            reponseInformation = gson.fromJson(result, Information.class);
            reponseInformation.setStatus(StatusReponse.OK);
        }catch (KeyNotFoundException e){
            reponseInformation = new Information();
            reponseInformation.setStatus(StatusReponse.KEY_NOT_FOUND);
        }



        return new ResponseEntity<Basic>(reponseInformation, HttpStatus.OK);
    }


    /**
     * Tester si la clé existe dans le stockage du serveur.
     * @param client Nom de client avec lequel l'utilisateur s'est connecté
     * @param k La clé pour tester
     * @param request Paramètre interne pour savoir l'origine du messsage
     * @return satatus de réponse OK si la clé exist et status de réponse KEY_NOT_FOUND si non
     */

    @RequestMapping(value = "/{client}/exist/{k}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Basic> existKey(@PathVariable String client, @PathVariable String k, HttpServletRequest request) {
        Client cli = new Client(client, request.getRemoteAddr());
        Gson gson = new Gson();

        Basic reponse;
        try {
            Application.getServer().exists(cli, k);
            reponse =new Basic(StatusReponse.KEY_FOUND);
        }catch (KeyNotFoundException e){
            reponse =new Basic(StatusReponse.KEY_NOT_FOUND);
        }
        return new ResponseEntity<Basic>(reponse, HttpStatus.OK);
    }
}
