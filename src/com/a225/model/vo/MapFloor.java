package com.a225.model.vo;

import javax.swing.ImageIcon;
import com.a225.model.loader.ElementLoader;

/**
 * ��ͼ�ذ���
 * @ClassName: MapFloor  
 * @Description:    
 * @author: WeiXiao
 * @CreateDate: 2019��4��13�� ����6:31:34
 */
public class MapFloor extends MapSquare{
	public MapFloor(int i, int j, ImageIcon img, int sx, int sy, int dx, int dy, int scaleX, int scaleY) {
		super(i, j, img, sx, sy, dx, dy, scaleX, scaleY);
	}
	
	public static MapFloor createMapFloor(int i,int j) {
		ImageIcon img = ElementLoader.getElementLoader().getImageMap().get("floor");
		return new MapFloor(i, j, img, 0, 0, 32, 32, 1, 1);
	}

	@Override
	public void update() {}

}
