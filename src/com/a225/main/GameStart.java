package com.a225.main;

import java.io.IOException;
import com.a225.frame.BeginJPanel;
import com.a225.frame.GameFrame;
import com.a225.frame.GameJPanel;
import com.a225.model.loader.ElementLoader;
import com.a225.thread.GameKeyListener;

/**
 * ��Ϸ�������
 * @ClassName: GameStart  
 * @Description:  
 * @author: WeiXiao
 * @CreateDate: 2019��4��8�� ����4:17:37
 */
public class GameStart {
	private static GameFrame gameFrame;
	private static BeginJPanel beginJPanel;
	private static GameKeyListener keyListener;

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
		keyListener = new GameKeyListener();
		beginJPanel = new BeginJPanel("img/bg/title.png");
		gameFrame.setjPanel(beginJPanel);
		gameFrame.addJPanel();
		// ��������
		
		// ������ʾ
		gameFrame.setVisible(true);
	}
	
	public static void changeJPanel(boolean gamePanel){
		if(GameController.isGameRunning()==gamePanel) return;
		gameFrame.setVisible(false);
		if(gamePanel){
			GameController.setGameRunning(true);
			gameFrame.setjPanel(new GameJPanel());
			gameFrame.setKeyListener(keyListener);
			gameFrame.addListener();
			gameFrame.addJPanel();
			gameFrame.start();
		} else {
			GameController.setGameRunning(false);
			gameFrame.setjPanel(beginJPanel);
			gameFrame.removeListener();
			gameFrame.addJPanel();
		}
		gameFrame.setVisible(false);
		gameFrame.setVisible(true);
	}

}
