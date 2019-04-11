package com.a225.frame;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		// TODO Auto-generated method stub
		this.setTitle("CrazyArcade");
		this.setSize(1200, 800);
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
		if(jPanel!=null)
			this.add(jPanel);
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
	}
	
}
