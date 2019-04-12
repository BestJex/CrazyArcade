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
	private Map<String, List<String>> squareTypeMap;//���������ֵ�
	private List<List<String>> mapList;//��ͼ
	

	//���캯��
	private ElementLoader() {
		properties = new Properties();
		gameInfoMap = new HashMap<>();
		imageMap = new HashMap<>();
		mapList = new ArrayList<>();
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
	
	//��ȡ������������
	public void readSquarePro() throws IOException{
		InputStream inputStream = 
				ElementLoader.class.getClassLoader().getResourceAsStream(gameInfoMap.get("squareProPath").get(0));
		properties.clear();
		properties.load(inputStream);
		for(Object o:properties.keySet()) {
			String info = properties.getProperty(o.toString());
			squareTypeMap.put(o.toString(),infoStringToList(info, ","));//����Map��value�е����Ѿ��ָ���������
		}
	}
	
	//��ȡ�ض���ͼ
	public void readMapPro(String mapPro) throws IOException{
		InputStream inputStream = 
				ElementLoader.class.getClassLoader().getResourceAsStream(gameInfoMap.get(mapPro).get(0));
		properties.clear();
		properties.load(inputStream);
		for(Object o:properties.keySet()) {
			String info = properties.getProperty(o.toString());
			mapList.add(infoStringToList(info,","));
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

	public List<List<String>> getMapList() {
		return mapList;
	}

	public Map<String, List<String>> getSquareTypeMap() {
		return squareTypeMap;
	}
	
	
	
}
