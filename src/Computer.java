//computer class
public class Computer implements Observer {
	public boolean update(boolean b) {
		return b;
	}

	@Override
	public boolean update(StateGameTicTac t) {
		// TODO Auto-generated method stub
		return t.getCTurn();
	}
	


}
