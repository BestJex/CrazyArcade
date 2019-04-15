package com.a225.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import com.a225.model.loader.ElementLoader;
import com.a225.model.manager.ElementManager;

/**
 * ˮ��ը����
 * @author ���
 *
 */
public class Bubble extends SuperElement{
	
	private ImageIcon img;
	private int moveX;

	//���캯��
	public Bubble(int x, int y, int w, int h, ImageIcon img) {
		super(x, y, w, h);
		this.img = img;
		moveX = 0;
	}
	
	//��������1
	public static Bubble createBubble(int x, int y,List<String> list) {
		//list=[Bubble,w,h]
		int w = Integer.parseInt(list.get(1));
		int h = Integer.parseInt(list.get(2));
		Map<String, ImageIcon> imageMap = 
				ElementLoader.getElementLoader().getImageMap();//��ȡ��Դ��������ͼƬ�ֵ�
		return new Bubble(x, y, w, h, imageMap.get(list.get(0)));
	}
	//��������2
	public static Bubble createBubble(List<String> list){
		//list=[ˮ��ͼƬ��ͼƬ��w��ͼƬ��h]
		int w = Integer.parseInt(list.get(1));
		int h = Integer.parseInt(list.get(2));
		Map<String, ImageIcon> imageMap = 
				ElementLoader.getElementLoader().getImageMap();//��ȡ��Դ��������ͼƬ�ֵ�
		return new Bubble(0, 0, w, h, imageMap.get(list.get(0)));
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(), 	//��Ļ���Ͻ�����
				getX()+MapSquare.PIXEL_X, getY()+MapSquare.PIXEL_Y, 	//��Ļ��������
				moveX*getW(), 0, 				//ͼƬ��������
				(moveX+1)*getW(), 46, 			//ͼƬ��������
				null);
	}
	
	//��д����ģ��
	@Override
	public void update() {
		super.update();
		updateImage();
	}

	//����ͼƬ
	public void updateImage() {
		moveX++;
		moveX = moveX % 4;
	}
	
	//ʹ�ü�ʱ����2.5��ı�Alive״̬
	@Override
	public void move() {
		Timer timer = new Timer(true);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				setAlive(false);
			}
		};
		timer.schedule(task, 2500);
	}
	

	@Override
	public void destroy() {
		if(!isAlive()) {	//��ʾ��ըЧ��������ExplodeBubble
			List<SuperElement> list = 
					ElementManager.getManager().getElementList("explode");
			list.add(BubbleExplode.createExplode(getX(), getY(), ElementLoader.getElementLoader().getGameInfoMap().get("explode")));
		}
	}

	//getters and setters
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public int getMoveX() {
		return moveX;
	}

	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}
	
}
