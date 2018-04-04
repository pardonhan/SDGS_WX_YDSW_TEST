package org.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
public class DESPlus_Pub {

	private static byte[] iv = { 1, 9, 3, 7, 3, 6, 2, 4 };
	private static String strDefaultKey = "LinYiShiGuoShuiJuWangShangShuiWuJu-HHzs-8365";
	public static String decryptU(String strIn)throws Exception{
		return java.net.URLDecoder.decode(decryptDES(strIn, strDefaultKey), "UTF-8");
	}
	public static String encryptU(String encryptString) throws Exception {
		return encryptDES(encryptString, strDefaultKey);
	}
	public static String decrypt(String strIn)throws Exception{
		return decryptDES(strIn, strDefaultKey);
//		return strIn;
//		return new String(decryptDES(strIn, strDefaultKey),"UTF-8");   
	}
	public static String encryptDES(String encryptString, String encryptKey) throws Exception {

		IvParameterSpec zeroIv = new IvParameterSpec(iv);

		SecretKeySpec key = new SecretKeySpec(getKey(encryptKey.getBytes()), "DES");

		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);

		byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));

		return Base64Utils.enByte2Base(encryptedData, "UTF-8");

	}
	/**
	 * ��ָ���ַ���������Կ����Կ������ֽ����鳤��Ϊ8λ ����8λʱ���油0������8λֻȡǰ8λ
	 * 
	 * @param arrBTmp
	 *            ���ɸ��ַ������ֽ�����
	 * @return ���ɵ���Կ
	 * @throws java.lang.Exception
	 */
	private static byte[] getKey(byte[] arrBTmp) throws Exception {
		// ����һ���յ�8λ�ֽ����飨Ĭ��ֵΪ0��
		byte[] arrB = new byte[8];

		// ��ԭʼ�ֽ�����ת��Ϊ8λ
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// ������Կ
//		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return arrB;
	}
	public static String decryptDES(String decryptString, String decryptKey)

	              throws Exception {

	          byte[] byteMi = Base64Utils.deBase2Byte(decryptString, "UTF-8");

	          IvParameterSpec zeroIv = new IvParameterSpec(iv);

	          SecretKeySpec key = new SecretKeySpec(getKey(decryptKey.getBytes()), "DES");

	          Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

	          cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);

	          byte decryptedData[] = cipher.doFinal(byteMi);

	 

	         return new String(decryptedData,"UTF-8");

	     }
	 /**
	    * MD5   ����
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
