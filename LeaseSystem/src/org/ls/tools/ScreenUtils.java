package org.ls.tools;


import java.awt.Dimension;
import java.awt.Toolkit;


public class ScreenUtils {

	private static Dimension scrSize = getScreenSize();

	/**
	 * 保证窗体显示在中间
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public static int[] getCenterLocation(int width, int height) {
		int[] pos = new int[4];
		pos[0] = (scrSize.width - width) / 2;
		pos[1] = (scrSize.height - height) / 2;
		pos[2] = width;
		pos[3] = height;
		return pos;
	}

	/**
	 * 获取屏幕大小
	 * 
	 * @return
	 */
	public static Dimension getScreenSize() {
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		return scrSize;
	}
}