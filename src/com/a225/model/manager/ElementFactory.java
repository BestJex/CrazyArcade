package com.a225.model.manager;

import com.a225.model.vo.SuperElement;

/**
 * Ԫ�ع�����
 * @ClassName: ElementFactory  
 * @Description: ���ڹ����������Ԫ�ع�����   
 * @author: WeiXiao
 * @CreateDate: 2019��4��11�� ����5:07:57
 */
public class ElementFactory {
	private static ElementFactory elementFactory;
	
	//���캯��
	private ElementFactory() {}
	
	public static ElementFactory getElementFactory() {
		if(elementFactory == null) {
			elementFactory = new ElementFactory();
		}
		return elementFactory;
	}
	
	public SuperElement produceElement() {
		//TODO:д����
		return null;
	}
}
