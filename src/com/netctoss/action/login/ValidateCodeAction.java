package com.netctoss.action.login;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.netctoss.action.BaseAction;

import Util.ImageUtil;

public class ValidateCodeAction extends BaseAction{

	private InputStream imageStream;
	
	public String execute(){
		//调用组件生成图片和验证码
		Map<String,BufferedImage> imagemap = ImageUtil.createImage();
		//将验证码记录到session
		String imageCode = imagemap.keySet().iterator().next();
		session.put("imageCode",imageCode);
		
		//将生成的图片转换成输入流，赋值给输出属性
		BufferedImage image = imagemap.get(imageCode);
		try {
			imageStream = ImageUtil.getInputStream(image);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		//返回success,找到对应的result
		return "success";
	}

	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	
}
