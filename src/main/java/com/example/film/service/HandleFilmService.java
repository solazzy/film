package com.example.film.service;

import com.example.film.util.CmdUtil;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;

@Service
public class HandleFilmService {

    public String cutFilm(String path){
        File file =  new  File(path);
        String [] fileName = file.list();
        System.out.println(Arrays.toString(fileName));
        try {
            CmdUtil.execCmd("mkdir "+ path+"/"+"process");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String s : fileName) {
            if (s.contains("mp4")){
                int videoTime = getVideoTime(path + "/" + s);
                System.out.println("videoTime ="+videoTime);
                video_cut_1(path ,s,videoTime);
            }
        }

        return "success";
    }

    /**
     * 对传递过来的视频名字、视频时间做剪辑分段处理
     * @param path
     * @param name
     * @param time
     */
    public void video_cut_1(String path,String name,int time){

        int cutTime= time / 3;
        int firstTime=0;

        String file = path + '/' + name;
        String save_name = path + "/process/";

        for (int i = 0; i <3 ; i++) {
            String cmdLine = "ffmpeg -ss "+ firstTime +" -i " +file +" "+" -c copy -t " +cutTime + " "+save_name+i+".mp4 -loglevel quiet -y ";
            CmdUtil.execCmd(cmdLine);
            firstTime+=cutTime;
        }
    }

    /**
     * 获取视频长度
     * @param path
     * @return
     */
    public int getVideoTime(String path) {
        String command="ffprobe "+path+" -show_entries format=duration -of compact=p=0:nk=1 -v 0";
        String line = null;
        StringBuilder sb = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader	bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));


            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                int i = Double.valueOf(line).intValue();
                return i;
            }
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return 0;
    }

}
