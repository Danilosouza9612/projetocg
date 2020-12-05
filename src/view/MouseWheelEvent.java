package view;

import java.awt.event.MouseWheelListener;

import service.AppService;

public class MouseWheelEvent implements MouseWheelListener{

	@Override
	public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {
		// TODO Auto-generated method stub
		AppService appState = AppService.getInstance();
		appState.zoom(e.getWheelRotation());
	}

}
