import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

public class ColorWheel extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6140004869396463759L;

	private final class SelectionListener extends MouseInputAdapter {

		private volatile boolean move = false;
		private final ColorWheel source;

		public SelectionListener(ColorWheel colorWheel) {
			source = colorWheel;
		}

		private void mouseAction(MouseEvent arg0) {
			if (isColorWheel(arg0.getX(), arg0.getY()) && move) {
				source.cursorX = source.relX(arg0.getX());
				source.cursorY = source.relY(arg0.getY());
				move = true;
				repaint();
			}
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			mouseAction(arg0);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			if (isColorWheel(arg0.getX(), arg0.getY()))
				move = true;
			mouseAction(arg0);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			move = false;
		}

	}

	private static float calculateDistance(int x, int y, int x2, int y2) {
		return (float) Math.hypot(x - x2, y - y2);
	}

	private static float calculateRotation(int x, int y, int x2, int y2) {
		return (float) (Math.atan((double) (y - y2) / (double) (x - x2)) / (Math.PI * 2)) + (x2 > x ? .5F : 0);
	}

	private int cursorX;

	private int cursorY;

	private volatile Image display;

	private int size;

	/**
	 * Creates a color wheel Component which can be added to a container and
	 * used to select a color
	 * 
	 * @param radius
	 *            the radius of the color circle in pixels
	 */
	public ColorWheel(int radius) {
		size = radius * 2;
		cursorX = radius;
		cursorY = radius;
		SelectionListener listener = new SelectionListener(this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}

	private int absX(int relX) {
		return relX + ((getWidth() - size) / 2);
	}

	private int absY(int relY) {
		return relY + ((getHeight() - size) / 2);
	}

	private Image createImage() {
		Image drawable = this.createImage(size, size);
		if (drawable == null)
			return null;
		Graphics g = drawable.getGraphics();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				float distance = 2 * calculateDistance(i, j, size / 2, size / 2) / size;
				if (distance <= 1) {
					g.setColor(Color.getHSBColor(calculateRotation(i, j, size / 2, size / 2), distance, 1F));
					g.fillRect(i, j, 1, 1);
				}
			}
		}
		g.dispose();
		return drawable;
	}

	/**
	 * Converts the user's selection into a color Object
	 * 
	 * @return the selected color
	 */
	public Color getSelectedColor() {
		return Color.getHSBColor(calculateRotation(cursorX, cursorY, size / 2, size / 2),
				2 * calculateDistance(cursorX, cursorY, size / 2, size / 2) / size, 1F);
	}

	/**
	 * Calculates the HSB for the currently selected color
	 * 
	 * @return the float values for the HSB of the selected color
	 */
	public float[] getHSB() {
		return new float[] { calculateRotation(cursorX, cursorY, size / 2, size / 2),
				2 * calculateDistance(cursorX, cursorY, size / 2, size / 2) / size, 1F };
	}

	private boolean isColorWheel(int x, int y) {
		return calculateDistance(relX(x), relY(y), size / 2, size / 2) <= (size / 2);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (display == null)
			display = createImage();
		g.drawImage(display, (this.getWidth() - size) / 2, (this.getHeight() - size) / 2, null);
		g.setColor(Color.white);
		g.fillRect(absX(cursorX) - 2, absY(cursorY) - 2, 4, 4);
		g.setColor(Color.black);
		g.drawRect(absX(cursorX) - 2, absY(cursorY) - 2, 4, 4);
	}

	private int relX(int absX) {
		return absX - ((getWidth() - size) / 2);
	}

	private int relY(int absY) {
		return absY - ((getHeight() - size) / 2);
	}

}
