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
public class ConstantPoolCount extends CommJavaElement {
	public final String NAME = "constant_pool_count";
	
	private int count;
	
	public ConstantPoolCount(byte[] classbytes){
		super(classbytes,8);
		this.count = CodeUtils.getInt(getData());
	}
	
	@Override
	public void print(){
		System.out.printf("%d",count);
	}
	
	@Override
	protected Range getPositionRange(){
		return new Range(8,10);
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
