package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import enumerate.StatusReponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alarreine on 15/04/2017.
 */
@RestController
@RequestMapping(method = RequestMethod.DELETE)
public class DeleteController {

    /**
     * Effacer cle stockée dans la serveur.
     * @param client Nom de client avec lequel l'utilisateur s'est connecté
     * @param k La clé pour effacer
     * @param request 
     * @return
     */
    @RequestMapping(value = "/{client}/delete/{k}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Basic> deleteKey(@PathVariable String client, @PathVariable String k, HttpServletRequest request) {
        Client cli = new Client(client, request.getRemoteAddr());
        Application.getServer().effacerInformation(cli, k);
        return new ResponseEntity<Basic>(new Basic(StatusReponse.OK), HttpStatus.OK);
    }
}
