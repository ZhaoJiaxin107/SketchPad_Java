package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;



public class EmptyCmd extends Command{
	public void executeClick(Point p, Drawing dwg) {
		dwg.list.clear();
	}

}
