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
 * Created by alarreine on 15/04/2017.
 */
@RestController
@RequestMapping(method = RequestMethod.POST)
public class PostController {

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
