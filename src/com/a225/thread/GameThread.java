package com.a225.thread;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.a225.main.GameStart;
import com.a225.model.manager.ElementManager;
import com.a225.model.vo.MagicBox;
import com.a225.model.vo.MapFragility;
import com.a225.model.vo.Npc;
import com.a225.model.vo.Player;
import com.a225.model.vo.SuperElement;

/**
 * ��Ϸ�߳̿���
 * @author ���
 *
 */
public class GameThread extends Thread{
	private boolean running;//��ʾ��ǰ�ؿ��Ƿ��ڽ���
	private boolean over = false;//��ʾ��Ϸ�Ƿ�������������ؿ�ʼ�˵�
	
	@Override
	public void run() {
		while(!over) {
			running = true;//��ǰ�ؿ����ڽ���
			//����Ԫ��
			loadElement();
			//��ʾ������̣��Զ���
			runGame();
			//������ǰ��
			overGame(over);
		}
		GameStart.changeJPanel(false);
	}
	
	//����Ԫ��
	private void loadElement() {
		ElementManager.getManager().loadMap();//���ص�ͼ����Ԫ��
	}
	
	/**
	 * �ؿ�����
	 * ���overΪ������Ϸʧ�ܷ��ؽ��棬���������һ��
	 * @param over
	 */
	private void overGame(Boolean over) {
		ElementManager.getManager().overGame(over);
	}
	
	//��ʾ�����Ϸ���̣��Զ���
	private void runGame() {
		while(running) {
			Map<String, List<SuperElement>> map = ElementManager.getManager().getMap();
			Set<String> set = map.keySet();
			for(String key:set) {
				List<SuperElement> list = map.get(key);
				for(int i=list.size()-1; i>=0; i--) {
					list.get(i).update();
					if(!list.get(i).isAlive())
						list.remove(i);
				}
			}
			
			//�����Ϸ�����̿���linkGame()?
			
			//�����ը����ײ����
			playerBoom();
			//���ƻ�����ը����ײ
			fragilityBoom();
			//������ը����ײ����
			npcBoom();
			//����������ײЧ��
			playerMagicBox();
			//����Ƿ����ȫ������
			defeat();
			
			//����runGame����
			try {	
				sleep(20);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	private void defeat() {
		boolean allDead = true;
		List<SuperElement> playerList = ElementManager.getManager().getElementList("player");
		for(SuperElement se:playerList) {
			if(!((Player)se).isDead()) {
				allDead = false;
			}
		}
		if(allDead) {
			running = false;
			over = true;
		}
	}
	
	//�����ը����ײ�ж�
	private void playerBoom() {
		List<SuperElement> playerList = ElementManager.getManager().getElementList("player");
		List<SuperElement> explodeList = ElementManager.getManager().getElementList("explode");
		for(int i=0; i<playerList.size(); i++) {
			for(int j=0; j<explodeList.size(); j++) {
				if(explodeList.get(j).crash(playerList.get(i))){
					Player player = (Player) playerList.get(i);
					player.setDead(true);
					player.setX(-100);
					player.setY(-100);
				}
			}
		}
		
	}
	//npc��ը����ײ�ж�
	private void npcBoom() {
		List<SuperElement> npcList = ElementManager.getManager().getElementList("npc");
		List<SuperElement> explodeList = ElementManager.getManager().getElementList("explode");
		for(int i=0; i<npcList.size(); i++) {
			for(int j=0; j<explodeList.size(); j++) {
				if(explodeList.get(j).crash(npcList.get(i))){
					Npc npc = (Npc) npcList.get(i);
					npc.setDead(true);
					npc.setX(-100);
					npc.setY(-100);
				}
			}
		}
	}
	
	//�ϰ�����ը����ײ�ж�
	private void fragilityBoom() {
		List<SuperElement> explodes = ElementManager.getManager().getElementList("explode");
		List<SuperElement> fragility = ElementManager.getManager().getElementList("fragility");
		for(int i=0; i<fragility.size(); i++) {
			for(int j=0; j<explodes.size(); j++) {
				if(explodes.get(j).crash(fragility.get(i))) {
					MapFragility mapFragility = (MapFragility)fragility.get(i);
					mapFragility.setDestoried(true);
				}
			}
		}
	}
	
	//����������ײ�ж�
	private void playerMagicBox() {
		List<SuperElement> playerList = ElementManager.getManager().getElementList("player");
		List<SuperElement> magicBoxList = ElementManager.getManager().getElementList("magicBox");
		for(int i=0; i<playerList.size(); i++) {
			for(int j=magicBoxList.size()-1; j>=0; j--) {
				if(magicBoxList.get(j).crash(playerList.get(i))){
					MagicBox magicBox = (MagicBox) magicBoxList.get(j);
					magicBox.setPlayer(i);//˭�Է���
					magicBox.setEaten(true);//���鱻��
				}
				
			}
		}
	}
	
	//runGame���ã�������չ
	public void linkGame() {}
	
	

}
