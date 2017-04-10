package controller;

/**
 * Created by alarreine on 11/04/2017.
 */

import bean.ReponseInformation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class DemanderControleur {



    @RequestMapping(method = GET, value = "get")
    public ReponseInformation greeting(@RequestParam(value="id", defaultValue="0") String id) {
        ReponseInformation reponseInformation = new ReponseInformation(id);
        return reponseInformation;
    }

}

