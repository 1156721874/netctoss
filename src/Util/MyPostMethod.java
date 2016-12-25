package Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class MyPostMethod {

	public static void main(String[] args) {

	}
	
	public static String doMethod(String url,Map<String,String> paranmap){
		PostMethod service = new PostMethod(url);
		String responseString = "";
		try{
			if(paranmap!=null){
				List list = new ArrayList();
				for(Map.Entry<String, String> entryport:paranmap.entrySet()){
					NameValuePair nvp = new NameValuePair(entryport.getKey(),paranmap.get(entryport.getKey()));
					list.add(nvp);
				}
				int statuscode = HttpClientManager.getHttpClient().executeMethod(service);
				if(statuscode==200){
					byte[] responseBody = service.getResponseBody();
					responseString = new String(responseBody,"UTF-8");
					return responseString;
				}else{
					throw new RuntimeException();
				}
				
			}
		}catch(HttpException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			service.releaseConnection();
		}
		return responseString;
	}

}
