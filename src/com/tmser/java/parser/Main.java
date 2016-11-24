/**
 * Mainbo.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.tmser.java.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * <pre>
 *
 * </pre>
 *
 * @author tjx1222
 * @version $Id: Main.java, v 1.0 2016年10月31日 下午9:35:39 tjx1222 Exp $
 */
public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException {
		byte[] buffer = null;
			buffer = Files.readAllBytes(Paths.get(
					ClassLoader.getSystemResource("com/tmser/java/parser/Main.class")
					.toURI()));
			System.out.println();
			byte[] newData = Arrays.copyOfRange(buffer, 0, 4);
			for(byte b : newData){
				System.out.printf("%x",b);
				System.out.println();
				System.out.print(Integer.toBinaryString(b & 0xff));
				System.out.println();

			}
			byte bt = (byte) 0b11001100;
			System.out.println(Integer.toBinaryString(bt & 0xff));
			
			ClassStruct cs = new ClassStruct(buffer);
			System.out.println(cs.toString());
	}
}
