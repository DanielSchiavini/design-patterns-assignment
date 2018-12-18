package util;

import java.util.LinkedList;
import java.util.List;

public class Observable<ObservedType> {

	private List<Observer<ObservedType>> observers = new LinkedList<Observer<ObservedType>>();

	public void addObserver(Observer<ObservedType> obs) {
		if (obs == null) {
			throw new IllegalArgumentException("Tried to add a null observer");
		}
		if (observers.contains(obs)) {
			return;
		}
		observers.add(obs);
	}

	public void notifyObservers(ObservedType data) {
		for (Observer<ObservedType> obs : observers) {
			obs.update(data);
		}
	}
}
