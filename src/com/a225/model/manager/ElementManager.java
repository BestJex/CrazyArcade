package com.a225.model.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.a225.model.vo.Player;
import com.a225.model.vo.SuperElement;

/**
 * 
 * @author Jenson
 * Ԫ�ع�����
 * ����ģʽ
 */
public class ElementManager {
	
	//Ԫ�ع���������
	private static ElementManager elementManager;
	static {
		elementManager = new ElementManager();
	}
	
	//Ԫ�ص�Map����
	Map<String, List<SuperElement>> map;
	
	//��ʼ������
	protected void init() {
		map = new HashMap<>();
	}
	
	//���캯��
	private ElementManager() {
		init();
		
		//��ʱ����player
		List<SuperElement> list = new ArrayList<>();
		list.add(Player.createPlayer(""));
		map.put("player", list);
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

}
