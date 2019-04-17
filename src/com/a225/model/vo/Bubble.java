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
	private int playerNum;//��ʾ��Ӧ��ҵ�ը����0Ϊ���һ��1Ϊ��Ҷ���2ΪnpcA��3ΪnpcB��4ΪnpcC

	//���캯��
	public Bubble(int x, int y, int w, int h, ImageIcon img, int playerNum) {
		super(x, y, w, h);
		this.img = img;
		this.playerNum = playerNum;
		moveX = 0;
		//��ͼ��Ӧλ������Ϊ�ϰ������ͨ��
		GameMap gameMap = ElementManager.getManager().getGameMap();
		List<Integer> maplist = GameMap.getIJ(x, y);
		gameMap.setBlockSquareType(maplist.get(0), maplist.get(1), GameMap.SquareType.BUBBLE);
	}
	
	//��������1
	public static Bubble createBubble(int x, int y,List<String> list,int playerNum) {
		//list=[Bubble,w,h]
		int w = Integer.parseInt(list.get(1));
		int h = Integer.parseInt(list.get(2));
		Map<String, ImageIcon> imageMap = 
				ElementLoader.getElementLoader().getImageMap();//��ȡ��Դ��������ͼƬ�ֵ�
		return new Bubble(x, y, w, h, imageMap.get(list.get(0)),playerNum);
	}
	//��������2
	public static Bubble createBubble(List<String> list,int playerNum){
		//list=[ˮ��ͼƬ��ͼƬ��w��ͼƬ��h]
		int w = Integer.parseInt(list.get(1));
		int h = Integer.parseInt(list.get(2));
		Map<String, ImageIcon> imageMap = 
				ElementLoader.getElementLoader().getImageMap();//��ȡ��Դ��������ͼƬ�ֵ�
		return new Bubble(0, 0, w, h, imageMap.get(list.get(0)),playerNum);
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
			
			//����ͼλ����Ϊfloor
			GameMap gameMap = ElementManager.getManager().getGameMap();
			List<Integer> maplist = GameMap.getIJ(getX(), getY());
			gameMap.setBlockSquareType(maplist.get(0), maplist.get(1), GameMap.SquareType.FLOOR);
			
			//�ı�ը������Ѿ�����ը����bubbleNum
			if(playerNum<2) {
				List<SuperElement> list2 = ElementManager.getManager().getElementList("player");
				Player player = (Player) list2.get(playerNum);
				player.setBubbleNum(player.getBubbleNum()-1);
			}
			else {
				List<SuperElement> list2 = ElementManager.getManager().getElementList("npc");
				Npc npc = (Npc) list2.get(playerNum-2);
				npc.setBubbleNum(npc.getBubbleNum()-1);
			}
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
	
	public int getPlayerNum() {
		return this.playerNum;
	}
}
