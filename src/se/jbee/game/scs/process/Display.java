package se.jbee.game.scs.process;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JFrame;
import javax.swing.JPanel;

import se.jbee.game.scs.gfx.Figure;
import se.jbee.game.scs.gfx.Shapes;

public class Display extends Canvas implements Runnable {

	private static final long LOOP_TIME_MS = 25;
	
	private AtomicReference<List<Figure>> figures; 

	public Display(AtomicReference<List<Figure>> figures, KeyListener onKey, MouseListener onMouseClick, MouseMotionListener onMouseMove) {
		super();
		this.figures = figures;

		JFrame frame = new JFrame("Spacecraft");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true); 
		frame.setLocation(0, 0);
		
		JPanel panel = (JPanel) frame.getContentPane();
		Dimension screen = getToolkit().getScreenSize();
		panel.setPreferredSize(screen);
		panel.setLayout(null);
		setBounds(0,0, screen.width, screen.height);
		panel.add(this);
		
		// No AWT repaint for canvas - done manually
		setIgnoreRepaint(true);
		
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// add a key input system (defined below) to our canvas
		// so we can respond to key pressed
		//disableEvents(eventsToDisable);
		addKeyListener(onKey);
		addMouseListener(onMouseClick);
		addMouseMotionListener(onMouseMove);
		
		// request the focus so key events come to us
		requestFocus();

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
	}

	@Override
	public void run() {
		final BufferStrategy strategy = getBufferStrategy();
		final Dimension screen = getSize();
		while (true) {
			long loopStart = System.currentTimeMillis();
			// Get hold of a graphics context for the accelerated 
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g.setColor(Color.black);
			g.fillRect(0, 0, screen.width, screen.height);
			
			List<Figure> fs = figures.get();
			for (Figure f : fs) {
				Shapes.planet(g, f.data[0], f.data[1], f.data[2], new Color(f.data[3]));
			}
			
			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			strategy.show();

			// sleep so that drawing + sleeping = loop time
			long loopDurationMs = System.currentTimeMillis() - loopStart;
			System.out.println(loopDurationMs);
			if (loopDurationMs < LOOP_TIME_MS) {
				try { Thread.sleep(LOOP_TIME_MS - loopDurationMs); } catch (Exception e) {}
			}
		}		
	}
		
}
