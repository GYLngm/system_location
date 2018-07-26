package org.ls.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Hover formed when the hand shape
 */
public class MyMouseMotionAdapter extends MouseMotionAdapter {

	private Component component;

	public MyMouseMotionAdapter(Component component) {
		this.component = component;
	}

	public void mouseMoved(MouseEvent e) {
		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}