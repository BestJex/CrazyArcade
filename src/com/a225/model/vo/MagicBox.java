package com.a225.model.vo;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import com.a225.model.loader.ElementLoader;
import com.a225.model.manager.ElementManager;
import com.a225.model.manager.GameMap;

public class MagicBox extends MapSquare{
	private static Random rd = new Random();
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
	public void update() {
		// TODO �Զ����ɵķ������
		super.update();
		updateImage();
	}
	
//	�л�ͼƬ
	public void updateImage() {
		if(eaten) return;
		if(++moveX>=40)
			moveX = 0;
		int sx = (moveX/10)*32;
		int sy = Integer.parseInt(typeMap.get(type).get(2));
		int dx = (moveX/10+1)*32;
		int dy = Integer.parseInt(typeMap.get(type).get(4));
		setPictureLoc(sx, sy, dx, dy);
	}

	@Override
	public void destroy() {
		if(eaten){	
//			�����ݻٷ�������Ϊ�ذ�
			GameMap gameMap = ElementManager.getManager().getGameMap();
			List<Integer> list = GameMap.getIJ(getX(), getY());
			gameMap.setBlockSquareType(list.get(0), list.get(1), GameMap.SquareType.FLOOR);
//			�õ�buff
			List<SuperElement> playerList = ElementManager.getManager().getElementList("player");
			Player player = (Player) playerList.get(this.getPlayer());
			switch (type) {
			case "34": //�����ƶ��ٶ�
				player.changeSpeed(2,5);//�����������ӱ����ͳ���ʱ�䣨�룩
				System.out.println(player.getSpeed());
				break;
			case "35": //���ݸ�������
				player.setBubbleLargest(player.getBubbleLargest()+1);
				System.out.println(player.getBubbleLargest());	
				break;
			default:
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
