package DrawCanvas.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DrawCanvas.command.BackCmd;
import DrawCanvas.command.ColorCmd;
import DrawCanvas.command.Command;
import DrawCanvas.command.CopyCmd;
import DrawCanvas.command.DeleteCmd;
import DrawCanvas.command.DrawShape;
import DrawCanvas.command.EmptyCmd;
import DrawCanvas.command.ExchangeCmd;
import DrawCanvas.command.FrontCmd;
import DrawCanvas.command.MoveCmd;
import DrawCanvas.command.ReshapeCmd;


public class Main extends JApplet{
	private static final long serialVersionUID = 1L;
	int i=0;

	private final int APPLET_WIDTH = 700, APPLET_HEIGHT = 500;
	private final Color initialColor = Color.BLACK; // default color starts as black

	private Command cmd; // the command being executed
	private Drawing dwg; // the drawing: shapes in order
	private ColorIndicator colorBox; // a GUI component to show the current
	private JTextField Information=new JTextField(30);								// default color
	
	// A ColorIndicator shows what the current color is.
		private class ColorIndicator extends JPanel {
			private static final long serialVersionUID = 0;

			private final int COLORBOX_WIDTH = 20, COLORBOX_HEIGHT = 20;

			// Constructor sets the size and border.
			public ColorIndicator() {
				setBorder(BorderFactory.createEtchedBorder());
				setPreferredSize(new Dimension(COLORBOX_WIDTH, COLORBOX_HEIGHT));
			}

			// Show a new color.
			public void show(Color color) {
				setBackground(color);
			}
		}
		
		
		
