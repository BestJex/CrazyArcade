package com.a225.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.a225.model.loader.ElementLoader;
import com.a225.model.manager.MoveTypeEnum;

/**
 * �����
 * @ClassName: Player  
 * @Description: ���VO��   
 * @author: WeiXiao
 * @CreateDate: 2019��4��11�� ����5:10:20
 */
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
	
	public static Player createPlayer(List<String> list) {
		//list = [PlayerA,x,y,w,h]
		int x = Integer.parseInt(list.get(1));
		int y = Integer.parseInt(list.get(2));
		int w = Integer.parseInt(list.get(3));
		int h = Integer.parseInt(list.get(4));
		Map<String, ImageIcon> imageMap = 
				ElementLoader.getElementLoader().getImageMap();//��ȡ��Դ��������ͼƬ�ֵ�
		return new Player(x, y, w, h, imageMap.get(list.get(0)));
	}

	//չʾ����ͼƬ
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img.getImage(), 
				getX(), getY(), 	//��Ļ���Ͻ�����
				getX()+getW(), getY()+getH(), 	//��Ļ��������
				moveX*100+25, moveY*100+40, 				//ͼƬ��������
				moveX*100+75, moveY*100+100, 			//ͼƬ��������
				null);
//		System.out.println(getX() +" " +getY() +" "+ getW() +" "+ getH());
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
		if(moveType==MoveTypeEnum.STOP){
			return ;
		}
		
		moveX++;
		if(moveX>3)
			moveX = 0;
		switch (moveType) {
		case TOP:moveY = 3;break;
		case LEFT:moveY = 1;break;
		case RIGHT:moveY = 2;break;
		case DOWN:moveY = 0;break;
		default:break;
		}
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
