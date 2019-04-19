package com.a225.model.vo;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import com.a225.model.manager.MoveTypeEnum;

/**
 * ��ɫ��
 * @ClassName: Character  
 * @Description:Ϊ��Һ͵��Եĸ��࣬�����������Թ��е����Ժͷ���    
 * @author: WeiXiao
 * @CreateDate: 2019��4��18�� ����5:27:20
 */
public class Character extends SuperElement{
	public final static int INIT_SPEED = 4; //��ʼ�ƶ��ٶ�
	
	protected boolean dead;//��¼�Ƿ���
	protected MoveTypeEnum moveType;
	protected int speed;//�ƶ��ٶ�
	protected int speedItemCount;//��Ч�еļ��ٿ�����
	protected int bubblePower;//�ڵ�����
	protected int bubbleNum;//��¼����Ѿ����˶��ٸ�ը��
	protected int bubbleLargest;//��������ԷŶ��ٸ�ը������ʼֵΪ3
	public int score;

	public Character(int x, int y, int w, int h) {
		super(x, y, w, h);
		moveType = MoveTypeEnum.STOP;
		speedItemCount = 0;
		bubblePower = 2;
		bubbleNum = 0;
		bubbleLargest = 1;
		speed = INIT_SPEED;
		score = 0;
		dead = false;
	}
	
	//	�ı�һ��ʱ����ƶ��ٶ�,�����ٶ���Ҫ�����ı����ͳ�����ʱ�䣨�룩
	public void changeSpeed(double times,int lastTime) {
		speed = (int)(speed*times);
		Timer timer = new Timer(true);
		speedItemCount++;
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				speedItemCount--;
				if(speedItemCount==0) {
					speed = INIT_SPEED;					
				}
			}
		};
		timer.schedule(task,lastTime*1000);
	}

	@Override
	public void showElement(Graphics g) {}

	@Override
	public void move() {}

	@Override
	public void destroy() {}
	

	
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public MoveTypeEnum getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveTypeEnum moveType) {
		this.moveType = moveType;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeedItemCount() {
		return speedItemCount;
	}

	public void setSpeedItemCount(int speedItemCount) {
		this.speedItemCount = speedItemCount;
	}

	public int getBubblePower() {
		return bubblePower;
	}

	public void setBubblePower(int bubblePower) {
		this.bubblePower = bubblePower;
	}

	public int getBubbleNum() {
		return bubbleNum;
	}

	public void setBubbleNum(int bubbleNum) {
		this.bubbleNum = bubbleNum;
	}

	public int getBubbleLargest() {
		return bubbleLargest;
	}

	public void setBubbleLargest(int bubbleLargest) {
		this.bubbleLargest = bubbleLargest;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
