/**
 * Mainbo.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.tmser.java.parser;

import java.nio.charset.Charset;

/**
 * <pre>
 *
 * </pre>
 *
 * @author tjx1222
 * @version $Id: CodeUtils.java, v 1.0 2016年11月6日 上午11:50:14 tjx1222 Exp $
 */
public class CodeUtils {

	public static byte[] getBytes(short data)
    {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        return bytes;
    }

    public static byte[] getBytes(char data)
    {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (data);
        bytes[1] = (byte) (data >> 8);
        return bytes;
    }

    public static byte[] getBytes(int data)
    {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }

    public static byte[] getBytes(long data)
    {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data >> 8) & 0xff);
        bytes[2] = (byte) ((data >> 16) & 0xff);
        bytes[3] = (byte) ((data >> 24) & 0xff);
        bytes[4] = (byte) ((data >> 32) & 0xff);
        bytes[5] = (byte) ((data >> 40) & 0xff);
        bytes[6] = (byte) ((data >> 48) & 0xff);
        bytes[7] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }

    public static byte[] getBytes(float data)
    {
        int intBits = Float.floatToIntBits(data);
        return getBytes(intBits);
    }

    public static byte[] getBytes(double data)
    {
        long intBits = Double.doubleToLongBits(data);
        return getBytes(intBits);
    }

    public static byte[] getBytes(String data, String charsetName)
    {
        Charset charset = Charset.forName(charsetName);
        return data.getBytes(charset);
    }

    public static byte[] getBytes(String data)
    {
        return getBytes(data, "UTF-8");
    }

    
    public static short getShort(byte[] bytes)
    {
    	return (short) getInt(bytes);
    }

    public static char getChar(byte[] bytes)
    {
        return (char) getInt(bytes);
    }

    public static int getInt(byte[] bytes)
    {
        int end, len = end = bytes.length > 4 ? 4 : bytes.length;
        int sum = 0;
        for(int i=0;i<end;i++){
        	int n = bytes[i] & 0xff;
        	n <<= (--len) * 8;
        	sum = n + sum;
        }
    	return sum;
    }
   
    public static long getLong(byte[] bytes)
    {
        int end, len = end = bytes.length > 8 ? 8 : bytes.length;
        long sum = 0;
        for(int i=0;i<end;i++){
        	long n = bytes[i] & 0xff;
        	n <<= (--len) * 8;
        	sum = n + sum;
        }
    	return sum;
    }

    public static float getFloat(byte[] bytes)
    {
        return Float.intBitsToFloat(getInt(bytes));
    }

    public static double getDouble(byte[] bytes)
    {
        long l = getLong(bytes);
        return Double.longBitsToDouble(l);
    }

    public static String getString(byte[] bytes, String charsetName)
    {
        return new String(bytes, Charset.forName(charsetName));
    }

    public static String getString(byte[] bytes)
    {
        return getString(bytes, "UTF-8");
    }
    
    public static void main(String[] args) {
    	byte[] i = new byte[]{0x00,0x01};
		System.out.println(getShort(i));
	}

}
