package com.a225.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import com.a225.model.loader.ElementLoader;
import com.a225.model.manager.ElementManager;
import com.a225.model.manager.MoveTypeEnum;
import com.a225.model.vo.GameMap.SquareType;

public class Npc extends SuperElement{
	
	private List<ImageIcon> img;
	private MoveTypeEnum moveType;//��ʼΪstop
	private int moveX;//��¼ͼƬ����
	private int imgW;//ͼƬ��
	private int imgH;//ͼƬ��
	private int npcNum;//��¼�ڼ���npc��0ΪnpcA��1ΪnpcB,2ΪnpcC
	private int bubbleNum;//��¼�Ѿ����˶��ٸ�ը��
	private int bubbleLargest;//��������ԷŶ��ٸ�ը������ʼֵΪ3
	
	private int speed = 8;
	private int step = 0; //����npc��������

	public Npc(int x, int y, int w, int h, int imgW, int imgH,List<ImageIcon> img, int npcNum) {
		super(x, y, w, h);
		this.imgW = imgW;
		this.imgH = imgH;
		this.img = new ArrayList<>(img);
		moveType = MoveTypeEnum.DOWN;
		moveX = 0;
		this.npcNum = npcNum;
		bubbleNum = 0;
		bubbleLargest = 1;
	}
	
	public static Npc createNpc(List<String> data,int i,int j,int npcNum) {
		//data=[���ͼƬ��x,y,w,h]
		List<ImageIcon> imageList = 
				new ArrayList<>(ElementLoader.getElementLoader().getNpcImageList(data.get(0)));
		int x = j*MapSquare.PIXEL_X+GameMap.getBiasX();
		int y = i*MapSquare.PIXEL_Y+GameMap.getBiasY();
		int w = MapSquare.PIXEL_X;
		int h = MapSquare.PIXEL_Y;
		int imgW = Integer.parseInt(data.get(3));
		int imgH = Integer.parseInt(data.get(4));
		return new Npc(x, y, w, h, imgW, imgH, imageList, npcNum);
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(getImg().get(moveX).getImage(),
				getX(), getY(), 
				getX()+getW(), getY()+getH(), 
				0, 0, 
				getImgW(), getImgH(), 
				null);
	}
	
	@Override
	public void update() {
		super.update();
		updateImage();
	}
	
	public void updateImage() {
		switch(moveType) {
		case STOP: moveX = 0;break;
		case LEFT: moveX = 1;break;
		case RIGHT: moveX = 2;break;
		case TOP: moveX = 3;break;
		case DOWN: moveX = 0;break;
		}
	}

	//�ƶ�
	@Override
	public void move() {
		if(step==8) {
			step=0;
			if(!letsGo(moveType)||Math.random()<0.2) {//���ǰ�����ϰ��ת��
				turn();
			}
		}
		switch(moveType) {
		case LEFT:
				setX(getX()-speed);
				break;
		case RIGHT:
				setX(getX()+speed);
				break;
		case TOP:
				setY(getY()-speed);
				break;
		case DOWN:
				setY(getY()+speed);
				break;
		}
		step++;
	}

	//ת��
	public void turn() {
		MoveTypeEnum m = randomOrient();
		boolean go = letsGo(m);
		if(go) {
			setMoveType(m);
			addBubble();
		}
		else {
			turn();
		}
	}
	//������һ������
	public MoveTypeEnum randomOrient() {
		MoveTypeEnum moveTypeEnum[] = {MoveTypeEnum.LEFT,MoveTypeEnum.RIGHT,MoveTypeEnum.TOP,MoveTypeEnum.DOWN};
		Random random = new Random();
		return moveTypeEnum[random.nextInt(moveTypeEnum.length)];
	}
	//�ж�ǰ���Ƿ����߹�ȥ
	public boolean letsGo(MoveTypeEnum m) {
		GameMap gameMap = ElementManager.getManager().getGameMap();
		boolean go = false;
		List<Integer> ijList = GameMap.getIJ(getX(), getY());
		//��ʱ�ж�ǰ���ǵذ弴��ǰ����TODO�ж�ǰ����Bubble
		switch(m) {
		case LEFT: if(gameMap.getBlockSquareType(ijList.get(0), ijList.get(1)-1)==SquareType.FLOOR) go = true;
			break;  
		case RIGHT: if(gameMap.getBlockSquareType(ijList.get(0), ijList.get(1)+1)==SquareType.FLOOR) go = true;
			break;
		case TOP: if(gameMap.getBlockSquareType(ijList.get(0)-1, ijList.get(1))==SquareType.FLOOR) go = true;
			break;  
		case DOWN: if(gameMap.getBlockSquareType(ijList.get(0)+1, ijList.get(1))==SquareType.FLOOR) go = true;
			break; 
		}
		return go;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//�������
	public void addBubble() {
		List<Integer> loc = GameMap.getXY(GameMap.getIJ(getX()+getW()/2, getY()+getH()/2));
		GameMap gameMap = ElementManager.getManager().getGameMap();
		List<Integer> maplist = GameMap.getIJ(loc.get(0), loc.get(1));
		if( bubbleNum<bubbleLargest &&  //��ǰ��ը����С������ֵ����ǰλ��û��ը��
				gameMap.getBlockSquareType(maplist.get(0), maplist.get(1))!=GameMap.SquareType.BUBBLE) {

			List<SuperElement> list = 
					ElementManager.getManager().getElementList("bubble");
			list.add(Bubble.createBubble(loc.get(0), loc.get(1), ElementLoader.getElementLoader().getGameInfoMap().get("bubble"),npcNum+2));
			bubbleNum++;
		}
	}

	public List<ImageIcon> getImg() {
		return img;
	}

	public void setImg(List<ImageIcon> img) {
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

	public int getNpcNum() {
		return npcNum;
	}

	public void setNpcNum(int npcNum) {
		this.npcNum = npcNum;
	}

	public int getBubbleNum() {
		return bubbleNum;
	}

	public void setBubbleNum(int bubbleNum) {
		this.bubbleNum = bubbleNum;
	}

	public int getBubbleLargest() {
		return bubbleLargest;
	}

	public void setBubbleLargest(int bubbleLargest) {
		this.bubbleLargest = bubbleLargest;
	}

	public int getImgW() {
		return imgW;
	}

	public void setImgW(int imgW) {
		this.imgW = imgW;
	}

	public int getImgH() {
		return imgH;
	}

	public void setImgH(int imgH) {
		this.imgH = imgH;
	}
	
	

}
