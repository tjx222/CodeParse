/**
 * Mainbo.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.tmser.java.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <pre>
 *
 * </pre>
 *
 * @author tjx1222
 * @version $Id: Magic.java, v 1.0 2016年11月6日 上午10:07:46 tjx1222 Exp $
 */
public class FieldInfo  extends CommJavaElement{
	public final String NAME = "field_info";
	
	private int accessTag;
	
	private int nameIndex;
	
	private int descIndex;
	
	private int attrCount;
	
	private List<AttributeInfo> attrs;
	
	private Range range;
	
	public FieldInfo(byte[] classbytes, int start){
		super(classbytes,start);
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
		 * 
		 * @see com.tmser.java.parser.JavaElement#print()
		 */
		@Override
		public String print() {
			return new StringBuilder("field ").append(NAME)
					.append(": [accessTag = ").append(accessTag)
					.append("], [nameIndex = ").append(nameIndex)
					.append("],[descIndex = ").append(descIndex)
					.append("],[attrCount = ").append(attrCount).append("]").toString();
		}

		/**
		 * @return
		 * @see com.tmser.java.parser.CommJavaElement#getPositionRange()
		 */
		@Override
		protected Range getPositionRange() {
			return range;
		}
		
		@Override
		protected void parse(final byte[] classbytes){
			if(Objects.nonNull(classbytes)){
				accessTag = CodeUtils.getInt(
						Arrays.copyOfRange(classbytes,start,start+2));
				
				nameIndex = CodeUtils.getInt(
						Arrays.copyOfRange(classbytes,start+2,start+4));
				
				descIndex = CodeUtils.getInt(
						Arrays.copyOfRange(classbytes,start+4,start+6));
				
				attrCount = CodeUtils.getInt(
						Arrays.copyOfRange(classbytes,start+6,start+8));
				
				int st = start+8;
				attrs = new ArrayList<AttributeInfo>(attrCount);
				for(int i =0 ;i < attrCount;i++){
					AttributeInfo attr = new AttributeInfo(classbytes, st);
					attrs.add(attr);
					st = attr.getEndPos();
				}
				
				range  = new Range(start, st);
				data = Arrays.copyOfRange(classbytes,range.start,range.end);
			}
		}

		public int getAccessTag() {
			return accessTag;
		}

		public int getNameIndex() {
			return nameIndex;
		}

		public int getDescIndex() {
			return descIndex;
		}

		public int getAttrCount() {
			return attrCount;
		}

		public List<AttributeInfo> getAttrs() {
			return attrs;
		}
		
	
}

