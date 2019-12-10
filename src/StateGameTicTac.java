
public class StateGameTicTac {
	private boolean pTurn;
	private boolean cTurn;
	public StateGameTicTac(boolean p, boolean c) {
		pTurn = p;
		cTurn = c;
	}
	public boolean getPTurn() {
		return pTurn;
	}
	public boolean getCTurn() {
		return cTurn;
	}
	
}
