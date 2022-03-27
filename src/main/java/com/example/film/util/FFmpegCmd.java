package com.example.film.util;


public class FFmpegCmd {
	/**
	 * 扩大分辨率
	 * -y 覆盖输入
	 * -i 视频文件地址
	 */
	private static final String EXPAND_BOUNDARY="  -y -i  %s -vf  scale=%s:%s %s";
	private static final Integer AUTO=-2;

	/**
	 * 设置封面
	 *
	 * -i %s -map 1 -map 0 -c copy -disposition:v:1 attached_pic -y %s
	 *
	 *  ffmpeg  -i 第一集-妖雾重回-1.mp4 -i E:\video\huluwa\cover3.jpg -map 0 -map 1 -c copy -c:v:1 png  -disposition:v:1 attached_pic E:\video\huluwa\success
	 */
	private static final String SETTING_COVER="  -i %s -i %s -map 0 -map 1 -c copy -c:v:1 png  -disposition:v:1 attached_pic %s";
	private static final String VIDEO_INFO="  -i %s ";
	/**
	 * 封面  font 输入路径
	 */
	private static final String Image_OverLay=" -y  -i %s -i %s    -filter_complex overlay=%s:%s    %s";
	/**
	 * 图片截取
	 *  文件源 宽:高:start:end 输出路径
	 */
	private static final String Image_SPLIT=" -y  -i %s     -vf crop=%s:%s:%s:%s    %s";
	/**
	 * 视频分段
	 *  开始时间00:00:00  视频路径 截取时长 输出路径
	 */
	private static final String Video_SPLIT="-y -ss %s -i %s    -to %s        %s";
	/**
	 * 视频转换
	 */
	private static final String Video_COVERT=" -i %s -c:v copy -c:a copy %s";

	/**
	 * image to video
	 */
	private static final String Image_TO_VIdeo=" -f image2 -r 1 -i %s  -vcodec h264  %s";


	private static final String Video_TS=" -i %s -c copy -bsf:v h264_mp4toannexb -f mpegts  %s";

	private static final String Concat="  -i \"concat:%s|%s\" -c copy -bsf:a aac_adtstoasc -movflags +faststart %s";

	/**
	 * 修改分辨率
	 * @param videoPath 文件路径
	 * @param outPath 输出路径
	 * @param width 修改宽度
	 * @param height 修改高度
	 */
	public static void expandBoundaryMethod(String videoPath,String outPath,Integer width,Integer height){
		width = width==null?AUTO:width;
		height = height==null?AUTO:height;
		String cmd = String.format (EXPAND_BOUNDARY,videoPath,width,height,outPath);
		CmdUtil.execCmd (cmd);
	}

	/**
	 * 设置封面
	 * @param videoPath 文件路径
	 * @param imgPath 图片路径
	 * @param outPath 输出路径
	 */
	public static void settingCoverMethod(String videoPath,String imgPath,String outPath){
		String cmd = String.format (SETTING_COVER,videoPath,imgPath,outPath);
		String s = CmdUtil.execCmd (cmd);
		System.out.println (s);
	}


	private static int getTimelen(String timelen) {
		int min = 0;
		String strs[] = timelen.split(":");
		if (strs[0].compareTo("0") > 0) {
			// 秒
			min += Integer.valueOf(strs[0]) * 60 * 60;
		}
		if (strs[1].compareTo("0") > 0) {
			min += Integer.valueOf(strs[1]) * 60;
		}
		if (strs[2].compareTo("0") > 0) {
			min += Math.round(Float.valueOf(strs[2]));
		}
		return min;
	}
	public static void imageOverlayMethod(String coverImage,String fontImage,Integer width,Integer height,String output){
		String cmd = String.format (Image_OverLay,coverImage,fontImage,width,height,output);
		CmdUtil.execCmd (cmd);
	}
	public static void ImageSplitMethod(String imagePath,Integer width,Integer height,Integer sw,Integer sh,String output ){
		String cmd = String.format (Image_SPLIT,imagePath,width,height,sw,sh,output);
		CmdUtil.execCmd (cmd);
	}
	public static void VideoSplitMethod(String videoPath,String start,String length,String output ){
		String cmd = String.format (Video_SPLIT,start,videoPath,length,output);
		CmdUtil.execCmd (cmd);
	}

    public static void videoConvert(String path,String outPath) {
		String cmd = String.format(Video_COVERT,path,outPath);
		CmdUtil.execCmd(cmd);
    }

	public static void ImageToVodeoMethod(String path, String outPutPath) {
		String cmd = String.format(Image_TO_VIdeo,path,outPutPath);
		CmdUtil.execCmd(cmd);
	}

	public static void toVideoTs(String path, String s) {
		String cmd = String.format(Video_TS,path,s);
		CmdUtil.execCmd(cmd);
	}

	public static void ConcatMethod(String imgPath, String vPath, String outPut) {
		String cmd = String.format(Concat,imgPath,vPath,outPut);
		CmdUtil.execCmd(cmd);
	}
}

