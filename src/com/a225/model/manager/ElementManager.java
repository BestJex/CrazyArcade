package com.a225.model.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.a225.model.loader.ElementLoader;
import com.a225.model.vo.GameMap;
import com.a225.model.vo.MapFloor;
import com.a225.model.vo.SuperElement;

/**
 * Ԫ�ع�����
 * ����ģʽ
 * @author Jenson
 */
public class ElementManager {
	//Ԫ�ع���������
	private static ElementManager elementManager;
	static {
		elementManager = new ElementManager();
	}
	
	//Ԫ�ص�Map����
	private Map<String, List<SuperElement>> map;
	
	//��Ϸ��ͼ
	private GameMap gameMap;
	
	//��ʼ������
	protected void init() {
		Map<String, List<String>> gameInfoMap = ElementLoader.getElementLoader().getGameInfoMap();
		List<String> windowSize = gameInfoMap.get("windowSize");
		gameMap = new GameMap(Integer.parseInt(windowSize.get(0)),Integer.parseInt(windowSize.get(1)));
		map = new HashMap<>();
	}
	
	//���캯��
	private ElementManager() {
		init();
		//��ʼ��player��list
		map.put("player", new ArrayList<SuperElement>());//���
		map.put("bubble", new ArrayList<SuperElement>());//ˮ��
		map.put("explode",new ArrayList<SuperElement>());//ˮ�ݱ�ը
		map.put("fragility", new ArrayList<SuperElement>());
		map.put("floor", new ArrayList<SuperElement>());
		map.put("obstacle", new ArrayList<SuperElement>());
	}
	
	
	//��ֵ�Ƚ���
	public Comparator<String> getMapKeyComparator() {
		Map<String, Integer> priorityMap = new HashMap<>();
		priorityMap.put("player", 50);
		priorityMap.put("bubble", 10);
		priorityMap.put("explode", 30);
		priorityMap.put("fragility", 20);
		priorityMap.put("floor", -10);
		priorityMap.put("obstacle", 40);
		return new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int p1 = priorityMap.get(o1);
				int p2 = priorityMap.get(o2);
				if(p1 > p2) {
					return 1;
				} else if(p1 < p2) {
					return -1;
				} else {
					return 0;
				}
			}
		};
	}
	
	//ͼ��͸�ӱȽ���	�̲߳���ȫ
	public Comparator<SuperElement> getElementComparator() {
		return new Comparator<SuperElement>() {
			@Override
			public int compare(SuperElement o1, SuperElement o2) {
				if(o1 instanceof MapFloor) return -1;//�ذ���Զ������ʾ
	
				int loc1 = o1.getY()+o1.getH();
				int loc2 = o2.getY()+o2.getH();
				if(loc1 > loc2) {
					return 1;
				} else if(loc1 < loc2) {
					return -1;
				} else {
					return 0;
				}
			}
		};
	}
	
	//���map����
	public Map<String, List<SuperElement>> getMap(){
		return map;
	}
	
	//�õ�Ԫ��list
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}
	
	//Ԫ�ع��������
	public static ElementManager getManager() {
		return elementManager;
	}
	
	//��ȡ��Ϸ��ͼ��
	public GameMap getGameMap() {
		return gameMap;
	}

	public void loadElement() {
		// TODO Auto-generated method stub
		//map.get("player").add(ElementFactory.getElementFactory().produceElement("playerOne"));
		
	}
	
	public void loadMap(){
		gameMap.createMap("stage1Map");
	}

	public void overGame() {
		gameMap.clearMap();
	}

}

