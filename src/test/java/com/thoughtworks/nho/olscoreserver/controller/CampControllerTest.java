package com.thoughtworks.nho.olscoreserver.controller;

import com.thoughtworks.nho.olscoreserver.camp.Camp;
import com.thoughtworks.nho.olscoreserver.repository.CampRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class CampControllerTest {

    private CampRepository campRepository = mock(CampRepository.class);//数据校验

    private CampController campController = new CampController();

    @Before
    public void setup() {
        Whitebox.setInternalState(campController, "campRepository", campRepository);
    }

    @Test
    public void should_return_201_when_create_a_valid_camp() {
        Camp camp = new Camp();
        camp.setTitle("TestLesson");
        camp.setDesc("TestDesc");
        ResponseEntity<String> response = campController.create(camp);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
    }

    @Test
    public void should_return_400_when_create_a_invalid_camp() {
        Camp camp = new Camp();
        camp.setTitle("TestLesson12345678901234567890123456789012345678901234567890");
        camp.setDesc("TestDesc");
        ResponseEntity<String> response = campController.create(camp);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }

    @Test
    public void should_return_409_when_create_a_existed_camp() {
        Camp camp = new Camp();
        camp.setTitle("TestLesson");
        camp.setDesc("TestDesc");
        Camp existedCamp = new Camp();
        existedCamp.setTitle("TestLesson");
        Mockito.when(campRepository.findOne(any(Example.class))).thenReturn(existedCamp);
        ResponseEntity<String> response = campController.create(camp);
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCodeValue());
    }

    @Test
    public void should_return_empty_list_when_get_camps() {
        Mockito.when(campRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Camp>> response = campController.getCamps();
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }
}