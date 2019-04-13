package com.a225.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.text.html.parser.TagElement;

import com.a225.model.loader.ElementLoader;

public class BubbleExplode extends SuperElement{
	
	private List<ImageIcon> img; //���汬ըͼƬ
	private int moveX;


	public BubbleExplode(int x,int y, int w, int h, List<ImageIcon> imageList) {
		//����������x������y����w����h����ըͼƬ�б�
		super(x, y, w, h);
		img = new ArrayList<>(imageList);
		moveX = 0;
	}
	
	//����ʵ��
	public static BubbleExplode createExplode(int x, int y,List<String> list) {
		//list=[ͼƬ0��ͼƬ1��ͼƬ2��ͼƬ��w��ͼƬ��h]
		int w = Integer.parseInt(list.get(3));
		int h = Integer.parseInt(list.get(4));
		List<ImageIcon> imageList = new ArrayList<>();
		Map<String, ImageIcon> imageMap = ElementLoader.getElementLoader().getImageMap();
		for(int i=0; i<3; i++) { //��ըЧ��ͼ��3��
			imageList.add(imageMap.get(list.get(i)));
		}
		return new BubbleExplode(x, y, w, h, imageList);
	}

	
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
//		g.drawImage(img.get(moveX).getImage(), getX(), getY(), getW(), getH(), null);
//		g.drawImage(img.get(moveX).getImage(), getX(), getY(),null);
//		g.drawImage(img.get(moveX).getImage(), getX(), getY(), 32, 32, null);
		g.drawImage(img.get(moveX).getImage(), 
				getX()-64, getY()-64, 	//��Ļ���Ͻ�����
				getX()+getW()-64, getY()+getH()-64, 	//��Ļ��������
				0, 0, 				//ͼƬ��������
				getW(), getH(), 			//ͼƬ��������
				null);
	}

	//����ͼƬ��ͼƬ������������״̬
	@Override
	public void move() {
		if(moveX<2) {
			moveX++;
		}
	}

	//��ըЧ������0.8��
	@Override
	public void destroy() {
		Timer timer = new Timer(true);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				setAlive(false);
			}
		};
		timer.schedule(task, 800);
	}

	//getters and setters
	

	public int getMoveX() {
		return moveX;
	}

	public List<ImageIcon> getImg() {
		return img;
	}

	public void setImg(List<ImageIcon> img) {
		this.img = img;
	}

	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}


}
