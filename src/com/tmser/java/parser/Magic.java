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
public class Magic extends CommJavaElement {
	public final String NAME = "magic";
	
	public Magic(final byte[] classbytes){
		super(classbytes,0);
	}
	
	@Override
	public void print(){
		for(byte b : getData()){
			System.out.printf("%x",b);
		}
	}
	
	@Override
	protected Range getPositionRange(){
		return new Range(0,4);
	}

	/**
	 * @return
	 * @see com.tmser.java.parser.JavaElement#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}
	
}
