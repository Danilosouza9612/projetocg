package view;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import renderer.DragRotation;

public class GLCanvasMouseEvent implements MouseListener{
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			PointerInfo mouseInfo = MouseInfo.getPointerInfo();
			Point b = mouseInfo.getLocation();
			DragRotation.getInstance().initializeDraggie(b.getLocation());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		DragRotation.getInstance().setDragRunning(false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
