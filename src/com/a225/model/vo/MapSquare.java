package com.a225.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * ��ͼ������
 * @ClassName: Map  
 * @Description: ��ͼ����VO��   
 * @author: DaXiao
 * @CreateDate: 2019��4��11�� ����23��11
 */
public class MapSquare extends SuperElement{
	private ImageIcon img;
	private boolean beDestoried;
	private boolean beWalked;
	private int moveX;
	private int moveY;
	
public MapSquare(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.img=img;
		beDestoried=false;
		beWalked=false;
	}

//
//
//	public static MapSquare createMapSquare(String s){
//		switch (s) {
//		case "01":
//			moveX=2;
//			moveY=1;
//			break;
//		case "02":
//			
//			break;
//		case "11":
//			
//			break;
//		case "21":
//	
//			break;
//		default:
//			break;
//		}
//		ImageIcon img=ElementLoad.getElementLoad().getMap().get(arrStrings[0]);
//		return new Player(x,y,w,h,img);
//		return new PlayerFire(x,y,30,30,img);
//	}
	@Override
	public void showElement(Graphics g) {
		// TODO �Զ����ɵķ������
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //��Ļ���Ͻ�����
				getX()+getW(), getY()+getH(),    //��Ļ���½�����
					60*moveX, 0,    //ͼƬ���Ͻ�����        60 ,0
					60*(moveX+1), 60,    //ͼƬ���½�����  120,60
//					moveX, 0,    //ͼƬ���Ͻ�����        60 ,0
//					moveX+60, 60,	
					null);
	}

	@Override
	public void move() {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void destroy() {
		// TODO �Զ����ɵķ������
		
	}

}
