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
 * @version $Id: JavaByte.java, v 1.0 2016年11月6日 上午11:10:35 tjx1222 Exp $
 */
public interface JavaElement {

	String getName();
	byte[] getData();
	void print();
	int getEndPos();
}
