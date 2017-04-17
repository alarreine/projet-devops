package controller;

import application.Application;
import bean.Client;
import bean.reponse.Basic;
import bean.reponse.Information;
import bean.requete.Delete;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
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
    public Basic deleteKey(@PathVariable String client, @RequestBody Delete d, HttpServletRequest request){
        Client cli = new Client(client,request.getRemoteAddr());
        String result = Application.getServer().effacerInformation(cli, d.getKey());
        Gson gson = new Gson();
        Information reponseInformation = gson.fromJson(result,Information.class);
        return  reponseInformation;
    }
}
