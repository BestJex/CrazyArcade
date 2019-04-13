package com.a225.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import com.a225.model.loader.ElementLoader;
import com.a225.model.manager.ElementManager;
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
	private boolean attack;//��¼����״̬��Ĭ��Ϊfalse
	private boolean keepAttack;//��¼�Ƿ�Ϊһֱ���Ź�������ʵ��һ�ΰ���ֻ��һ��ˮ��
	
	private static final int SPEED = 5;
	
	//���캯��
	public Player(int x, int y, int w, int h, ImageIcon img) {
		super(x, y, w, h);
		this.img = img;
		moveType = MoveTypeEnum.STOP;
		moveX = 0;
		moveY = 0;
		attack = false;
		keepAttack = false;
	}
	
	public static Player createPlayer(List<String> list) {
		//list = [PlayerA,x,y,w,h]
		int x = Integer.parseInt(list.get(1));
		int y = Integer.parseInt(list.get(2));
		int w = MapSquare.PIXEL_X;
		int h = MapSquare.PIXEL_Y;
		Map<String, ImageIcon> imageMap = 
				ElementLoader.getElementLoader().getImageMap();//��ȡ��Դ��������ͼƬ�ֵ�
		return new Player(x, y, w, h, imageMap.get(list.get(0)));
	}

	//չʾ����ͼƬ
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(), 	//��Ļ���Ͻ�����
				getX()+getW(), getY()+getH(), 	//��Ļ��������
				moveX*100+25, moveY*100+40, 				//ͼƬ��������
				moveX*100+75, moveY*100+100, 			//ͼƬ��������
				null);
	}

	//�ƶ�
	@Override
	public void move() {
		switch(moveType) {
		case TOP: setY(getY()-SPEED);break;
		case LEFT: setX(getX()-SPEED);break;
		case RIGHT: setX(getX()+SPEED);break;
		case DOWN: setY(getY()+SPEED);break;
		case STOP:
		default:
			break;
		}

	}
	
	//��д����ģ��
	@Override
	public void update() {
		super.update();
		addBubble();
		updateImage();
	}
	
	//����ͼƬ
	public void updateImage() {
		if(moveType==MoveTypeEnum.STOP){
			return;
		}
		
		moveX = ++moveX%4;
		
		switch (moveType) {
		case TOP:moveY = 3;break;
		case LEFT:moveY = 1;break;
		case RIGHT:moveY = 2;break;
		case DOWN:moveY = 0;break;
		default:break;
		}
	}
	
	//�������
	public void addBubble() {
		if(attack) {
			List<SuperElement> list = 
					ElementManager.getManager().getElementList("bubble");
			list.add(Bubble.createBubble(getX(), getY(), ElementLoader.getElementLoader().getGameInfoMap().get("bubble")));
			attack = false;
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

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public boolean isKeepAttack() {
		return keepAttack;
	}

	public void setKeepAttack(boolean keepAttack) {
		this.keepAttack = keepAttack;
	}
	
	

}
