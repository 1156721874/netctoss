package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class testwater {
	 /** 
	 * 给图片添加水印 
	* 
	 * @param filePath 
	 * 需要添加水印的图片的路径 
	* @param markContent 
	 * 水印的文字 
	* @param markContentColor 
	 * 水印文字的颜色 
	* @param qualNum 
	 * 图片质量 
	* @return 
	 */ 
	 public static boolean createMark(String filePath, String markContent, Color markContentColor, float qualNum) { 
	 ImageIcon imgIcon = new ImageIcon(filePath); 
	 Image theImg = imgIcon.getImage(); 
	 Font font=new Font("SansSerif",Font.ITALIC,48);
	 int width = theImg.getWidth(null); 
	 int height = theImg.getHeight(null); 
	 BufferedImage bimage = new BufferedImage(width, height, 
	 BufferedImage.TYPE_INT_RGB); 
	 Graphics2D g = bimage.createGraphics(); 
	 g.setColor(markContentColor); 
	 g.setFont(font);
	 g.setBackground(Color.white); 
	 g.drawImage(theImg, 0, 0, null); 
	 g.drawString(markContent, width / 5, height / 5); // 添加水印的文字和设置水印文字出现的内容 
	 g.rotate(180,0,0);
	 g.dispose(); 
	 try { 
	 FileOutputStream out = new FileOutputStream(filePath); 
	 JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
	 JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage); 
	 param.setQuality(qualNum, true); 
	 encoder.encode(bimage, param); 
	 out.flush();
	 out.close(); 
	 } catch (Exception e) { 
	 return false; 
	 } 
	 return true; 
	 } 
	 
	 public static void main(String[] args) {
		 testwater.createMark("E:\\Amusement\\新建文件夹 (7)\\水印.jpg","oooooooooooooooooooooooooo",Color.RED,0.5f);
	}
}
