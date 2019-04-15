package com.a225.frame;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.a225.model.loader.ElementLoader;
import com.a225.thread.GameThread;

/**
 * ��Ϸ����
 * @author Jenson
 * 
 */
public class GameFrame  extends JFrame{
	
	private KeyListener keyListener; //��Ϸ����
	private MouseListener mouseListener; //��Ϸ���ʹ�������
	private JPanel jPanel; //����
	
	public GameFrame() {
		init();
	}

//	��ʼ��
	protected void init() {
		List<String> str = ElementLoader.getElementLoader().getGameInfoMap().get("windowSize");
		this.setTitle("CrazyArcade");
		this.setSize(new Integer(str.get(0)).intValue(), new Integer(str.get(1)).intValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
//	�󶨼���
	public void addListener() {
		if(keyListener!=null)
			this.addKeyListener(keyListener);
		if(mouseListener!=null)
			this.addMouseListener(mouseListener);
	}
	
//	����ע��
	public void addJPanel() {
		if(jPanel!=null){
			this.setContentPane(jPanel);
		}
	}
	
	public void removeListener() {
		if(keyListener!=null) {
			this.removeKeyListener(keyListener);
			keyListener = null;
		}
	}
	
//	��������
	public void start() {
		//�߳�����
		GameThread gameThread = new GameThread();
		gameThread.start();
		//����ˢ���߳�����
		if(jPanel instanceof Runnable) {//jp����ָ���ʵ����� �ǲ���Runnable�����ࣨʵ���ࣩ
			new Thread((Runnable)jPanel).start();
		}
		this.setVisible(true);
	}
	
	
	
//	getter and setter
	public KeyListener getKeyListener() {
		return keyListener;
	}

	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}

	public MouseListener getMouseListener() {
		return mouseListener;
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public JPanel getjPanel() {
		return jPanel;
	}

	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
		//this.setContentPane(jPanel);
		//this.jPanel.setBounds(0, 0, getWidth(), getHeight());
	}
	
}
