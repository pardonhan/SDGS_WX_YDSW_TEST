package org.utils;

import java.io.IOException;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SoapUtils {

	public static String exec_pub(String namespace,String address,String method,List<ParamData<?>> params,boolean secret){
		
		HttpTransportSE ht = null;
		try {
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
			SoapObject soapObject = new SoapObject(namespace, method);
			
			for (ParamData<?> param : params) {
				String val = String.valueOf(param.getData());
				if(secret){
					soapObject.addProperty("args", DESPlus_Pub.encryptU(val));//�����޼���	
				}else{
					soapObject.addProperty("args", val);//�����޼���	
				}
			}
			
			envelope.setOutputSoapObject(soapObject);
//			envelope.dotNet = true;
			
			ht = new HttpTransportSE(address, 5*60*1000);
//			ht = new HttpTransportSE(address);
//			ht.debug = true;
			
			ht.call(null, envelope);
			if(envelope.getResponse() != null){
				SoapObject soapResult = (SoapObject) envelope.bodyIn;
				Object detail = (Object)soapResult.getProperty(0);
				return detail.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ht != null){ht.getServiceConnection().disconnect();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "";
	}
}
