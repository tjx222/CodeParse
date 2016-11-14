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
 * @version $Id: JavaByte.java, v 1.0 2016年11月6日 上午11:10:35 tjx1222 Exp $
 */
public abstract class CommJavaElement implements JavaElement{
	
	private byte[] data;
	
	protected void parse(final byte[] classbytes){
		if(Objects.nonNull(classbytes)){
			data = Arrays.copyOfRange(classbytes,getPositionRange().start,getPositionRange().end);
		}
	}
	
	@Override
	public byte[] getData(){
		return data;
	}
	
	protected abstract Range getPositionRange();
}
