package com.example.film.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandleFilmService {
    @Autowired
    CutFilmService cutFilmService;

    /**
     * 处理视频
     * @param filePath
     * @return
     */
    public String handleFilm(String filePath){

        // 将视频处理成三段
        cutFilmService.cutFilmToThree(filePath);


        return "success";
    }

}
