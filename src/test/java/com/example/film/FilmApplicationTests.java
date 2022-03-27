package com.example.film;

import com.example.film.service.HandleFilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FilmApplicationTests {

    @Autowired
    HandleFilmService handleFilmService;

    @Test
    void contextLoads() {
    }

    @Test
    void cutFilmTest(){
        handleFilmService.cutFilm("/Users/shixin/film/fire/2022-03-19");
    }

}
