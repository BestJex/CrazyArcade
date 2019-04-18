package com.a225.model.vo;

import java.awt.Rectangle;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import com.a225.model.loader.ElementLoader;
import com.a225.model.manager.ElementManager;

public class MagicBox extends MapSquare{

	private boolean eaten;//���Ե���ʧ��
	private int moveX;//ͼƬ�任
	private String type;//��������
	private int player;//�ĸ�player��õ���
	
	static Map<String, List<String>> typeMap = ElementLoader.getElementLoader().getSquareTypeMap();
	
	public MagicBox(int i, int j, ImageIcon img, 
			int sx, int sy, int dx,int dy, int scaleX, int scaleY, String type) {
		super(i, j, img, sx, sy, dx, dy, scaleX, scaleY);
		moveX = 0;
		eaten = false;
		this.type = type;
	}
	
	public static MagicBox createMagicBox(int i,int j) {
//		�����������
		Random rd = new Random();
		int letter = rd.nextInt(8)+1;
		String boxtype = "3" + letter;
		List<String> data = typeMap.get(boxtype);
		int sx = Integer.parseInt(data.get(1));
		int sy = Integer.parseInt(data.get(2));
		int dx = Integer.parseInt(data.get(3));
		int dy = Integer.parseInt(data.get(4));
		int scaleX = Integer.parseInt(data.get(6));
		int scaleY = Integer.parseInt(data.get(7));
		ImageIcon img = ElementLoader.getElementLoader().getImageMap().get(data.get(0));
		MagicBox magicBox = new MagicBox(i, j, img, sx, sy, dx, dy, scaleX, scaleY, boxtype);
		return magicBox;
	}
	
//	��дcrash��������С��ײ���
	@Override
	public boolean crash(SuperElement se) {
		Rectangle r1 = new Rectangle(getX()+getW()/4, getY()+getH()/4, getW()/2, getH()/2);
		Rectangle r2 = new Rectangle(se.getX(), se.getY(), se.getW(), se.getH());
		return r1.intersects(r2);//�н�����Χtrue
	}
	
	@Override
	public void update() {
		// TODO �Զ����ɵķ������
		super.update();
		updateImage();
	}
	
//	�л�ͼƬ
	public void updateImage() {
		if(eaten) return;
		moveX = ++moveX%3;
		int sx = moveX*32;
		int sy = Integer.parseInt(typeMap.get(type).get(2));
		int dx = (moveX+1)*32;
		int dy = Integer.parseInt(typeMap.get(type).get(4));
		setPictureLoc(sx, sy, dx, dy);
	}

	@Override
	public void destroy() {
		// TODO �Զ����ɵķ������
		if(eaten){	
//			�����ݻٷ�������Ϊ�ذ�
			GameMap gameMap = ElementManager.getManager().getGameMap();
			List<Integer> list = gameMap.getIJ(getX(), getY());
			gameMap.setBlockSquareType(list.get(0), list.get(1), GameMap.SquareType.FLOOR);
//			�õ�buff
			List<SuperElement> playerList = ElementManager.getManager().getElementList("player");
			Player player = (Player) playerList.get(this.getPlayer());
			switch (type) {
			case "31": //ʹ�ƶ�����
				player.changeDirection(2);//���뷽��ı�ĳ���ʱ�䣨�룩
				System.out.println(player.getSPEED());
				break;
			
			case "34": //�����ƶ��ٶ�
				player.changeSpeed(2,10);//�����������ӱ����ͳ���ʱ�䣨�룩
				System.out.println(player.getSPEED());
				break;
			
			case "35": //���ݸ�������
				player.setBubbleLargest(player.getBubbleLargest()+1);
				System.out.println(player.getBubbleLargest());	
				break;
				

			default:
				System.out.println("1");	

				break;
			}
			
			eaten = false;
			setAlive(false);
		}
		
	}
//	����Ӵ�����
	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
	
	
}
