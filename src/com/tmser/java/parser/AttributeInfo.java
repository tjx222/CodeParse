/**
 * Mainbo.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.tmser.java.parser;

import java.util.Arrays;
import java.util.Objects;

/**
 * <pre>
 *
 * </pre>
 *
 * @author tjx1222
 * @version $Id: Magic.java, v 1.0 2016年11月6日 上午10:07:46 tjx1222 Exp $
 */
public class AttributeInfo extends CommJavaElement{
	
	public final String NAME = "attribute_info";
	
	public AttributeInfo(byte[] classbytes,int start){
		super(classbytes,start);
	}
	
	private int attrNameIndex;
	
	private long attrLength;
	
	private byte[] infoContent;
	
	private Range range;

	/**
	 * @return
	 * @see com.tmser.java.parser.JavaElement#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * 
	 * @see com.tmser.java.parser.JavaElement#print()
	 */
	@Override
	public void print() {
		System.out.println(NAME+": [attrNameIndex = " + attrNameIndex +"], [attrLength ="+
				attrLength + "]");
	}
	
	@Override
	protected void parse(final byte[] classbytes){
		if(Objects.nonNull(classbytes)){
			attrNameIndex = CodeUtils.getInt(
					Arrays.copyOfRange(classbytes,start,start+2));
			attrLength = CodeUtils.getLong(
					Arrays.copyOfRange(classbytes,start+2,start+6));
			range  = new Range(start, (int)(start+ attrLength + 6));
			infoContent = Arrays.copyOfRange(classbytes,start+6,range.end);
			data = Arrays.copyOfRange(classbytes,range.start,range.end);
		}
	}

	/**
	 * @return
	 * @see com.tmser.java.parser.CommJavaElement#getPositionRange()
	 */
	@Override
	protected Range getPositionRange() {
		return range;
	}

	public int getAttrNameIndex() {
		return attrNameIndex;
	}

	public long getAttrLength() {
		return attrLength;
	}

	public byte[] getInfoContent() {
		return infoContent;
	}
	
}
