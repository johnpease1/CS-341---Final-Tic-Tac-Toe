//game class
import java.util.ArrayList;

public class Game implements Subject {
	//data member
	private java.util.List<Observer> observers;
	
	public Game() {
		observers = new ArrayList<Observer>();
	}

	public void attach(Observer o) {
		observers.add(o);
		
	}

	public void notifyTurn(StateGameTicTac t) {
		observers.get(0).update(t);
		
	}
}
