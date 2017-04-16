package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import bean.reponse.Information;
import bean.requete.Auth;
import bean.requete.SetInformation;
import com.google.gson.Gson;
import enumerate.StatusReponse;
import org.springframework.http.HttpHeaders;
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

    @RequestMapping("/{client}/set/{setInformation}")
    public Basic stockerInformationParCle(@PathVariable String client, SetInformation setInformation, HttpServletRequest reques) {
        Gson gson = new Gson();

        Client cli = new Client();
        cli.setNom(client);
        cli.setIp(reques.getRemoteAddr());

        String json = gson.toJson(setInformation.getListInfo());

        Application.getServer().setInformation(cli, setInformation.getKey(), json);
        Basic reponse = new Basic();
        reponse.setStatus(StatusReponse.OK);

        return reponse;
    }

    @RequestMapping("/auth/{client}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Basic authorisation(@PathVariable Auth client, HttpServletRequest request) {

        Client cli = new Client();
        cli.setIp(request.getRemoteAddr());
        cli.setNom(client.getUser());

        Basic reponse = new Basic();
        reponse.setStatus(StatusReponse.UNAUTHORIZED);

        Application.getServer().auth(cli, client.getPassword());
        reponse.setStatus(StatusReponse.AUTHORIZED);

        return reponse;
    }

}
