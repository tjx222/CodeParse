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
public class AccessFlags extends CommJavaElement {
	public final String NAME = "access_flags";
	
	public final int ACC_PUBLIC = 0x0001; //may be accessed from outside its package.
	
	public final int ACC_FINAL = 0x0010; //no subclasses allowed.

	public final int ACC_SUPER = 0x0020; //	Treat superclass methods specially when invoked by the invokespecial instruction.
	
	public final int ACC_INTERFACE = 0x0200;//	Is an interface, not a class.
			
	public final int ACC_ABSTRAC = 0x0400; //	Declared abstract; must not be instantiated.
			
	public final int ACC_SYNTHETIC = 0x1000; //	Declared synthetic; not present in the source code.
			
	public final int ACC_ANNOTATION	= 0x2000; //Declared as an annotation type.
			
	public final int ACC_ENUM =	0x4000; //	Declared as an enum type.
	
	private int accessFlags;
	
	public AccessFlags(byte[] classbytes, int start){
		super(classbytes,start);
		this.accessFlags = CodeUtils.getInt(getData());
	}
	
	@Override
	public void print(){
		System.out.println(Integer.toBinaryString(accessFlags));
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

	public int getAccessFlags() {
		return accessFlags;
	}
	
	
	
}
