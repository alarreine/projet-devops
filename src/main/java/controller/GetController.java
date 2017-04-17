package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import bean.reponse.Information;
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
@RequestMapping(method = RequestMethod.GET)
public class GetController {


    @RequestMapping(value = "/{client}/key/{k}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Basic> getInformationByKey(@PathVariable String client, @PathVariable String k, HttpServletRequest request) {
        Client cli = new Client(client, request.getRemoteAddr());
        Gson gson = new Gson();

        String result = Application.getServer().demanderInformation(cli, k);
        Information reponseInformation = gson.fromJson(result, Information.class);
        reponseInformation.setStatus(StatusReponse.OK);

        return new ResponseEntity<Basic>(reponseInformation, HttpStatus.OK);
    }

    @RequestMapping(value = "/{client}/exist/{k}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Basic> existKey(@PathVariable String client, @PathVariable String k, HttpServletRequest request) {
        Client cli = new Client(client, request.getRemoteAddr());
        Gson gson = new Gson();

        Application.getServer().exists(cli, k);
        Basic reponse = new Basic(StatusReponse.OK);
        return new ResponseEntity<Basic>(reponse, HttpStatus.OK);
    }
}
