package com.authstr.sd.login.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * 验证码图片生成类
 * @author authstr
 * @time 2019年2月16日20:18:15
 */
public class ValidateCode {
    public static void main(String[] args) {
        //示例
//		// 设置响应的类型格式为图片格式
//		response.setContentType("image/jpeg");
//		// 禁止图像缓存。
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires", 0);
//		ValidateCode instance = new ValidateCode();
//		CookieUtil.setCookie(response, "scaptcha", instance.getCode().toUpperCase(), null, 1 * 60);
//		try {
//			instance.write(response.getOutputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    }
    // 图片的宽度。
    private int width = 120;
    // 图片的高度。  
    private int height = 40;
    // 验证码字符个数  
    private int codeCount = 4;
    // 验证码干扰线数  
    private int lineCount = 40;
    // 验证码  
    private String code = null;
    // 验证码图片Buffer  
    private BufferedImage buffImg = null;

    private char[] codeSequence = {'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h',
            'i', 'J', 'j', 'K', 'k', 'L', 'M', 'm', 'N', 'n', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w',
            'X', 'x', 'Y', 'y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};

    // 生成随机数  
    private Random random = new Random();

    public ValidateCode() {
        this.createCode();
    }

    /**
     * @param width  图片宽
     * @param height 图片高
     */
    public ValidateCode(int width, int height) {
        this.width = width;
        this.height = height;
        this.createCode();
    }

    /**
     * @param width     图片宽
     * @param height    图片高
     * @param codeCount 字符个数
     * @param lineCount 干扰线条数
     */
    public ValidateCode(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }

    public void createCode() {
        Random heightRandom = new Random();
        int codeX = 0;
        int fontHeight = 0;
        fontHeight = height - heightRandom.nextInt(7) - 7;// 字体的高度
        codeX = width / (codeCount + 1);// 每个字符的宽度

        // 图像buffer  
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 将图像填充为白色  
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体  
        ImgFontByte imgFont = new ImgFontByte();
        Font font = imgFont.getFont(fontHeight);
        g.setFont(font);

        // 绘制干扰线  
        for (int i = 0; i < lineCount; i++) {
            int xs = getRandomNumber(width);
            int ys = getRandomNumber(height);
            int xe = xs + getRandomNumber(width / 8);
            int ye = ys + getRandomNumber(height / 8);
            g.setColor(getRandomColor());
            g.drawLine(xs, ys, xe, ye);
        }


        StringBuffer randomCode = new StringBuffer();

//        int x = 5;
        // 随机产生验证码字符  
        for (int i = 0; i < codeCount; i++) {
            int h = height - 8;
            int w = (i + 1) * codeX + heightRandom.nextInt(4);
            String strRand = String.valueOf(codeSequence[random
                    .nextInt(codeSequence.length)]);
            // 设置字体颜色  
            g.setColor(getRandColor(0, 160));

            int degree = new Random().nextInt() % 10;
            g.rotate(degree * Math.PI / 180, (w + codeX - 5) / 2, (h + fontHeight) / 2);
            // 设置字体位置  
            g.drawString(strRand, w, h);  //getRandomNumber(height / 2) + 25
            g.rotate(-degree * Math.PI / 150, (w + codeX - 5) / 2, (h + fontHeight) / 2);
            randomCode.append(strRand);
        }
        code = randomCode.toString();
    }

    /**
     * 获取随机颜色
     */
    private Color getRandomColor() {
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r, g, b);
    }

    public Color getRandColor(int fc, int bc) {//给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 获取随机数
     */
    private int getRandomNumber(int number) {
        return random.nextInt(number);
    }

    public void write(String path) throws IOException {
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code;
    }

    /**
     * 字体样式类
     */
    class ImgFontByte {
        public Font getFont(int fontHeight) {
            return new Font("Arial", Font.PLAIN, fontHeight);
        }
    }

}
