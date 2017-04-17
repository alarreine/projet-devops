package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import bean.reponse.Information;
import bean.requete.Increase;
import bean.requete.RenameKey;
import bean.requete.SetInformation;
import com.google.gson.Gson;
import enumerate.StatusReponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlleur pour les méthodes qui font de mise-a-jour des informations stockées
 */
@RestController
@RequestMapping(method = RequestMethod.PUT)
public class PutController {

    /**
     * Renommer la clé stockée au serveur.
     * @param client Nom de client avec lequel l'utilisateur s'est connecté
     * @param renomeCles la clé à renommer
     * @param request Paramètre interne pour savoir l'origine du messsage
     * @return la nouvelle clé après changer l'ancienne
     */

    @RequestMapping(value = "/{client}/rename")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Information> renameKey(@PathVariable String client, @RequestBody RenameKey renomeCles, HttpServletRequest request) {
        Client cli = new Client(client, request.getRemoteAddr());
        String result = Application.getServer().renomeCle(cli, renomeCles.getKey(), renomeCles.getNewKey());
        Gson gson = new Gson();
        Information reponseInformation = gson.fromJson(result, Information.class);
        reponseInformation.setStatus(StatusReponse.OK);
        return new ResponseEntity<Information>(reponseInformation, HttpStatus.OK);
    }

    /**
     * Incrémenter la clé stockée au serveur.
     * @param client Nom de client avec lequel l'utilisateur s'est connecté
     * @param iK la clé à incrémenter
     * @param request Paramètre interne pour savoir l'origine du messsage
     * @return
     */

    @RequestMapping(value = "/{client}/increase")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Information> increaseKey(@PathVariable String client, @RequestBody Increase iK, HttpServletRequest request) {
        Client cli = new Client(client, request.getRemoteAddr());
        String result = Application.getServer().incrementerInformation(cli, iK.getKey());
        Gson gson = new Gson();
        Information reponseInformation = gson.fromJson(result, Information.class);
        reponseInformation.setStatus(StatusReponse.OK);
        return new ResponseEntity<Information>(reponseInformation, HttpStatus.OK);
    }

    /**
     * Ajouter une liste d'information au serveur.
     * @param client Nom de client avec lequel l'utilisateur s'est connecté
     * @param sI information à ajouter dans la liste qui déjà existe
     * @param request Paramètre interne pour savoir l'origine du messsage
     * @return
     */

    @RequestMapping(value = "/{client}/addlist")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Basic> addListe(@PathVariable String client, @RequestBody SetInformation sI, HttpServletRequest request) {
        Client cli = new Client(client, request.getRemoteAddr());
        Gson gson = new Gson();

        Information info1 = gson.fromJson(Application.getServer().demanderInformation(cli, sI.getKey()), Information.class);

        info1.getInfo().addAll(sI.getInfo().getInfo());
        String json = gson.toJson(info1);

        Application.getServer().setInformation(cli, sI.getKey(), json);
        Basic reponse = new Basic(StatusReponse.OK);
        return new ResponseEntity<Basic>(reponse, HttpStatus.OK);
    }

}
