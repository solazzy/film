package com.example.film.controller;

import com.example.film.domain.FilePath;
import com.example.film.service.HandleFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 视频处理
 *
 * @author shixin
 */
@RestController
@RequestMapping("/film")
public class CutFilmController {
    @Autowired
    private HandleFilmService handleFilmService;

    /**
     * 生成视频
     */
    @PostMapping("/cutFilm")
    public String cutFilm(FilePath filePath)
    {
        return handleFilmService.handleFilm(filePath.getFilePath());
    }
}
