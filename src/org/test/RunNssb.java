package org.test;

import org.json.JSONObject;
import org.utils.AESUtils;

public class RunNssb {

	public static void main(String[] args) {
		
		String charset = "utf-8";
		
		try {
			
			String yhmm = AESUtils.MD5("a7654321");//
			JSONObject json = new JSONObject();
			json.put("tzlx", "W");//��ҵ
			json.put("yhdm", "371325751788249");//�ʺ� 370112069001351 91370322MA3BXG5Y64
			json.put("yhmm", yhmm);//���� MD5
			json.put("millis", ""+System.currentTimeMillis());
			json.put("zq", ""+60*60*1000);//���� ����
			//��ת��ַ
//			json.put("site", "/gpsqMobile.do?lx=wxgp");//��Ʊ����
			json.put("site", "/ysqmobile/report/wsfpdk_zyfpdk_mobile.jsp");
			
			String en_key = AESUtils.en2us(json.toString(), charset);
			System.err.println("en_key:"+en_key);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
