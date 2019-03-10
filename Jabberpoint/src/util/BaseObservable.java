package util;

import java.util.LinkedList;
import java.util.List;

/**
 * This class implements the Observable pattern.
 * @author Daniel Schiavini
 * @param <ObservedType>: The type of the data being observed.
 */
public class BaseObservable<ObservedType> implements Observable<ObservedType> {

	/** A list of the observer instances **/
	private List<Observer<ObservedType>> observers = new LinkedList<Observer<ObservedType>>();

	/**
	 * Adds a new observer to this observable object.
	 * @param obs: The observer instance.
	 */
	public void addObserver(Observer<ObservedType> obs) {
		if (obs == null) {
			throw new IllegalArgumentException("Tried to add a null observer");
		}
		if (observers.contains(obs)) {
			return;
		}
		observers.add(obs);
	}

	/**
	 * Notifies all the observers.
	 * @param data: The data that changed.
	 */
	public void notifyObservers(ObservedType data) {
		for (Observer<ObservedType> obs : observers) {
			obs.update(data);
		}
	}
}
