package DrawCanvas.panel;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import DrawCanvas.command.BackCmd;
import DrawCanvas.command.ColorCmd;
import DrawCanvas.command.Command;
import DrawCanvas.command.CopyCmd;
import DrawCanvas.command.CutCmd;
import DrawCanvas.command.DrawShape;
import DrawCanvas.command.EmptyCmd;
import DrawCanvas.command.ExchangeCmd;
import DrawCanvas.command.FrontCmd;
import DrawCanvas.command.MoveCmd;
import DrawCanvas.command.PasteCmd;
import DrawCanvas.command.RedoCmd;
import DrawCanvas.command.ReshapeCmd;
import DrawCanvas.command.UndoCmd;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Main extends JApplet   {
	private static final long serialVersionUID = 1L;
	int i=0;

	private final int APPLET_WIDTH = 1200, APPLET_HEIGHT = 800;
	private final Color initialColor = Color.BLACK; // default color starts as black

	private Command cmd; // the command being executed
	private Drawing dwg; // the drawing: shapes in order
	private ColorIndicator colorBox; // a GUI component to show the current
	private JTextField Information=new JTextField(30);								// default color

	BufferedImage bi;
	Graphics g = null;
	Image tempImage = null;
	
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
			
			bi = new BufferedImage(1024,800,BufferedImage.TYPE_INT_RGB);
			
			// The drawing will appear in a white CanvasPanel.
			CanvasPanel canvasPanel = new CanvasPanel();
			canvasPanel.setBackground(Color.white);
			g  = bi.getGraphics();

			// Make JButton objects for all the command buttons.
			JButton rectButton = new JButton("Rectangle");
			JButton ovalButton = new JButton("Oval");
			JButton lineButton = new JButton("Line");
			JButton hexaButton = new JButton("Hexagon");
			JButton circleButton = new JButton("Circle");
			JButton squareButton = new JButton("Square");
			JButton curveButton = new JButton("Curve");
			
			JButton moveButton = new JButton("Move");
			JButton cutButton = new JButton("Cut");
			JButton pasteButton = new JButton("Paste");
			JButton frontButton = new JButton("Front");
			JButton backButton = new JButton("Back");
			JButton exchangeButton = new JButton("Exchange");
			JButton copyButton = new JButton("Copy");
			JButton emptyButton = new JButton("Empty");
			JButton reshapeButton = new JButton("Reshape");
			JButton undoButton = new JButton("Undo");
			JButton redoButton = new JButton("Redo");
			JButton saveButton = new JButton("Save");
			JButton openButton = new JButton("Open");
			
			JButton blackButton = new JButton("Black");
			JButton redButton = new JButton("Red");
			JButton greenButton = new JButton("Green");
			JButton yellowButton = new JButton("Yellow");
			JButton blueButton = new JButton("Blue");
			JButton pinkButton = new JButton("Pink");
			JButton grayButton = new JButton("Gray");
			JButton purpleButton = new JButton("Purple");
			JButton orangeButton = new JButton("Orange");
			JButton cyanButton = new JButton("Cyan");

		
			// Add listeners for all the command buttons.
			rectButton.addActionListener(new RectButtonListener());
			ovalButton.addActionListener(new OvalButtonListener());
			lineButton.addActionListener(new LineButtonListener());
			hexaButton.addActionListener(new HexaButtonListener());
			circleButton.addActionListener(new CircleButtonListener());
			squareButton.addActionListener(new SquareButtonListener());
			curveButton.addActionListener(new CurveButtonListener());
			
			moveButton.addActionListener(new MoveButtonListener());
			cutButton.addActionListener(new CutButtonListener());
			pasteButton.addActionListener(new PasteButtonListener());
			frontButton.addActionListener(new FrontButtonListener());
			backButton.addActionListener(new BackButtonListener());
			exchangeButton.addActionListener(new ExchangeButtonListener());
			copyButton.addActionListener(new CopyButtonListener());
			emptyButton.addActionListener(new EmptyButtonListener());
			reshapeButton.addActionListener(new ReshapeButtonListener());
			undoButton.addActionListener(new UndoButtonListener());
			redoButton.addActionListener(new RedoButtonListener());
			
			blackButton.addActionListener(new BlackButtonListener());
			redButton.addActionListener(new RedButtonListener());
			greenButton.addActionListener(new GreenButtonListener());
			yellowButton.addActionListener(new YellowButtonListener());
			blueButton.addActionListener(new BlueButtonListener());
			pinkButton.addActionListener(new PinkButtonListener());
			grayButton.addActionListener(new GrayButtonListener());
			purpleButton.addActionListener(new PurpleButtonListener());
			orangeButton.addActionListener(new OrangeButtonListener());
			cyanButton.addActionListener(new CyanButtonListener());
			// Shape Panel
			JPanel shapePanel = new JPanel(); // holds buttons for adding shapes
			JLabel shapeLabel = new JLabel("Add shape:");
			shapePanel.setLayout(new FlowLayout());
			shapePanel.add(shapeLabel);
			rectButton.setBackground(Color.pink);
			ovalButton.setBackground(Color.pink);
			lineButton.setBackground(Color.pink);
			hexaButton.setBackground(Color.pink);
			circleButton.setBackground(Color.pink);
			squareButton.setBackground(Color.pink);
			curveButton.setBackground(Color.pink);
			shapePanel.add(rectButton);
			shapePanel.add(ovalButton);
			shapePanel.add(lineButton);
			shapePanel.add(hexaButton);
			shapePanel.add(circleButton);
			shapePanel.add(squareButton);
			shapePanel.add(curveButton);
			// Edit Panel
			JPanel editPanel = new JPanel(); // holds buttons for editing operations
			JLabel editLabel = new JLabel("Operations:");
			editPanel.setLayout(new FlowLayout());
			editPanel.add(editLabel);
			moveButton.setBackground(Color.lightGray);
			cutButton.setBackground(Color.lightGray);
			pasteButton.setBackground(Color.lightGray);
			frontButton.setBackground(Color.lightGray);
			backButton.setBackground(Color.lightGray);
			exchangeButton.setBackground(Color.lightGray);
			copyButton.setBackground(Color.lightGray);
			emptyButton.setBackground(Color.lightGray);
			reshapeButton.setBackground(Color.lightGray);
			undoButton.setBackground(Color.lightGray);
			redoButton.setBackground(Color.lightGray);
			saveButton.setBackground(Color.lightGray);
			openButton.setBackground(Color.lightGray);
			editPanel.add(moveButton);
			editPanel.add(cutButton);
			editPanel.add(pasteButton);
			editPanel.add(frontButton);
			editPanel.add(backButton);
			editPanel.add(exchangeButton);
			editPanel.add(copyButton);
			editPanel.add(emptyButton);
			editPanel.add(reshapeButton);
			editPanel.add(undoButton);
			editPanel.add(redoButton);
			editPanel.add(saveButton);
			editPanel.add(openButton);
			// The color panel 
			JPanel colorPanel = new JPanel();
			JLabel colorLabel = new JLabel("Colors:");
			colorPanel.setLayout(new FlowLayout());
			colorPanel.add(colorLabel);
			colorBox = new ColorIndicator();
			colorBox.show(initialColor);
			colorPanel.add(colorBox);
			colorPanel.add(blackButton);
			colorPanel.add(redButton);
			colorPanel.add(greenButton);
			colorPanel.add(yellowButton);
			colorPanel.add(blueButton);
			colorPanel.add(pinkButton);
			colorPanel.add(grayButton);
			colorPanel.add(purpleButton);
			colorPanel.add(orangeButton);
			colorPanel.add(cyanButton);
			
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
			saveButton.addActionListener(new ActionListener()
			{
					public void actionPerformed(ActionEvent e)
					{
						 Frame f = new fileDialog("File Dialog Demo!");  
				            f.setVisible(false);  
				            f.setSize(100, 100);  
				  
				            FileDialog fd = new FileDialog(f, "File Dialog", FileDialog.SAVE);  
				            fd.setVisible(true);  
				            try  
				            {  
				                if (fd.getFile() != null)  
				                {  
				                    String pathStr = fd.getDirectory();  
				  
				                    pathStr = pathStr.replace("\\", "\\\\");  
				                    String path = pathStr + fd.getFile();  
				  
				                    ImageIO.write(bi, "JPEG", new File(path));  
				                }  
				  
				            }  
				            catch (IOException e2)  
				            {  
				                e2.printStackTrace();  
				            }  
				        }  
			});
			
			openButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Frame f = new fileDialog("Open");  
		            f.setVisible(false);  
		            f.setSize(100, 100);  
		            FileDialog fd1 = new FileDialog(f, "File Dialog", FileDialog.LOAD);  
		            fd1.setVisible(true);  
		            try  
		            {  
		                File f2 = new File(fd1.getDirectory(), fd1.getFile());  
		                FileInputStream readfile = new FileInputStream(f2);  
		                tempImage = ImageIO.read(new File(fd1.getDirectory(), fd1  
		                        .getFile()));  
		  
		                CanvasPanel canvasPanel = new CanvasPanel();  
		                canvasPanel.setBounds(400, 300, 100, 100);  
		                add(canvasPanel);  
		  
		            }  
		            catch (IOException e1)  
		            {  
		                e1.printStackTrace();  
		            }  
				}
			
			});
			
			
			
		}
		
	
		
		public void paint(Graphics g) {
			super.paint(g); // make all the GUI components paint themselves
			  
			//g.drawImage(bi,0,0,this);
		}
		
		
	

		
		//What to do when rectButton is pressed.
		private class RectButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  a  rectangle.");
				cmd=new DrawShape("Rectangle");
				repaint();
			}
		}

		// What to do when ellipseButton is pressed.
		private class OvalButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  an oval.");
				cmd=new DrawShape("Oval");
				repaint();
			}
		}

		// What to do when lineButton is pressed.
		private class LineButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  a  line.");
				cmd=new DrawShape("Line");
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
		
		private class CircleButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  a  circle.");
				cmd=new DrawShape("Circle");
				repaint();
			}
		}
		
		private class SquareButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  a  square.");
				cmd=new DrawShape("Square");
				repaint();
			}
		}
		
		private class CurveButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Draw  a  Curve.");
				cmd=new DrawShape("Curve");
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
		private class CutButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Cut  the  shape.");
				cmd=new CutCmd();
				repaint();
			}
		}
		private class PasteButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				Information.setText("Paste  the  shape.");
				cmd=new PasteCmd();
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
		
		private class UndoButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				Information.setText("Undo operation");
				cmd=new UndoCmd(); 
				repaint();
			}
		}
		
		private class RedoButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				Information.setText("Redo operation");
				cmd=new RedoCmd(); 
				repaint();
			}
		}
		
		// What to do when blackButton is pressed.
		private class BlackButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.black); // show that the new default color is black
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
				colorBox.show(Color.yellow); // show that the new default color is yellow
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
		
		// What to do when pinkButton is pressed.
		private class PinkButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.pink); // show that the new default color is pink
				Information.setText("Set  the  shape's  color  to  pink.");
				dwg.color=Color.pink;
				cmd=new ColorCmd();
				repaint();
			}  
		}
		// What to do when grayButton is pressed.
		private class GrayButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.gray); // show that the new default color is gray
				Information.setText("Set  the  shape's  color  to  gray.");
				dwg.color=Color.gray;
				cmd=new ColorCmd();
				repaint();
			}
		}
		
		// What to do when purpleButton is pressed.
		private class PurpleButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(new Color(128,0,128)); // show that the new default color is purple
				Information.setText("Set  the  shape's  color  to  purple.");
				dwg.color=new Color(128,0,128);
				cmd=new ColorCmd();
				repaint();
			}
		}
		
		private class OrangeButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.orange); // show that the new default color is orange
				Information.setText("Set  the  shape's  color  to  orange.");
				dwg.color=Color.orange;
				cmd=new ColorCmd();
				repaint();
			}
		}
		
		private class CyanButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorBox.show(Color.cyan); // show that the new default color is orange
				Information.setText("Set  the  shape's  color  to  cyan.");
				dwg.color=Color.cyan;
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

			public void paint(Graphics g) {
				super.paint(g); // execute the paint method of JPanel
				dwg.draw(g); // have the drawing draw itself
				//g.drawImage(bi, 0,0,this);
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

			//  other mouse events.
			public void mouseReleased(MouseEvent event) {
			}

			public void mouseEntered(MouseEvent event) {
			}

			public void mouseExited(MouseEvent event) {
			}

			public void mouseMoved(MouseEvent event) {
			}
			
		
		}
		 class fileDialog extends Frame  
		    {  
		  
		        private static final long serialVersionUID = 1L;  
		  
		        fileDialog(String title)  
		        {  
		            super(title);  
		            MyWindowAdapter adapter = new MyWindowAdapter(this);  
		            addWindowListener(adapter);  
		        }  
		    }  
		  
		    class MyWindowAdapter extends WindowAdapter  
		    {  
		        fileDialog sf;  
		  
		        public MyWindowAdapter(fileDialog sfr)  
		        {  
		            this.sf = sfr;  
		        }  
		  
		        public void windowClosing(WindowEvent we)  
		        {  
		            sf.setVisible(false);  
		        }  
		    }  


}