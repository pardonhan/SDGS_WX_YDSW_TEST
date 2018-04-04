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
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws java.lang.Exception
	 */
	private static byte[] getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
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
