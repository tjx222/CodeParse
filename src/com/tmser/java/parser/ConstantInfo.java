/**
 * Mainbo.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.tmser.java.parser;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * <pre>
 *
 * </pre>
 *
 * @author tjx1222
 * @version $Id: Magic.java, v 1.0 2016年11月6日 上午10:07:46 tjx1222 Exp $
 */
public abstract class ConstantInfo {
	
	public static enum ConstantType{
		UTF8((byte)0x01),INT((byte)0x03),FLOAT((byte)0x04),
		LONG((byte)0x05),DOUBLE((byte)0x06),CLASS((byte)0x07),
		STRING((byte)0x08),FIELD((byte)0x09),METHOD((byte)0x0a),
		INTERFACE((byte)0x0b),NAT((byte)0x0c),METHODHANDLE((byte)0x0f),
		METHODTYPE((byte)0x10),INVOKEDYNAMIC((byte)0x12);
		
		public final byte type;
		ConstantType(byte type){
			this.type = type;
		}
		
		public byte getType(){
			return type;
		}
		
		public static final ConstantType valeof(byte type){
			for(ConstantType ct : ConstantType.values()){
				if(ct.type == type){
					return ct;
				}
			}
			return null;
		}
		
	}
	
	protected byte tag;
	
	public static final ConstantInfo parse(byte[] classbytes,int startPos){
		ConstantInfo rs = null;
		int endpos = startPos + 1;
		byte[] tag = Arrays.copyOfRange(classbytes, startPos, endpos);
		//System.out.println(startPos + " -- " + endpos + " " + java.lang.Integer.toBinaryString(tag[0] & 0xff));
		switch(ConstantType.valeof(tag[0])){
		case UTF8:
			rs = new Utf8(classbytes,endpos);break;
		case INT:
			rs = new Integer(classbytes, endpos);break;
		case FLOAT:
			rs = new Float(classbytes, endpos);break;
		case LONG:
			rs = new Long(classbytes, endpos);break;
		case DOUBLE:
			rs = new Double(classbytes, endpos);break;
		case CLASS:
			rs = new Class(classbytes, endpos);break;
		case STRING:
			rs = new String(classbytes, endpos);break;
		case FIELD:
			rs = new Fieldref(classbytes, endpos);break;
		case METHOD:
			rs = new Methodref(classbytes, endpos);break;
		case INTERFACE:
			rs = new IntefaceMethod(classbytes, endpos);break;
		case NAT:
			rs = new NameAndType(classbytes, endpos);break;
		case METHODHANDLE:
			rs = new MethodHandle(classbytes, endpos);break;
		case METHODTYPE:
			rs = new MethodType(classbytes, endpos);break;
		case INVOKEDYNAMIC:
			rs = new InvokeDynamic(classbytes, endpos);break;
		default:
			break;
		}
		
		return rs;
	}
	
	public abstract ConstantType getType();
	
	public abstract int getSize();
	
	
	public static class Utf8 extends ConstantInfo{
		private int length;
		
		private java.lang.String value;
		
