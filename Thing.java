package ascii;

public abstract class Thing {

	protected int x, y, h, w;
	protected Map owner;

	public Thing(int x, int y, Map owner) {
		this.x = x;
		this.y = y;
		this.owner = owner;
	}

	public Thing(Map owner) {
		this.owner = owner;

	}

	public abstract void generateAnatomy();

	public int getPosition() {
		return x + y * 25;
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}

	public abstract void checkStatus();

	public abstract void adjustGoals();

	public abstract void move();
}
