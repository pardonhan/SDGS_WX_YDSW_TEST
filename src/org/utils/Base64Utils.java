package org.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {
	
	private static Base64 base64 = null;
	
	/**
	 * 加密
	 * @param base
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String enBase(String base,String charset){
		
		base64 = new Base64();
		
		String result;
		try {
			result = new String(base64.encodeBase64(base.getBytes(charset)),charset);
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}
    	
		return result;
	}
	
	/**
	 * 解密
	 * @param base
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String deBase(String base,String charset){
		
		base64 = new Base64();
		
		String result;
		try {
			result = new String(base64.decodeBase64(base.getBytes(charset)),charset);
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}
    	
		return result;
	}

	/**
	 * 加密
	 * @param base
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static byte[] enBase2Byte(String base,String charset){
		
		base64 = new Base64();
		
		byte[] result;
		try {
			result = base64.encodeBase64(base.getBytes(charset));
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}
    	
		return result;
	}
	
	/**
	 * 解密
	 * @param base
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static byte[] deBase2Byte(String base,String charset){
		
		base64 = new Base64();
		
		byte[] result;
		try {
			result = base64.decodeBase64(base.getBytes(charset));
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}
    	
		return result;
	}
	
	/**
	 * 加密
	 * @param base
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String enByte2Base(byte[] base,String charset){
		
		base64 = new Base64();
		
		String result;
		try {
			result = new String(base64.encodeBase64(base),charset);
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}
    	
		return result;
	}
	
	/**
	 * 解密
	 * @param base
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String deByte2Base(byte[] base,String charset){
		
		base64 = new Base64();
		
		String result;
		try {
			result = new String(base64.decodeBase64(base),charset);
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}
    	
		return result;
	}
}
