package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import bean.reponse.Information;
import bean.requete.Delete;
import com.google.gson.Gson;
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

    @RequestMapping(value = "/{client}/delete/{k}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Basic> deleteKey(@PathVariable String client, @PathVariable String k, HttpServletRequest request){
        Client cli = new Client(client,request.getRemoteAddr());
        Application.getServer().effacerInformation(cli, k);
        Basic reponse = new Basic(StatusReponse.OK);
        return  new ResponseEntity<Basic>(reponse, HttpStatus.OK);
    }
}
