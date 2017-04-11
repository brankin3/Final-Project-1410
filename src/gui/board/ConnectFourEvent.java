package gui.board;

public class ConnectFourEvent {

	public static final int CLICK = 0;
	public static final int MOUSEOVER = 1;
	
	private int id;
	private int column;
	
	public ConnectFourEvent(int id, int column) {
		this.id = id;
		this.column = column;
	}
	
	public int getID() {
		return id;
	}
	
	public int getColumn() {
		return column;
	}

}
