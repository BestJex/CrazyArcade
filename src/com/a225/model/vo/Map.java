package com.a225.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

public class Map extends SuperElement{


/**
 * ��ͼ��
 * @ClassName: Map  
 * @Description: ��ͼVO��   
 * @author: DaXiao
 * @CreateDate: 2019��4��11�� ����21��11
 */
	private int mapSizeW;
	private int mapSizeH;
	List<List<String>> list;
	
	public Map(int x, int y, int w, int h) {
		super(x, y, w, h);
		mapSizeW = 0;
		mapSizeH = 0;
	}
	
	@Override
	public void showElement(Graphics g) {
		
		
	}

	@Override
	public void move() {
		
		
	}

	@Override
	public void destroy() {
		
		
	}

	
}
