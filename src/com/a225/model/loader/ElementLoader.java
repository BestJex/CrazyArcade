package com.a225.model.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;

/**
 * ��Դ������
 * ʹ�õ������ģʽ
 * @author Jenson
 *
 */
public class ElementLoader {
	private static ElementLoader elementLoader;
	private Properties properties;
	private Map<String, List<String>> gameInfoMap;//��Ϸ��Ϣ�ֵ�
	private Map<String, ImageIcon> imageMap;//ͼƬ�ֵ�
	private Map<String, List<String>> mapMap;//��ͼ�ֵ�
	

	//���캯��
	private ElementLoader() {
		properties = new Properties();
		gameInfoMap = new HashMap<>();
		imageMap = new HashMap<>();
		mapMap = new HashMap<>();
	}
	
	//����ģʽ
	public static ElementLoader getElementLoader() {
		if (elementLoader == null) {
			elementLoader = new ElementLoader();
		}
		return elementLoader;
	}
	
	//��ȡ�������ļ�
	public void readGamePro() throws IOException {
		InputStream inputStream = ElementLoader.class.getClassLoader().getResourceAsStream("com/a225/pro/Game.pro");
		properties.clear();
		properties.load(inputStream);
		for(Object o:properties.keySet()) {
			String info = properties.getProperty(o.toString());
			gameInfoMap.put(o.toString(), infoStringToList(info,","));
		}
	}
	
	//��ȡͼƬ
	public void readImagePro() throws IOException{
		InputStream inputStream = 
				ElementLoader.class.getClassLoader().getResourceAsStream(gameInfoMap.get("imageProPath").get(0));
		properties.clear();
		properties.load(inputStream);
		for(Object o:properties.keySet()) {
			String loc = properties.getProperty(o.toString());
			imageMap.put(o.toString(), new ImageIcon(loc));
		}
	}
	
	//��ȡ��Ϸ�������
	public void readCharactorsPro() throws IOException {
		InputStream inputStream = 
				ElementLoader.class.getClassLoader().getResourceAsStream(gameInfoMap.get("charatersPath").get(0));
		properties.clear();
		properties.load(inputStream);
		for(Object o:properties.keySet()) {
			String info = properties.getProperty(o.toString());
			gameInfoMap.put(o.toString(),infoStringToList(info, ","));//����Map��value�е����Ѿ��ָ���������
		}
	}
	
	/**
	//��ȡ��ͼ
	public void readMapPro() throws IOException{
		InputStream inputStream = 
				ElementLoader.class.getClassLoader().getResourceAsStream(gameInfoMap.get("mapProPath").get(0));
		properties.clear();
		properties.load(inputStream);
		for(Object o:properties.keySet()) {
			String loc = properties.getProperty(o.toString());
			mapMap.put(o.toString(), infoStringToList(loc,","));
		}
	}
	
	/**
	 * ���������ָ���ַ����и��תΪ�ַ���List
	 * @param info �������ַ���
	 * @param splitString �и��ַ���
	 * @return �и����ַ���List
	 */
	private List<String> infoStringToList(String info,String splitString){
		return Arrays.asList(info.split(splitString));
	}

	public Map<String, List<String>> getGameInfoMap() {
		return gameInfoMap;
	}

	public Map<String, ImageIcon> getImageMap() {
		return imageMap;
	}
	
	
	
}
