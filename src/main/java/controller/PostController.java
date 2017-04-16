package controller;

import bean.reponse.Basic;
import enumerate.StatusReponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alarreine on 15/04/2017.
 */
@RestController
public class PostController {

    @PostMapping("/set")
    public Basic stockerInformationParCle(@RequestParam(value = "cles", defaultValue = "0") String id,
                                          @RequestParam(value = "info", defaultValue = "0") String info) {

        Basic reponse = new Basic();
        reponse.setStatus(StatusReponse.OK);


        return reponse;
    }

}