		public void init() {
			cmd = new Command(); // all methods in Command are empty
			dwg = new Drawing(initialColor); // make an empty drawing

			// The drawing will appear in a white CanvasPanel.
			CanvasPanel canvasPanel = new CanvasPanel();
			canvasPanel.setBackground(Color.white);
			
			// Make JButton objects for all the command buttons.
			JButton rectButton = new JButton("Rectangle");
			JButton ovalButton = new JButton("Oval");
			JButton lineButton = new JButton("Line");
			JButton hexaButton = new JButton("Hexagon");
			JButton moveButton = new JButton("Move");
			JButton deleteButton = new JButton("Delete");
			JButton frontButton = new JButton("Front");
			JButton backButton = new JButton("Back");
			JButton exchangeButton = new JButton("Exchange");
			JButton copyButton = new JButton("Copy");
			JButton emptyButton = new JButton("Empty");
			JButton reshapeButton = new JButton("Reshape");
			JButton blackButton = new JButton("Black");
			JButton redButton = new JButton("Red");
			JButton greenButton = new JButton("Green");
			JButton yellowButton = new JButton("Yellow");
			JButton blueButton = new JButton("Blue");
			JButton pinkButton = new JButton("Pink");
			JButton grayButton = new JButton("Gray");
			
			// Add listeners for all the command buttons.
			rectButton.addActionListener(new RectButtonListener());
			ovalButton.addActionListener(new OvalButtonListener());
			lineButton.addActionListener(new LineButtonListener());
			hexaButton.addActionListener(new HexaButtonListener());
			moveButton.addActionListener(new MoveButtonListener());
			deleteButton.addActionListener(new DeleteButtonListener());
			frontButton.addActionListener(new FrontButtonListener());
			backButton.addActionListener(new BackButtonListener());
			exchangeButton.addActionListener(new ExchangeButtonListener());
			copyButton.addActionListener(new CopyButtonListener());
			emptyButton.addActionListener(new EmptyButtonListener());
			reshapeButton.addActionListener(new ReshapeButtonListener());
			blackButton.addActionListener(new BlackButtonListener());
			redButton.addActionListener(new RedButtonListener());
			greenButton.addActionListener(new GreenButtonListener());
			yellowButton.addActionListener(new YellowButtonListener());
			blueButton.addActionListener(new BlueButtonListener());
			pinkButton.addActionListener(new PinkButtonListener());
			grayButton.addActionListener(new GrayButtonListener());
			
			// Shape Panel
			JPanel shapePanel = new JPanel(); // holds buttons for adding shapes
			JLabel shapeLabel = new JLabel("Add shape:");
			shapePanel.setLayout(new FlowLayout());
			shapePanel.add(shapeLabel);
			rectButton.setBackground(Color.pink);
			ovalButton.setBackground(Color.pink);
			lineButton.setBackground(Color.pink);
			hexaButton.setBackground(Color.pink);
			shapePanel.add(rectButton);
			shapePanel.add(ovalButton);
			shapePanel.add(lineButton);
			shapePanel.add(hexaButton);
			
			// Edit Panel
			JPanel editPanel = new JPanel(); // holds buttons for editing operations
			JLabel editLabel = new JLabel("Edit the shape with operations:");
			editPanel.setLayout(new FlowLayout());
			editPanel.add(editLabel);
			moveButton.setBackground(Color.lightGray);
			deleteButton.setBackground(Color.lightGray);
			frontButton.setBackground(Color.lightGray);
			backButton.setBackground(Color.lightGray);
			exchangeButton.setBackground(Color.lightGray);
			copyButton.setBackground(Color.lightGray);
			emptyButton.setBackground(Color.lightGray);
			reshapeButton.setBackground(Color.lightGray);
			editPanel.add(moveButton);
			editPanel.add(deleteButton);
			editPanel.add(frontButton);
			editPanel.add(backButton);
			editPanel.add(exchangeButton);
			editPanel.add(copyButton);
			editPanel.add(emptyButton);
			editPanel.add(reshapeButton);
			
			// The color panel 
			JPanel colorPanel = new JPanel();
			JLabel colorLabel = new JLabel("Colors:");
			colorPanel.setLayout(new FlowLayout());
			colorPanel.add(colorLabel);
			colorBox = new ColorIndicator();
			colorBox.show(initialColor);
			/*redButton.setBackground(Color.yellow);
			greenButton.setBackground(Color.yellow);
			blueButton.setBackground(Color.yellow);*/
			colorPanel.add(colorBox);
			colorPanel.add(blackButton);
			colorPanel.add(redButton);
			colorPanel.add(greenButton);
			colorPanel.add(yellowButton);
			colorPanel.add(blueButton);
			colorPanel.add(pinkButton);
			colorPanel.add(grayButton);
			
			//info panel
			JPanel inforPanel=new JPanel();
			JLabel inforLabel=new JLabel("Operation Information:");
			inforPanel.setLayout(new FlowLayout());
			inforPanel.add(inforLabel);
			Information.setEditable(false);
			Information.setBackground(Color.cyan);
			Information.setFont(new Font("Californian FB",Font.BOLD,16));
			inforPanel.add(Information);
			inforPanel.setBackground(Color.cyan);
			
			// Use a grid layout to stack the button panels vertically.
			// Also, give them a cyan background.
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(4, 1));
			shapePanel.setBackground(Color.cyan);
			editPanel.setBackground(Color.cyan);
			colorPanel.setBackground(Color.cyan);
			buttonPanel.add(shapePanel);
			buttonPanel.add(editPanel);
			buttonPanel.add(colorPanel);
			buttonPanel.add(inforPanel);
			
			// Now we have two panels: buttonPanel and canvasPanel. We want
			// buttonPanel to appear above canvasPanel, and canvasPanel should grow
			// with the applet.
			Container cp = getContentPane();
			cp.setLayout(new BorderLayout());
			cp.add(buttonPanel, BorderLayout.NORTH);
			cp.add(canvasPanel, BorderLayout.CENTER);

