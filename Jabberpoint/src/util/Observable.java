package util;

/**
 * This interface specifies the Observable pattern.
 * @author Daniel Schiavini
 * @param <ObservedType>: The type of the data being observed.
 */
public interface Observable<ObservedType> {
	/**
	 * Adds a new observer to this observable object.
	 * @param obs: The observer instance.
	 */
	public void addObserver(Observer<ObservedType> obs);

	/**
	 * Notifies all the observers.
	 * @param data: The data that changed.
	 */
	public void notifyObservers(ObservedType data);
}
