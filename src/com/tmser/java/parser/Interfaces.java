/**
 * Mainbo.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.tmser.java.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author tjx1222
 * @version $Id: Magic.java, v 1.0 2016年11月6日 上午10:07:46 tjx1222 Exp $
 */
public class Interfaces implements JavaElement{
	public final String NAME = "interfaces";
	
	private List<Integer> interfaceIndexs;
	
	private int interfaceCount;
	
	private int size;
	
	private byte[] data;
	
	private int start;
	
	public Interfaces(byte[] classbytes, int start, int interfaceCount){
		this.interfaceCount = interfaceCount;
		this.start = start;
		parse(classbytes);
	}
	
	@Override
	public String print(){
		return "interface indexs " + interfaceIndexs;
	}
	

	/**
	 * @return
	 * @see com.tmser.java.parser.JavaElement#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * @return
	 * @see com.tmser.java.parser.JavaElement#getData()
	 */
	@Override
	public byte[] getData() {
		return data;
	}

	/**
	 * @param classbytes
	 * @see com.tmser.java.parser.JavaElement#parse(byte[])
	 */
	private void parse(byte[] classbytes) {
		this.size = 0;
		this.interfaceIndexs = new ArrayList<Integer>();
		int st = start;
		for(int i=0;i<interfaceCount;i++){
			this.size +=2;
			interfaceIndexs.add(Integer.valueOf(
					CodeUtils.getShort(Arrays.copyOfRange(classbytes,st,st+size))));
			st += 2;
		}
		
		this.data = Arrays.copyOfRange(classbytes, start, start+size);
	}

	/**
	 * @return
	 * @see com.tmser.java.parser.JavaElement#getEndPos()
	 */
	@Override
	public int getEndPos() {
		return start+size;
	}
	
	
}
