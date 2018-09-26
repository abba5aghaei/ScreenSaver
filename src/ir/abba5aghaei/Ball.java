package ir.abba5aghaei;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Ball extends JFrame {

	private static final long serialVersionUID = 1L;
	private int X = 550;
	private int Y = 200;
	private Timer timer;
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private int ground = screen.width - 108;
	private int out = screen.height - 108;
	int flag = 1;

	public Ball() {
		super("ShapedWindow");

		addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				setShape(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
			}
		});

		setUndecorated(true);
		setBounds(X, Y, 108, 108);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			setIconImage(ImageIO.read(new File("basketball.png")));
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("basketball.png")))));
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		relase();
	}

	public void relase() {

		timer = new Timer();
		Go go = new Go();
		timer.schedule(go, 0, 5);
	}

	public void move() {

		if (flag == 1) {
			X++;
			Y--;
		}

		else if (flag == 2) {
			X++;
			Y++;
		}

		else if (flag == 3) {
			X--;
			Y++;
		}

		else if (flag == 4) {
			X--;
			Y--;
		}

		if (Y == 0)
			if (flag == 1)
				flag = 2;
			else
				flag = 3;

		if (X == ground)
			if (flag == 2)
				flag = 3;
			else
				flag = 4;

		if (Y == out)
			if (flag == 3)
				flag = 4;
			else
				flag = 1;

		if (X == 0)
			if (flag == 4)
				flag = 1;
			else
				flag = 2;
	}

	public class Go extends TimerTask {

		public void run() {

			move();

			setBounds(X, Y, 108, 108);
		}
	}
}