package controller;

/**
 * Created by alarreine on 11/04/2017.
 */

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SetInformationControleur {


    @PostMapping("/set")
        public ResponseEntity stockerInformationParCle(@RequestParam(value = "cles", defaultValue = "0") String id,
                                                       @RequestParam(value = "info", defaultValue = "0") String info) {

        ResponseEntity reponse = new ResponseEntity("Successfully login", new HttpHeaders(), HttpStatus.OK);


        return reponse;
    }

}

