package gui.colorwheel;
import javax.swing.JComponent;

public class BrightnessSlider extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8983824381683513219L;

	private int width, height;
	private int cursor;
	
	public BrightnessSlider(int width, int height) {
		this.width = width;
		this.height = height;
		this.cursor = Math.max(width, height)/2;
	}

}