		public Utf8(byte[] classbytes,int startPos){
			this.length = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos, startPos+2));
			this.value = new java.lang.String(Arrays.copyOfRange(classbytes, startPos+2, 
						startPos+2+length),Charset.forName("utf-8"));
		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 2 + length;
		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getType()
		 */
		@Override
		public ConstantType getType() {
			return ConstantType.UTF8;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[value = ").append(value).append("]").toString();
		}
	}
	
	public static class Integer extends ConstantInfo{
		
		private int value;
		
		public Integer(byte[] classbytes,int startPos){
			this.value = CodeUtils.getInt(
					Arrays.copyOfRange(classbytes, startPos, startPos+4));
		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 4;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.INT;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[value = ").append(value).append("]").toString();
		}
	}
	
	public static class Float extends ConstantInfo{
		
		private float value;
		
		public Float(byte[] classbytes,int startPos){
			this.value = CodeUtils.getFloat(
					Arrays.copyOfRange(classbytes, startPos, startPos+4));
		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 4;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.FLOAT;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[value = ").append(value).append("]").toString();
		}
	}
	
	public static class Long extends ConstantInfo{
		private long value;
		
		public Long(byte[] classbytes,int startPos){
			this.value = CodeUtils.getLong(
					Arrays.copyOfRange(classbytes, startPos, startPos+8));
		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 8;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.LONG;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[value = ").append(value).append("]").toString();
		}
	}
	
	public static class Double extends ConstantInfo{
		private double value;
		
		public Double(byte[] classbytes,int startPos){
			this.value = CodeUtils.getDouble(
					Arrays.copyOfRange(classbytes, startPos, startPos+8));
		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 8;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.DOUBLE;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[value = ").append(value).append("]").toString();
		}
	}
	
	public static class Class extends ConstantInfo{
		private int index;
		
		public Class(byte[] classbytes,int startPos){
			this.index = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos, startPos+2));
		}
		
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 2;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.CLASS;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[index = ").append(index).append("]").toString();
		}
	}
	
	public static class String extends ConstantInfo{
		private int index;
		
		public String(byte[] classbytes,int startPos){
			this.index = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos, startPos+2));

		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 2;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.STRING;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[index = ").append(index).append("]").toString();
		}
	}
	
	public static class Fieldref extends ConstantInfo{
		private int classIndex;
		private int nameAndTypeIndex;
		
		public Fieldref(byte[] classbytes,int startPos){
			this.classIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos, startPos+2));
			this.nameAndTypeIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos+2, startPos+4));

		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 2 + 2;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.FIELD;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[classIndex = ").append(classIndex)
					.append("] , nameAndTypeIndex=[").append(nameAndTypeIndex).append("]").toString();
		}
	}
	
	public static class Methodref extends ConstantInfo{
		private int classIndex;
		private int nameAndTypeIndex;
		
		public Methodref(byte[] classbytes,int startPos){
			this.classIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos, startPos+2));
			this.nameAndTypeIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos+2, startPos+4));

		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 2 + 2;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.METHOD;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[classIndex = ").append(classIndex)
					.append("] , nameAndTypeIndex=[").append(nameAndTypeIndex).append("]").toString();
		}
	}
	
	public static class IntefaceMethod extends ConstantInfo{
		private int classIndex;
		private int nameAndTypeIndex;
		
		public IntefaceMethod(byte[] classbytes,int startPos){
			this.classIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos, startPos+2));
			this.nameAndTypeIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos+2, startPos+4));

		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 2 + 2;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.INTERFACE;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[classIndex = ").append(classIndex)
					.append("] , nameAndTypeIndex=[").append(nameAndTypeIndex).append("]").toString();
		}
	}
	
	public static class NameAndType extends ConstantInfo{
		private int nameIndex;
		private int descIndex;
		
		public NameAndType(byte[] classbytes,int startPos){
			
			this.nameIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos, startPos+2));
			this.descIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos+2, startPos+4));

		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 2 + 2;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.NAT;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[nameIndex = ").append(nameIndex)
					.append("] , descIndex=[").append(descIndex).append("]").toString();
		}
	}
	
	
	public static class MethodHandle extends ConstantInfo{
		private int referenceKind;
		private int referenceIndex;
		
		public MethodHandle(byte[] classbytes,int startPos){
			
			this.referenceKind = Arrays.copyOfRange(classbytes, startPos, startPos+1)[0] & 0xff;
			this.referenceIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos+1, startPos+3));

		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 1 + 2;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.METHODHANDLE;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[referenceKind = ").append(referenceKind)
					.append("] , referenceIndex=[").append(referenceIndex).append("]").toString();
		}
	}
	
	
	public static class MethodType extends ConstantInfo{
		
		private int descriptorIndex;
		
		public MethodType(byte[] classbytes,int startPos){
			
			this.descriptorIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos, startPos+2));

		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 2;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.METHODTYPE;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[descriptorIndex = ").append(descriptorIndex)
					.append("]").toString();
		}
	}
	
	public static class InvokeDynamic extends ConstantInfo{
		private int bootstrapMethodAttrIndex;
		private int nameAndTypeIndex;
		
		public InvokeDynamic(byte[] classbytes,int startPos){
			
			this.nameAndTypeIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos, startPos+2));
			this.bootstrapMethodAttrIndex = CodeUtils.getShort(
					Arrays.copyOfRange(classbytes, startPos+2, startPos+4));

		}
		/**
		 * @return
		 * @see com.tmser.java.parser.ConstantInfo#getSize()
		 */
		@Override
		public int getSize() {
			return 1 + 2 + 2;
		}
		
		@Override
		public ConstantType getType() {
			return ConstantType.INVOKEDYNAMIC;
		}
		
		@Override
		public java.lang.String toString(){
			return new StringBuilder(getType().name()).append(" Constant ")
					.append("[nameAndTypeIndex = ").append(nameAndTypeIndex)
					.append("] , bootstrapMethodAttrIndex=[").append(bootstrapMethodAttrIndex).append("]").toString();
		}
	}
	
	
}

