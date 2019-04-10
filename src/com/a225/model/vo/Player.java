package com.a225.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.a225.model.manager.MoveTypeEnum;

public class Player extends SuperElement{
	
	private ImageIcon img;
	private MoveTypeEnum moveType;
	private int moveX;
	private int moveY;
	
	//���캯��
	public Player(int x, int y, int w, int h, ImageIcon img) {
		super(x, y, w, h);
		this.img = img;
		moveType = MoveTypeEnum.STOP;
		moveX = 0;
		moveY = 0;
	}
	
	public static Player createPlayer(String str) {
		String url = "img/Characters/Done_body16001_walk.png";
		return new Player(0, 0, 50, 60, new ImageIcon(url));
	}

	//չʾ����ͼƬ
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img.getImage(), 
				getX(), getY(), //��Ļ���Ͻ�����
				getX()+getW(), getY()+getH(), //��Ļ���½�����
				60*moveX, 0, //ͼƬ���Ͻ�����60,0
				60*(moveX+1), 60, //ͼƬ���½�����120,60
				null);
	}

	//�ƶ�
	@Override
	public void move() {
		// TODO Auto-generated method stub
		switch(moveType) {
		case TOP: setY(getY()-5);break;
		case LEFT: setX(getX()-5);break;
		case RIGHT: setX(getX()+5);break;
		case DOWN: setY(getY()+5);break;
		case STOP:
		}

	}
	
	//��д����ģ��
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		updateImage();
	}
	
	//����ͼƬ
	public void updateImage() {
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	//gettes and setters
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public MoveTypeEnum getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveTypeEnum moveType) {
		this.moveType = moveType;
	}

	public int getMoveX() {
		return moveX;
	}

	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}

	public int getMoveY() {
		return moveY;
	}

	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}
	
	

}