			setSize(APPLET_WIDTH, APPLET_HEIGHT);
			
		}
		
		public void paint(Graphics g) {
			super.paint(g); // make all the GUI components paint themselves
		}
		
		//What to do when rectButton is pressed.
		private class RectButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  a  rectangle.");
				cmd=new DrawShape("Rectangle");
				repaint();
			}
		}

		// What to do when ovalButton is pressed.
		private class OvalButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  a  oval.");
				cmd=new DrawShape("Oval");
				repaint();
			}
		}

		// What to do when lineButton is pressed.
		private class LineButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  a  segment.");
				cmd=new DrawShape("Segment");
				repaint();
			}
		}
		
		private class HexaButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  a  hexagon.");
				cmd=new DrawShape("Hexagon");
				repaint();
			}
		}

		// What to do when moveButton is pressed.
		private class MoveButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Move  the  shape.");
				cmd=new MoveCmd();
				repaint();
			}
		}

		// What to do when deleteButton is pressed.
		private class DeleteButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Delet  the  shape.");
				cmd=new DeleteCmd();
				repaint();
			}
		}

		// What to do when frontButton is pressed.
		private class FrontButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Move  the  shape  to  the  front.");
				cmd=new FrontCmd();
				repaint();
			}
		}

		// What to do when backButton is pressed.
		private class BackButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Move  the  shape  to  the  back.");
				cmd=new BackCmd();
				repaint();
			}
		}

		// What to do when exchangeButton is pressed.
		private class ExchangeButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Exchange  two  shapes'  positions.");
				cmd=new ExchangeCmd();
				repaint();
			}
		}
		
		private class CopyButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				Information.setText("Copy  the  shape.");
				cmd=new CopyCmd();
				repaint();
			}
		}
		
		private class EmptyButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				Information.setText("Click  the  white  panel, and  clear the  whole  panel.");
				cmd=new EmptyCmd();
				repaint();
			}
		}
		
		private class ReshapeButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				Information.setText("Reshape  the  shape.");
				cmd=new ReshapeCmd(); 
				repaint();
			}
		}
		
		// What to do when blackButton is pressed.
		private class BlackButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.black); // show that the new default color is red
				Information.setText("Set  the  shape's  color  to  black.");
				dwg.color=Color.black;
				cmd=new ColorCmd();
				repaint();
			}
		}

		// What to do when redButton is pressed.
		private class RedButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.red); // show that the new default color is red
				Information.setText("Set  the  shape's  color  to  red.");
				dwg.color=Color.red;
				cmd=new ColorCmd();
				repaint();
			}
		}

		// What to do when greenButton is pressed.
		private class GreenButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.green); // show that the new default color is
											// green
				Information.setText("Set  the  shape's  color  to  green.");
				dwg.color=Color.green;
				cmd=new ColorCmd();
				repaint();
			}
		}
		
		// What to do when yellowButton is pressed.
		private class YellowButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.yellow); // show that the new default color is red
				Information.setText("Set  the  shape's  color  to  yellow.");
				dwg.color=Color.yellow;
				cmd=new ColorCmd();
				repaint();
			}
		}

		// What to do when blueButton is pressed.
		private class BlueButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.blue); // show that the new default color is
											// blue
				Information.setText("Set  the  shape's  color  to  blue.");
				dwg.color=Color.blue;
				cmd=new ColorCmd();
				repaint();
			}
		}
		
		// What to do when redButton is pressed.
		private class PinkButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.pink); // show that the new default color is red
				Information.setText("Set  the  shape's  color  to  pink.");
				dwg.color=Color.pink;
				cmd=new ColorCmd();
				repaint();
			}
		}
		// What to do when redButton is pressed.
		private class GrayButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.gray); // show that the new default color is red
				Information.setText("Set  the  shape's  color  to  gray.");
				dwg.color=Color.gray;
				cmd=new ColorCmd();
				repaint();
			}
		}
	
		// CanvasPanel is the class upon which we actually draw.
		// It listens for mouse events and calls the appropriate method of the
		// current command.
		private class CanvasPanel extends JPanel implements MouseListener,
				MouseMotionListener {
			private static final long serialVersionUID = 0;

			// Constructor just needs to set up the CanvasPanel as a listener.
			public CanvasPanel() {
				addMouseListener(this);
				addMouseMotionListener(this);
			}

			public void paint(Graphics page) {
				super.paint(page); // execute the paint method of JPanel
				dwg.draw(page); // have the drawing draw itself
			}

			// When the mouse is clicked, call the executeClick method of the
			// current command.
			public void mouseClicked(MouseEvent event) {
				cmd.executeClick(event.getPoint(), dwg);
				repaint();
			}

			// When the mouse is pressed, call the executePress method of the
			// current command.
			public void mousePressed(MouseEvent event) {
				cmd.executePress(event.getPoint(), dwg);
				repaint();
			}

			// When the mouse is dragged, call the executeDrag method of the current
			// command.
			public void mouseDragged(MouseEvent event) {
				cmd.executeDrag(event.getPoint(), dwg);
				repaint();
			}

			// We don't care about the other mouse events.
			public void mouseReleased(MouseEvent event) {
			}

			public void mouseEntered(MouseEvent event) {
			}

			public void mouseExited(MouseEvent event) {
			}

			public void mouseMoved(MouseEvent event) {
			}
		}
	

}
