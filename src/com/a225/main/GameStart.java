package com.a225.main;

import java.io.IOException;

import javax.swing.JFrame;

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

	//��Ϸ�������
	public static void main(String[] args) {
		// ��Դ����
		try {
			ElementLoader.getElementLoader().readGamePro();
			ElementLoader.getElementLoader().readImagePro();
			ElementLoader.getElementLoader().readCharactorsPro();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ������أ��Զ���������
		GameFrame gameFrame = new GameFrame();
		GameJPanel gameJPanel = new GameJPanel();
		GameKeyListener gameListener = new GameKeyListener();
		gameFrame.setKeyListener(gameListener);
		gameFrame.setjPanel(gameJPanel);
		gameFrame.addJPanel();
		// ��������
		gameFrame.addListener();
		// ��Ϸ��ʼ
		gameFrame.start();
	}

}
