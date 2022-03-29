package com.example.film.service;

import com.example.film.util.CmdUtil;
import com.example.film.util.FFmpegCmd;
import com.example.film.util.FFprobeCmd;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Service
public class CutFilmService {

    /**
     * 将视频截取成三段
     * @param path
     * @return
     */
    public String cutFilmToThree(String path){
        File file =  new  File(path);
        String [] fileName = file.list();
        System.out.println(Arrays.toString(fileName));
        int videoTime = 0;
        try {
            Runtime.getRuntime().exec("mkdir "+ path+"/"+"process");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String s : fileName) {
            if (s.contains("mp4")){
                videoTime = FFprobeCmd.showEntries(path + "/" + s);
                System.out.println("videoTime ="+videoTime);
                video_cut_1(path ,s,videoTime);
            }
        }

        for (int i = 0; i < 3 ; i++) {
            try {
                String save_name = path+ "/process/";
                String aiPath = path+"/process/ai-"+i;
                Runtime.getRuntime().exec("mkdir "+ aiPath);
                String outPut = String.format ("%s%s.mp4",save_name,i);
                FFmpegCmd.VideoSplitToNMethod(outPut,aiPath);
                int fileNum = videoTime/3/20;
                int remainder = fileNum%5;
                int quotient = fileNum/5;
                StringBuffer stringBuffer = new StringBuffer();
                if (remainder == 0){
                    for (int j = 0; j < quotient +1; j++) {
                        stringBuffer.append("\r\n");
                        stringBuffer.append(j*5);
                        stringBuffer.append("请配音：\r\n");
                    }
                    CmdUtil.writeTxtFile(stringBuffer.toString(),aiPath + "/test.txt");
                }
                if (remainder == 1 || remainder == 2){
                    for (int j = 0; j < quotient; j++) {
                        stringBuffer.append("\r\n");
                        stringBuffer.append(j*5);
                        stringBuffer.append("请配音：\r\n");
                    }
                    stringBuffer.append("\r\n");
                    stringBuffer.append(fileNum);
                    stringBuffer.append("请配音：\r\n");

                    CmdUtil.writeTxtFile(stringBuffer.toString(),aiPath + "/test.txt");
                }
                if (remainder == 3 || remainder == 4 ||remainder == 5 ){
                    for (int j = 0; j < quotient +1; j++) {
                        stringBuffer.append("\r\n");
                        stringBuffer.append(j*5);
                        stringBuffer.append("请配音：\r\n");
                    }
                    stringBuffer.append("\r\n");
                    stringBuffer.append(fileNum);
                    stringBuffer.append("请配音：\r\n");
                    CmdUtil.writeTxtFile(stringBuffer.toString(),aiPath + "/test.txt");
                }
            } catch (Exception e) {
                e.printStackTrace();
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

        int length= time / 3;
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
