//player class
public class Player implements Observer{
	public boolean update(boolean b) {
		return b;
	}

	@Override
	public boolean update(StateGameTicTac t) {
		// TODO Auto-generated method stub
		return t.getPTurn();
	}
	public boolean getTurn() {
		return false;
	}
}
