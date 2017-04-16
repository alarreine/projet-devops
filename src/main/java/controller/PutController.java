package controller;

import bean.reponse.Basic;
import bean.requete.RenameKey;
import controller.exception.ConflicException;
import enumerate.StatusReponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controlleur pour les méthodes qui font de mise-a-jour des informations stockées
 */
@RestController
@RequestMapping(method = RequestMethod.PUT)
public class PutController {

    @RequestMapping(value = "/rename")
    @ResponseStatus(HttpStatus.OK)
    public Basic renomeCle(@RequestBody RenameKey renomeCles) {

        Basic reponseBasic = new Basic();
        if(renomeCles.getKey()=="1") {
            throw new ConflicException(renomeCles.getKey());
        }
        reponseBasic.setStatus(StatusReponse.OK);
        return reponseBasic;
    }
}
