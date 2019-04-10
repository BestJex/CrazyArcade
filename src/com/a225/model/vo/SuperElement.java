package com.a225.model.vo;

import java.awt.Graphics;

/**
 * 
 * @author Jenson
 * ������ϷԪ�صĸ���
 */
public abstract class SuperElement {
	
	//Ԫ������
	private int x;
	private int y;
	private int w;
	private int h;
	//��¼�Ƿ���
	private boolean alive; 
	
	//���캯��
	private SuperElement() {}
	public SuperElement(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		alive = true;
	}
	
	//ģ����
	public void update() {
		move();
		destroy();
	}
	
	//Ԫ��չʾ
	public abstract void showElement(Graphics g);
	
	//Ԫ���ƶ�
	public abstract void move();
	
	//Ԫ������
	public abstract void destroy();
	
	//getters and setters
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
}
