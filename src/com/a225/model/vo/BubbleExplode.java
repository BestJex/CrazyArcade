package com.a225.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;


import com.a225.model.loader.ElementLoader;
import com.a225.model.manager.ElementManager;

public class BubbleExplode extends SuperElement{
	
	private List<ImageIcon> img; //���汬ըͼƬ
	private int moveX;
	
	//ը���ڵ�ͼ�����ĸ���������Ĳ���
	private int up;
	private int down;
	private int left;
	private int right;


	public BubbleExplode(int x,int y, int w, int h, List<ImageIcon> imageList) {
		//����������x������y����w����h����ըͼƬ�б�
		super(x, y, w, h);
		img = new ArrayList<>(imageList);
		moveX = 0;
		up = 0;
		down = 0;
		left = 0;
		right = 0;
		setMoveStep();
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
		int perW = getW()/5;
		int perH = getH()/5;
		g.drawImage(img.get(moveX).getImage(), 
				getX()-left*MapSquare.PIXEL_X, getY()-up*MapSquare.PIXEL_Y, 	//��Ļ���Ͻ�����
				getX()+(right+1)*MapSquare.PIXEL_X, getY()+(down+1)*MapSquare.PIXEL_Y, 	//��Ļ��������
				(2-left)*perW, (2-up)*perH, 				//ͼƬ��������
				(3+right)*perW, (3+down)*perH, 			//ͼƬ��������\
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
	
	//�жϱ�ը�������Ե��ͻ
	@Override
	public boolean crash(SuperElement se) {
		int bias = 0;
		Rectangle explodeColumn = 
				new Rectangle(getX()+bias, getY()-getUp()*MapSquare.PIXEL_Y+bias, MapSquare.PIXEL_X-bias, (getUp()+getDown()+1)*MapSquare.PIXEL_Y-bias);//ˮ�ݱ�ըʮ������
		Rectangle explodeRow =  
				new Rectangle(getX()-getLeft()*MapSquare.PIXEL_X+bias, getY()+bias, (getLeft()+getRight()+1)*MapSquare.PIXEL_X-bias, MapSquare.PIXEL_Y-bias);//ˮ�ݱ�ըʮ�ֺ���
		Rectangle rectangle = new Rectangle(se.getX()+bias, se.getY()+bias, se.getW()-bias, se.getH()-bias);
		boolean column = explodeColumn.intersects(rectangle);
		boolean row = explodeRow.intersects(rectangle);
		return (column||row);
	}
	
	private int getMoveStep(int i, int j, String direction) {
		//���㷽��ı���
		int bi = 0;
		int bj = 0;
		switch (direction) {
		case "up": bi=-1;break;
		case "down": bi=1;break;
		case "left": bj=-1;break;
		case "right": bj=1;break;
		default: break;
		}
		//��ȡ��ͼ
		GameMap gameMap = ElementManager.getManager().getGameMap();
		//����step
		int step = 0;
		int tpower = 2;
		for(int k=0;k<tpower;k++) {
			i += bi;
			j += bj;
			if(gameMap.outOfBoundary(i,j)||gameMap.blockIsObstacle(i, j)) {
				break;
			} else {
				step++;
				if(gameMap.getBlockSquareType(i, j)==GameMap.SquareType.FRAGILITY) {
					break;
				}
			}
		}
		return step;
	}
	
	
	//��ȡ��ը��Χup down left right
	public void setMoveStep() {
		int mapI = GameMap.getIJ(getX(), getY()).get(0);
		int mapJ = GameMap.getIJ(getX(), getY()).get(1);
		
		up = getMoveStep(mapI, mapJ, "up");
		down = getMoveStep(mapI, mapJ, "down");
		left = getMoveStep(mapI, mapJ, "left");
		right = getMoveStep(mapI, mapJ, "right");
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

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	

}
