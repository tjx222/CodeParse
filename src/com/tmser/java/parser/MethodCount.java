/**
 * Mainbo.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.tmser.java.parser;

/**
 * <pre>
 *
 * </pre>
 *
 * @author tjx1222
 * @version $Id: Magic.java, v 1.0 2016年11月6日 上午10:07:46 tjx1222 Exp $
 */
public class MethodCount extends CommJavaElement{
	public final String NAME = "method_count";
	
	private int count;
	public MethodCount(byte[] classbytes,int start){
		super(classbytes,start);
		this.count = CodeUtils.getInt(getData());
	}
	
	@Override
	public String print(){
		return String.valueOf(count);
	}
	
	@Override
	protected Range getPositionRange(){
		return new Range(start,start+2);
	}

	/**
	 * @return
	 * @see com.tmser.java.parser.JavaElement#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}
	
	public int getCount() {
		return count;
	}
}
