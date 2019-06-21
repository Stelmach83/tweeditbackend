package dev.stelmach.tweeditapi.controller;

import dev.stelmach.tweeditapi.api.TweeditResponse;
import dev.stelmach.tweeditapi.entity.TestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<TweeditResponse> test(@RequestBody TestDTO test) {
        TweeditResponse resp = new TweeditResponse(200, "Seems good", test);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
