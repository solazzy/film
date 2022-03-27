package com.example.film.util;


import com.example.film.constant.BaseConstant;

public class FFmpegCmd {


	/**
	 * 修改分辨率
	 * @param videoPath 文件路径
	 * @param outPath 输出路径
	 * @param width 修改宽度
	 * @param height 修改高度
	 */
	public static void expandBoundaryMethod(String videoPath,String outPath,Integer width,Integer height){
		width = width==null? BaseConstant.AUTO:width;
		height = height==null?BaseConstant.AUTO:height;
		String cmd = String.format (BaseConstant.EXPAND_BOUNDARY,videoPath,width,height,outPath);
		CmdUtil.execCmd (cmd);
	}

	/**
	 * 设置封面
	 * @param videoPath 文件路径
	 * @param imgPath 图片路径
	 * @param outPath 输出路径
	 */
	public static void settingCoverMethod(String videoPath,String imgPath,String outPath){
		String cmd = String.format (BaseConstant.SETTING_COVER,videoPath,imgPath,outPath);
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
		String cmd = String.format (BaseConstant.Image_OverLay,coverImage,fontImage,width,height,output);
		CmdUtil.execCmd (cmd);
	}
	public static void ImageSplitMethod(String imagePath,Integer width,Integer height,Integer sw,Integer sh,String output ){
		String cmd = String.format (BaseConstant.Image_SPLIT,imagePath,width,height,sw,sh,output);
		CmdUtil.execCmd (cmd);
	}
	public static void VideoSplitMethod(String videoPath,String start,String length,String output ){
		String cmd = String.format (BaseConstant.Video_SPLIT,start,videoPath,length,output);
		CmdUtil.execCmd (cmd);
	}

	/**
	 * 视频分段
	 * @param start 开始时间
	 * @param videoPath 文件路径
	 * @param length 截取时长
	 * @param output 输出路径
	 */
	public static void VideoSplitToMethod(int start,String videoPath,int length,String output ){
		String cmd = String.format (BaseConstant.Video_SPLIT_TO,start,videoPath,length,output);
		CmdUtil.execCmd (cmd);
	}

	/**
	 * 提取视频的第一张图片作为封面
	 * @param filePath
	 * @param path
	 */

    public static void videoFirstImage(String filePath,String path) {
		String cmd = String.format(BaseConstant.VIDEO_FIRST_IMAGE,filePath,path,BaseConstant.IMAGE_NAME);
		CmdUtil.execCmd(cmd);
    }

	/**
	 * 整个16:9横屏视频全部填充黑色
	 * @param filePath
	 */
	public static void fillImageBlack(String filePath,String path) {
		String cmd = String.format(BaseConstant.FILL_IMAGE_BLACK,filePath,path,BaseConstant.IMAGE_NAME);
		CmdUtil.execCmd(cmd);
	}

	public static void ImageToVodeoMethod(String path, String outPutPath) {
		String cmd = String.format(BaseConstant.Image_TO_VIdeo,path,outPutPath);
		CmdUtil.execCmd(cmd);
	}

	public static void toVideoTs(String path, String s) {
		String cmd = String.format(BaseConstant.Video_TS,path,s);
		CmdUtil.execCmd(cmd);
	}

	public static void ConcatMethod(String imgPath, String vPath, String outPut) {
		String cmd = String.format(BaseConstant.Concat,imgPath,vPath,outPut);
		CmdUtil.execCmd(cmd);
	}
}

