package com.learning.controller;

import com.learning.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@RequestBody String query) {
        String result = queryService.executeQuery(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
