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
 * @version $Id: Magic.java, v 1.0 2016��11��6�� ����10:07:46 tjx1222 Exp $
 */
public class MinorVersion extends CommJavaElement {
	public final String NAME = "minor_version";
	
	private int minorVersion;
	
	public MinorVersion(final byte[] classbytes){
		super(classbytes,6);
		this.minorVersion = CodeUtils.getInt(getData());
	}
	
	@Override
	public String print(){
		return String.valueOf(minorVersion);
	}
	
	@Override
	protected Range getPositionRange(){
		return new Range(4,6);
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
