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
public class ConstantPool implements JavaElement{
	public final String NAME = "constant_pool";
	
	private List<ConstantInfo> infos;
	
	private int constantPoolCount;
	
	private int size;
	
	private byte[] data;
	
	public ConstantPool(byte[] classbytes,int constantPoolCount){
		this.constantPoolCount = constantPoolCount;
		parse(classbytes);
	}
	
	@Override
	public String print(){
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for(ConstantInfo info : infos){
			sb.append("costant ").append(i++).append(": ")
			.append(info);
		}
		return sb.toString();
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
		this.infos = new ArrayList<ConstantInfo>();
		for(int i=0;i<constantPoolCount;i++){
			ConstantInfo ci = ConstantInfo.parse(classbytes,10+size);
			infos.add(ci);
			this.size += ci.getSize();
		}
		
		this.data = Arrays.copyOfRange(classbytes, 10, 10+size);
	}

	/**
	 * @return
	 * @see com.tmser.java.parser.JavaElement#getEndPos()
	 */
	@Override
	public int getEndPos() {
		return 10+size;
	}
	
	
}
