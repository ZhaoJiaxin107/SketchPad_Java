package DrawCanvas.command;

import java.awt.Point;
import java.util.ArrayList;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;

public class UndoCmd extends Command{
	public ArrayList<Shape> undolist = new ArrayList<Shape>();
	public void executeClick(Point p, Drawing dwg) {
		Shape g=dwg.list.get(dwg.list.size()-1);
		dwg.list.remove(g);
		undolist.add(g);
		//System.out.println(undolist);
	}
}
