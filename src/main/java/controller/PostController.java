package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import bean.requete.Auth;
import bean.requete.SetInformation;
import com.google.gson.Gson;
import enumerate.StatusReponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlleur pour les méthodes qui font de insertion des informations.
 * Dans tous les cas il faul que l'utilisateur soit connecté.
 */
@RestController
@RequestMapping(method = RequestMethod.POST)
public class PostController {
    /**
     * Stocker une information par une clé donnée pour l'utilisateur.
     * @param client Nom client avec lequel l'utilisateur s'est connecté
     * @param setInformation Information pour stocker
     * @param request Paramètre interne pour savoir l'origin du message
     * @return ACCEPTED si l'insertion a été fait.
     */
    @RequestMapping("/{client}/set")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<Basic> stockerInformationParCle(@PathVariable String client, @RequestBody SetInformation setInformation, HttpServletRequest request) {
        Gson gson = new Gson();

        Client cli = new Client();
        cli.setNom(client);
        cli.setIp(request.getRemoteAddr());

        String json = gson.toJson(setInformation.getInfo());

        Application.getServer().setInformation(cli, setInformation.getKey(), json);

        return new ResponseEntity<Basic>(new Basic(StatusReponse.OK), HttpStatus.ACCEPTED);
    }
    /**
     * Autoriser la conexion d'un utilisateur.
     * @param client Nom client avec lequel l'utilisateur va se connecter
     * @param request Paramètre interne pour savoir l'origin du message
     * @return ACCEPTED si la connexion a été bien etablie. Sino UNAUTHORIZED
     */
    @RequestMapping("/auth")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<Basic> authorisation(@RequestBody Auth client, HttpServletRequest request) {

        Client cli = new Client();
        cli.setIp(request.getRemoteAddr());
        cli.setNom(client.getUser());

        Application.getServer().auth(cli, client);

        return new ResponseEntity<Basic>(new Basic(StatusReponse.AUTHORIZED), HttpStatus.ACCEPTED);
    }

}
