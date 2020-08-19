package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;

public class PasteCmd extends Command{
	CutCmd dc = new CutCmd();
	
	public void executeClick(Point p, Drawing dwg) {
		if(dc.g1!=null) {
			dwg.list.add(dc.g1);
		}
	}

}
