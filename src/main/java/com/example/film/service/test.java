package com.example.film.service;

import sun.awt.image.BufferedImageGraphicsConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class test {
    public static void createImage2(String str, Font font, File outFile,
                                    Integer width, Integer height) throws Exception {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImageGraphicsConfig config = BufferedImageGraphicsConfig.getConfig(bufferedImage);
        bufferedImage =config.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        Graphics g = bufferedImage.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout tl = new TextLayout(str, font, frc);
        //字在页面的位置
        Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(0,250));
        //白边的范围
        g2.setStroke(new BasicStroke(8));
        g2.setColor(Color.RED);
        ((Graphics2D) g).draw(sha);
        g2.setColor(Color.WHITE);
        g2.fill(sha);
        // 输出png图片
        ImageIO.write(bufferedImage, "png", outFile);
    }
    public static void main(String[] args) throws Exception {
        String name= "hello java";
        createImage2(name, new Font("楷体", Font.BOLD, 150), new File(
                "/Users/shixin/Desktop/test/a.png"), 800, 600);
    }

}
