package org.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESUtils {
	
	private static String key = "sd-hhzs-ydsw-kr8";
	private static String ivParameter = "8934373946220381";
	
	private static final int key_len = 16;
	
	private static final boolean is_keygen = false;
	
	public static String en2s(String content,String charset){
		try {
			byte[] b_data = encode(content.getBytes(charset));
			return bytes2base64(b_data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String de2s(String content,String charset){
		try {
			byte[] b_data = base642bytes(content, charset);
			byte[] b_d_data = decode(b_data);
			return new String(b_d_data,charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String en2hex(String content,String charset){
		try {
			byte[] b_data = encode(content.getBytes(charset));
			return bytes2hex(b_data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String de2hex(String content,String charset){
		try {
			byte[] b_data = hex2bytes(content);
			byte[] b_d_data = decode(b_data);
			return new String(b_d_data,charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String en2us(String content,String charset){
		try {
//			System.out.println("normal:\n"+content);
			byte[] b_data = encode(content.getBytes(charset));
//			System.out.println("en-16:\n"+bytes2hex(b_data));
			
			String s_data = bytes2base64(b_data);
//			System.out.println("base64:\n"+s_data);
			return java.net.URLEncoder.encode(s_data, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String de2us(String content,String charset){
		try {
			String s_data = java.net.URLDecoder.decode(content, charset);
			byte[] b_data = base642bytes(s_data, charset);
			byte[] b_d_data = decode(b_data);
			return new String(b_d_data,charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static SecretKeySpec getKeyV2(int len){
		
		try {
			byte[] key_array = key.getBytes();
			
	        //��ȡ��������Կ������ʽ  
	        byte[] enkey = new byte[len];
	        if(key_array != null && key_array.length > 0){
	        	for (int i = 0; i < key_array.length; i++) {
	        		if(i < len){
	        			enkey[i] = key_array[i];
	        		}
				}
	        	System.err.println("key-16:\n"+bytes2hex(enkey));
	            System.err.println("key-10:\n"+bytes2dec(enkey));
				System.err.println("key-2:\n"+bytes2bin(enkey));
		        
		        return new SecretKeySpec(enkey, "AES");
	        }
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static SecretKeySpec getKey(){
		
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");  
            kgen.init(128, new SecureRandom(key.getBytes()));  
            SecretKey secretKey = kgen.generateKey();  
            byte[] enCodeFormat = secretKey.getEncoded();  
            System.err.println("key-16:\n"+bytes2hex(enCodeFormat));
            System.err.println("key-10:\n"+bytes2dec(enCodeFormat));
			System.err.println("key-2:\n"+bytes2bin(enCodeFormat));
            return new SecretKeySpec(enCodeFormat, "AES"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static byte[] encode(byte[] content){
		
		try {
			SecretKeySpec key = null;
			if(is_keygen){
				key = getKey();
			}else{
				key = getKeyV2(key_len);
			}
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// ����������   
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);// ��ʼ��   
            return cipher.doFinal(content);  // ����   
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static byte[] decode(byte[] content){
		
		try {
			SecretKeySpec key = null;
			if(is_keygen){
				key = getKey();
			}else{
				key = getKeyV2(key_len);
			}  
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// ����������   
            cipher.init(Cipher.DECRYPT_MODE, key, iv);// ��ʼ��   
            return cipher.doFinal(content);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String bytes2bin(byte[] content){
		
		StringBuffer sb = new StringBuffer(content.length);
    	for (int i = 0; i < content.length; i++) {
			sb.append(Integer.toBinaryString(content[i]));
    	}
    	return sb.toString();
	}
	
	public static String bytes2dec(byte[] content){
		
		StringBuffer sb = new StringBuffer(content.length);
    	for (int i = 0; i < content.length; i++) {
			sb.append((int)content[i]);
    	}
    	return sb.toString();
	}
	
    /**
     * ���ֽ�����תΪ16�����ַ�
     * @param content �ֽ�����
     * @return 16�����ַ�
     */
	public static String bytes2hex(byte[] content){
		
		StringBuffer sb = new StringBuffer(content.length);
    	String sTemp;
    	for (int i = 0; i < content.length; i++) {
			sTemp = Integer.toHexString(0xFF & content[i]);
			if (sTemp.length() < 2)
			sb.append(0);
			sb.append(sTemp.toUpperCase());
    	}
    	return sb.toString();
	}
	
    /**
     * ��16�����ַ�ת�����ֽ�����
     * @param content 16�����ַ�
     * @return �ֽ�����
     */
    public static byte[] hex2bytes(String content) {  
        int len = (content.length() / 2);  
        byte[] result = new byte[len];  
        char[] achar = content.toCharArray();  
        for (int i = 0; i < len; i++) {  
            int pos = i * 2;  
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));  
        }  
        return result;  
    }  
    
    private static byte toByte(char c) {  
    	return (byte) "0123456789ABCDEF".indexOf(c);  
    }  
    
    public static String bytes2base64(byte[] content){
    	try {
    		return new BASE64Encoder().encode(content);  
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static byte[] base642bytes(String content,String charset){
    	BASE64Decoder decoder = new BASE64Decoder();  
        try {  
        	return decoder.decodeBuffer(content);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;
    }

	/**
	 * MD5   加密
	 */
	public final static String MD5(String s){ 
		   char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}; 
		   try { 
		   byte[] strTemp = s.getBytes(); 
		   MessageDigest mdTemp = MessageDigest.getInstance("MD5"); 
		   mdTemp.update(strTemp); 
		   byte[] md = mdTemp.digest(); 
		   int j = md.length; 
		   char str[] = new char[j * 2]; 
		   int k = 0; 
		   for (int i = 0; i < j; i++) { 
		   byte byte0 = md[i]; 
		   str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
		   str[k++] = hexDigits[byte0 & 0xf]; 
		   } 
		   return new String(str); 
		   } 
		   catch (Exception e){ 
		   return null; 
		   } 
	}
    
}
