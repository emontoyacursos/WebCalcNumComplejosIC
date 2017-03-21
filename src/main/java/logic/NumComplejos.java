package logic;

public class NumComplejos {
	float r, i;

	public NumComplejos(float r, float i) {
		this.r = r;
		this.i = i;
	}

	public NumComplejos add(NumComplejos c2) {
		float rtmp = r + c2.getR();
		float itmp = i + c2.getI();
		NumComplejos r = new NumComplejos(rtmp, itmp);
		return r;
	}

	public NumComplejos subtract(NumComplejos c2) {
		float rtmp = r - c2.getR();
		float itmp = i - c2.getI();
		NumComplejos r = new NumComplejos(rtmp, itmp);
		return r;
	}

	public NumComplejos multiply(NumComplejos c2) {
		NumComplejos r = null;
		return r;
	}

	public NumComplejos divide(NumComplejos c2) {
		NumComplejos r = null;
		return r;
	}

	public float getR() {
		return r;
	}

	public float getI() {
		return i;
	}

}
