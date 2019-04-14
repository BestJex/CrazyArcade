package com.a225.main;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.a225.frame.BeginJPanel;
import com.a225.frame.GameFrame;
import com.a225.frame.GameJPanel;
import com.a225.model.loader.ElementLoader;
import com.a225.thread.GameKeyListener;
import com.a225.thread.GameThread;

/**
 * ��Ϸ�������
 * @ClassName: GameStart  
 * @Description:  
 * @author: WeiXiao
 * @CreateDate: 2019��4��8�� ����4:17:37
 */
public class GameStart {
	private static GameFrame gameFrame;
	private static GameJPanel gameJPanel;
	private static BeginJPanel beginJPanel;
	public static boolean gameRuning = false;
	public static boolean beginLock = true;

	//��Ϸ�������
	public static void main(String[] args) {
		// ��Դ����
		try {
			ElementLoader.getElementLoader().readGamePro();
			ElementLoader.getElementLoader().readImagePro();
			ElementLoader.getElementLoader().readCharactorsPro();
			ElementLoader.getElementLoader().readBubblePro();
			ElementLoader.getElementLoader().readSquarePro();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ������أ��Զ���������
		gameFrame = new GameFrame();
		gameJPanel = new GameJPanel();
		new Thread((Runnable)gameJPanel).start();
		GameThread gameThread = new GameThread();
		gameThread.start();
		beginJPanel = new BeginJPanel("img/bg/title.png");
		GameKeyListener gameListener = new GameKeyListener();
		gameFrame.setKeyListener(gameListener);
		gameFrame.setjPanel(beginJPanel);
		gameFrame.addJPanel();
		// ��������
		gameFrame.addListener();
		// ��Ϸ��ʼ
		gameFrame.setVisible(true);
	}
	
	public static void changeJPanel(){

		if(gameRuning){
			beginLock  = true;
			gameFrame.setjPanel(beginJPanel);
		} else {
			beginLock  = false;
			gameFrame.setjPanel(gameJPanel);
		}
		gameFrame.addJPanel();
		gameRuning = !gameRuning;
		gameFrame.setVisible(true);
		//gameFrame.start();
		
	}

}
