package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;


public class DeleteCmd extends Command{
	public void executeClick(Point p, Drawing dwg) {
		Shape g=dwg.getFrontmostContainer(p);
		dwg.list.remove(g);
	}
}
