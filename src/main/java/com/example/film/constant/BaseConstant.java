package com.example.film.constant;

public class BaseConstant {

    /**
     * 获取视频长度
     * -i 视频文件地址
     */
    public static final String SHOW_ENTRIES="ffprobe -i %s -show_entries format=duration -of compact=p=0:nk=1 -v 0";
    /**
     * 扩大分辨率
     * -y 覆盖输入
     * -i 视频文件地址
     */
    public static final String EXPAND_BOUNDARY="  -y -i  %s -vf  scale=%s:%s %s";
    public static final Integer AUTO=-2;

    /**
     * 设置封面
     *
     * -i %s -map 1 -map 0 -c copy -disposition:v:1 attached_pic -y %s
     *
     *  ffmpeg  -i 第一集-妖雾重回-1.mp4 -i E:\video\huluwa\cover3.jpg -map 0 -map 1 -c copy -c:v:1 png  -disposition:v:1 attached_pic E:\video\huluwa\success
     */
    public static final String SETTING_COVER="  -i %s -i %s -map 0 -map 1 -c copy -c:v:1 png  -disposition:v:1 attached_pic %s";
    public static final String VIDEO_INFO="  -i %s ";
    /**
     * 封面  font 输入路径
     */
    public static final String Image_OverLay=" -y  -i %s -i %s    -filter_complex overlay=%s:%s    %s";
    /**
     * 图片截取
     *  文件源 宽:高:start:end 输出路径
     */
    public static final String Image_SPLIT=" -y  -i %s     -vf crop=%s:%s:%s:%s    %s";
    /**
     * 视频分段
     *  开始时间00:00:00  视频路径 截取时长 输出路径
     */
    public static final String Video_SPLIT="-y -ss %s -i %s    -to %s        %s";
    /**
     * 视频分段
     *  开始时间00:00:00  视频路径 截取时长 输出路径
     */
    public static final String Video_SPLIT_TO="ffmpeg -ss %s -i %s  -c copy -t %s %s -loglevel quiet -y ";
    /**
     * 获取视频的第一张图片
     */
    public static final String VIDEO_FIRST_IMAGE = "ffmpeg -i %s -ss 0 -frames:v 1 %s%s";

    public static final String IMAGE_NAME = "/titlePage.mp4";
    /**
     * 整个16:9横屏视频全部填充黑色
     */
    public static final String FILL_IMAGE_BLACK = "ffmpeg -i %s -i 5.webp -filter_complex overlay=W-w:H-h  %s";

    /**
     * 创建process文件夹
     */
    public static final String MAKE_PROCESS ="mkdir %s/process";

    /**
     * 视频转换
     */
    public static final String Video_COVERT=" -i %s -c:v copy -c:a copy %s";

    /**
     * image to video
     */
    public static final String Image_TO_VIdeo=" -f image2 -r 1 -i %s  -vcodec h264  %s";


    public static final String Video_TS=" -i %s -c copy -bsf:v h264_mp4toannexb -f mpegts  %s";

    public static final String Concat="  -i \"concat:%s|%s\" -c copy -bsf:a aac_adtstoasc -movflags +faststart %s";

}
