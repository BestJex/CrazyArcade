package com.a225.frame;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;

import com.a225.model.manager.ElementManager;
import com.a225.model.vo.SuperElement;

/**
 * 
 * @author Jenson
 * ����������������
 */
public class GameJPanel extends JPanel implements Runnable{
	
//	��ʾ�������ݣ��滭
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//ǰ����
		gameRuntime(g);
		//�νӶ���
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint(); //ÿ��100����ˢ�»���
		}
	}
	
	//չʾԪ�ع����������е�Ԫ��
	public void gameRuntime(Graphics g) {
		Map<String, List<SuperElement>> map = ElementManager.getManager().getMap();
		
//		List<SuperElement> tempList = new ArrayList<>();
//		for (List<SuperElement> list:map.values()) {
//			tempList.addAll(list);
//		}
//		tempList.sort(ElementManager.getManager().getElementComparator());
//		for (SuperElement superElement: tempList) {
//			superElement.showElement(g);
//		}

		Set<String> set = map.keySet();
		Set<String> sortSet = new TreeSet<String>(ElementManager.getManager().getMapKeyComparator());
        sortSet.addAll(set);
		for(String key:sortSet) {
			List<SuperElement> list = map.get(key);
			for(int i=0; i<list.size(); i++) {
				list.get(i).showElement(g);
			}
		}

	}
	

}
