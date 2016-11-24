/**
 * Mainbo.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.tmser.java.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  类文件结构解析图
 * </pre>
 *
 * @author tjx1222
 * @version $Id: ClassStruct.java, v 1.0 2016年11月21日 下午9:51:13 tjx1222 Exp $
 */
public class ClassStruct {
	
	private List<JavaElement>  elements = new ArrayList<>();
	
	public ClassStruct(byte[] classbytes){
		this.magic =  new Magic(classbytes);
		this.minVersion = new MinorVersion(classbytes);
		this.majorVersion = new MajorVersion(classbytes);
		this.constantPoolCount = new ConstantPoolCount(classbytes);
		this.constantPool = new ConstantPool(classbytes, constantPoolCount.getCount());
		this.accessFlags = new AccessFlags(classbytes, this.constantPool.getEndPos());
		this.thisClass = new ThisClass(classbytes, this.accessFlags.getEndPos());
		this.superClass = new SuperClass(classbytes, this.thisClass.getEndPos());
		this.interfaceCount = new InterfaceCount(classbytes, this.superClass.getEndPos());
		this.interfaces = new Interfaces(classbytes, 
				this.interfaceCount.getEndPos(), this.interfaceCount.getCount());
		this.fieldCount = new FieldCount(classbytes, this.interfaces.getEndPos());
		this.filedInfos = new FiledInfos(classbytes, this.fieldCount.getEndPos(),
				this.fieldCount.getCount());
		this.methodCount = new MethodCount(classbytes, this.filedInfos.getEndPos());
		this.methodInfos = new MethodInfos(classbytes, this.methodCount.getEndPos(), 
				this.methodCount.getCount());
		
		this.attributeCount = new AttributeCount(classbytes, this.methodInfos.getEndPos());
		this.attributeInfos = new AttributeInfos(classbytes, this.attributeCount.getEndPos(), 
				this.getAttributeCount().getCount());
		
		elements.add(this.attributeInfos);
		elements.add(this.attributeCount);
		elements.add(this.methodInfos);
		elements.add(this.methodCount);
		elements.add(this.filedInfos);
		elements.add(this.fieldCount);
		elements.add(this.interfaces);
		elements.add(this.interfaceCount);
		elements.add(this.superClass);
		elements.add(this.thisClass);
		elements.add(this.accessFlags);
		elements.add(this.constantPool);
		elements.add(this.constantPoolCount);
		elements.add(this.majorVersion);
		elements.add(this.minVersion);
		elements.add(this.magic);
	}

	private Magic magic;
	
	private MinorVersion minVersion;
	
	private MajorVersion majorVersion;
	
	private ConstantPoolCount constantPoolCount;
	
	private ConstantPool constantPool;
	
	private AccessFlags accessFlags;
	
	private ThisClass thisClass;
	
	private SuperClass superClass;
	
	private InterfaceCount interfaceCount;
	
	private Interfaces interfaces;
	
	private FieldCount fieldCount;
	
	private FiledInfos filedInfos;
	
	private MethodCount methodCount;
	
	private MethodInfos methodInfos;
	
	private AttributeCount attributeCount;
	
	private AttributeInfos attributeInfos;

	public Magic getMagic() {
		return magic;
	}

	public MinorVersion getMinVersion() {
		return minVersion;
	}

	public MajorVersion getMajorVersion() {
		return majorVersion;
	}

	public ConstantPoolCount getConstantPoolCount() {
		return constantPoolCount;
	}

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public AccessFlags getAccessFlags() {
		return accessFlags;
	}

	public ThisClass getThisClass() {
		return thisClass;
	}

	public SuperClass getSuperClass() {
		return superClass;
	}

	public InterfaceCount getInterfaceCount() {
		return interfaceCount;
	}

	public Interfaces getInterfaces() {
		return interfaces;
	}

	public FieldCount getFieldCount() {
		return fieldCount;
	}

	public FiledInfos getFiledInfos() {
		return filedInfos;
	}

	public MethodCount getMethodCount() {
		return methodCount;
	}

	public MethodInfos getMethodInfos() {
		return methodInfos;
	}

	public AttributeCount getAttributeCount() {
		return attributeCount;
	}

	public AttributeInfos getAttributeInfos() {
		return attributeInfos;
	}
	
	@Override
	public String toString(){
		
		return null;
	}
	
}
