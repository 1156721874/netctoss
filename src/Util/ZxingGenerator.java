package Util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
public class ZxingGenerator {
	/**
     * <p>Title: encode</p>
     * <p>Description: 生成二维码的实现代码</p>
     * @param content  要生成二维码的内容
     * @param imgpath  生成二维码图片的路径
     * @date
     */
    public void encode(String content,String imgpath) {  
        try {  
            BitMatrix byteMatrix;  
            
//            Hashtable hints= new Hashtable();  
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);  
             
            byteMatrix = new MultiFormatWriter().encode(new String(content.getBytes("GBK"),"iso-8859-1"),  
                    BarcodeFormat.QR_CODE, 200, 200);  
            File file = new File(imgpath);  
              
            MatrixToImageWriter.writeToFile(byteMatrix, "png", file);  
            System.out.println("成功生成二维码");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
 

 

  /**
   * <p>Title: decode</p>
   * <p>Description:解析二维码的实现代码 </p>
   * @param imgPath 二维码的路径
   * @return  返回解析的二维码内容
   * @date
   */
    public String decode(String imgPath) {  
        String response = "";
        try {  
            Reader reader = new MultiFormatReader();  
          
            File file = new File(imgPath);  
            BufferedImage image;  
            try {  
                image = ImageIO.read(file);  
                if (image == null) {  
                    System.out.println("Could not find image");  
                }  
                LuminanceSource source = new BufferedImageLuminanceSource(image);  
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
                Result result;  
                Hashtable hints = new Hashtable();  
                hints.put(DecodeHintType.CHARACTER_SET, "GBK");  
                result = new MultiFormatReader().decode(bitmap, hints);  
                
                System.out.println("解析出的结果如下：");
                System.out.println("result = "+ result.toString());
                   System.out.println("resultFormat = "+ result.getBarcodeFormat());
                   System.out.println("resultText = "+ result.getText());
                   
                   response = result.toString();
            } catch (IOException ioe) {  
                System.out.println(ioe.toString());  
            } catch (ReaderException re) {  
                System.out.println(re.toString());  
            }  
 
        } catch (Exception ex) {  
             ex.printStackTrace();
        }
        
        return response;
    }  
    public static void main(String[] args) {
		ZxingGenerator oper = new ZxingGenerator();
		oper.encode("http://localhost:8080/netctoss/login/toLogin", "E:\\Study\\tarenaspace\\netctoss\\WebRoot\\images\\ZxingImage\\a.png");
		String code = oper.decode("E:\\Study\\tarenaspace\\netctoss\\WebRoot\\images\\ZxingImage\\a.png");
		System.out.println(code);
		
		List<Integer> list=new ArrayList<Integer>();
		Map<Integer,String> map = new HashMap<Integer,String>();
		
	}
}
