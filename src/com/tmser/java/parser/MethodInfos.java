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
public class MethodInfos extends CommJavaElement{
	
	public final String NAME = "method_infos";
	
	private List<MethodInfo> infos;
	
	private int methodCount;
	
	private byte[] data;
	
	private Range range;
	
	public MethodInfos(byte[] classbytes,int start, int methodCount){
		this.methodCount = methodCount;
		this.start = start;
		parse(classbytes);
	}
	
	@Override
	public void print(){
		for(MethodInfo info : infos){
			info.print();
		}
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
	@Override
	protected void parse(final byte[] classbytes){
		this.infos = new ArrayList<MethodInfo>();
		int st = start;
		for(int i=0;i<methodCount;i++){
			MethodInfo ci = new MethodInfo(classbytes,st);
			infos.add(ci);
			st = ci.getEndPos();
		}
		
		range  = new Range(start, st);
		this.data = Arrays.copyOfRange(classbytes, start, st);
	}

	
	/**
	 * @return
	 * @see com.tmser.java.parser.CommJavaElement#getPositionRange()
	 */
	@Override
	protected Range getPositionRange() {
		return range;
	}
	
}
