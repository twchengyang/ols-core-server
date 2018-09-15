package com.thoughtworks.nho.olscoreserver.controller;

import com.thoughtworks.nho.olscoreserver.camp.Camp;
import com.thoughtworks.nho.olscoreserver.repository.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/camp")
public class CampController {

    @Autowired
    private CampRepository campRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> create(@RequestBody Camp camp) {
        camp.setCreateTime(new Date().toString());
        campRepository.save(camp);
        return new ResponseEntity<>(camp.getTitle(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Camp>> getCamps() {
        return new ResponseEntity<>(campRepository.findAll(), HttpStatus.OK);
    }
}
