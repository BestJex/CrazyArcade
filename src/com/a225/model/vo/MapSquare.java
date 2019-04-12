package com.a225.model.vo;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import com.a225.model.loader.ElementLoader;
import com.a225.model.manager.ElementManager;


public class MapSquare extends SuperElement{
	final static int pixelx = 32;//����x
	final static int pixely = 32;//����y
	private ImageIcon img;
	private boolean beDestoried;
	private boolean beWalked;
	private int sx,sy,dx,dy;
	
public MapSquare(int x, int y, int w, int h,boolean beDestoried,boolean beWalked){
		super(x, y, w, h);
		this.img=img;
		beDestoried=false;
		beWalked=false;
		
	}

/**
 * ��ͼ������
 * @ClassName: MapSquare  
 * @Description: ��ͼ����VO��   
 * @author: DaXiao
 * @CreateDate: 2019��4��11�� ����23��11
 */

//0���ϰ��﷽�飬1����·���飬2���ɴݻٷ���
//#01������02��С���ӣ�11����ɫ���飬21����ɫ����
	public static MapSquare createMapSquare(List<String> data,int x, int y){
		ImageIcon img = ElementLoader.getElementLoader().getImageMap().get(data.get(0));
		int sx = Integer.parseInt(data.get(1));
		int sy = Integer.parseInt(data.get(2));
		int dx = Integer.parseInt(data.get(3));
		int dy = Integer.parseInt(data.get(4));
		int xLoc  = pixelx*x;
		int yLoc  = pixely*y;
		boolean beDestoied = data.get(5).equals("0")?false:true;
		boolean beWalked = data.get(5).equals("0")?false:true;
//		��������λ��
		if(dy-sy == pixely) yLoc = yLoc - pixely;
//		switch (dy-sy) {
//		case pixely:
//			yLoc =yLoc - pixely;
//			break;
//		case pixely/2:
//			yLoc = yLoc - pixely/2;
//			break;
//		default:
//			break;
//		}
		MapSquare mapSquare = new MapSquare(xLoc, yLoc, dy-sy, dx-sx, beDestoied, beWalked);
		mapSquare.setPictureLoc(sx, sy, dx, dy);
		return mapSquare;
	}
	@Override
	public void showElement(Graphics g) {
		// TODO �Զ����ɵķ������
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //��Ļ���Ͻ�����
				getX()+pixelx, getY()+pixely,    //��Ļ���½�����
					sx, sy,    //ͼƬ���Ͻ�����        60 ,0
					dx, dy,    //ͼƬ���½�����  120,60
					null);
	}
	
	public void setPictureLoc(int sx,int sy,int dx,int dy){
		this.sx = sx;
		this.sy = sy;
		this.dx = dx;
		this.dy = dy;
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
