package com.example.film.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CutFilmServiceTest {

    @Autowired
    CutFilmService cutFilmService;

    @Test
    void contextLoads() {
    }

    @Test
    void cutFilmTest(){
        cutFilmService.cutFilmToThree("/Users/shixin/film/fire/2022-03-27");
    }

}