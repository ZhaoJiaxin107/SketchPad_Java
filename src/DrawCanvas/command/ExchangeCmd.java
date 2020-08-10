package DrawCanvas.command;

import java.awt.Point;

import DrawCanvas.panel.Drawing;
import DrawCanvas.shape.Shape;


public class ExchangeCmd extends Command{
	private Shape firstShape; // the first Shape clicked

	public void executeClick(Point p, Drawing dwg) {
		// Find the frontmost shape containing p.
		Shape s = dwg.getFrontmostContainer(p);

		if (s != null) { // was there a Shape containing p
			if (firstShape == null)
				firstShape = s; // save this Shape for when there's another
			// click
			else {
				// We have two Shapes to exchange. Get their centers.
				Point firstCenter = firstShape.getCenter();
				Point secondCenter = s.getCenter();

				// Exchange their centers.
				firstShape.setCenter(secondCenter);
				s.setCenter(firstCenter);

				// Now we get to start all over.
				firstShape = null;
			}
		}
	}
}
