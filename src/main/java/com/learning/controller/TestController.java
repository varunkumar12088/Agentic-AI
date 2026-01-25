package com.learning.controller;

import com.learning.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> test(@RequestParam(name = "q") String query) {
        String response = testService.testing(query);
        return ResponseEntity.ok(response);
    }
}
