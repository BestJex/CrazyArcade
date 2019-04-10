package com.a225.frame;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		// TODO Auto-generated method stub
		super.paint(g);
		//ǰ����
		gameRuntime(g);
		//�νӶ���
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(100);
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
		Set<String> set = map.keySet();
		for(String key:set) {
			List<SuperElement> list = map.get(key);
			for(int i=0; i<list.size(); i++) {
				list.get(i).showElement(g);
			}
		}
	}
	

}
