package com.example.film.service;

import com.example.film.constant.BaseConstant;
import com.example.film.util.CmdUtil;
import com.example.film.util.FFmpegCmd;
import com.example.film.util.FFprobeCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

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
        File file =  new  File(filePath);
        String [] fileNames = file.list();
        System.out.println(Arrays.toString(fileNames));
        if (fileNames.length == 0) {
            return null;
        }
        // 创建process文件夹
        CmdUtil.execCmd(String.format(BaseConstant.MAKE_PROCESS,filePath));

        for (String fileName : fileNames) {
            if (fileName.contains("mp4")){
                int videoTime = FFprobeCmd.showEntries(filePath + "/" + fileName);
                System.out.println("videoTime ="+videoTime);

                int length= videoTime / 3;
                int start=0;

                String videoPath = path + '/' + name;
                String save_name = path + "/process/";

                for (int i = 0; i <3 ; i++) {
                    String outPut = String.format ("%s%s.mp4",save_name,i);
                    FFmpegCmd.VideoSplitToMethod(start,videoPath,length,outPut);
                    start+=length;
                }
            }
        }

        /**
         * 对传递过来的视频名字、视频时间做剪辑分段处理
         * @param path
         * @param name
         * @param time
         */







        CmdUtil.execCmd(String.format(BaseConstant.VIDEO_FIRST_IMAGE,filePath,path,BaseConstant.IMAGE_NAME));

        cutFilmService.cutFilmToThree(filePath);

        CmdUtil.execCmd(String.format(BaseConstant.FILL_IMAGE_BLACK,filePath,path,BaseConstant.IMAGE_NAME));
        // 将视频处理成三段



        return "success";
    }

}
