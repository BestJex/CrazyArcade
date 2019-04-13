package com.a225.model.vo;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import com.a225.model.manager.ElementManager;

/**
 * ��ͼ������
 * @ClassName: MapSquare  
 * @Description: ��ͼ����VO��   
 * @author: DaXiao
 * @CreateDate: 2019��4��11�� ����23��11
 */
public class MapSquare extends SuperElement{
	public static int pixelx = 64;//��λ����x
	public static int pixely = 64;//��λ����y
	private ImageIcon img;
	private int sx,sy,dx,dy;
	
	public MapSquare(int i, int j ,ImageIcon img, int sx, int sy, int dx, int dy, int scaleX,int scaleY) {
		super((j-scaleX+1)*pixelx+ElementManager.getManager().getGameMap().getBiasX(), 
				(i-scaleY+1)*pixely+ElementManager.getManager().getGameMap().getBiasY(), 
				pixelx*scaleX, pixely*scaleY);
		this.img = img;
		this.setPictureLoc(sx, sy, dx, dy);
	}
	
	@Override
	public final void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //��Ļ���Ͻ�����
				getX()+getW(), getY()+getH(),    //��Ļ���½�����
				sx, sy,    //ͼƬ���Ͻ�����
				dx, dy,    //ͼƬ���½�����
				null);
	}
	
	public void setPictureLoc(int sx,int sy,int dx,int dy){
		this.sx = sx;
		this.sy = sy;
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void move() {}

	@Override
	public void destroy() {}
	
}
