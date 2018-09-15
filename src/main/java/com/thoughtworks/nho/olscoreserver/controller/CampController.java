package com.thoughtworks.nho.olscoreserver.controller;

import com.thoughtworks.nho.olscoreserver.camp.Camp;
import com.thoughtworks.nho.olscoreserver.repository.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
@RequestMapping("/api/camp")
public class CampController {

    @Autowired
    private CampRepository campRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Camp camp) {
        if (camp.getTitle().length() > 50 || camp.getDesc().length() > 200) {
            return new ResponseEntity<>(camp.getTitle(), HttpStatus.BAD_REQUEST);
        }
        Example<Camp> ex = Example.of(camp);
        if (campRepository.findOne(ex) != null) {
            return new ResponseEntity<>(camp.getTitle(), HttpStatus.CONFLICT);
        }
        camp.setCreateTime(new Date().toString());
        campRepository.save(camp);
        return new ResponseEntity<>(camp.getTitle(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Camp>> getCamps() {
        return new ResponseEntity<>(campRepository.findAll(), HttpStatus.OK);
    }
}
