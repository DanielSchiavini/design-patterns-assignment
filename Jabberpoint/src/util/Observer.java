package util;

/**
 * This interface specifies the observers in the observable pattern.
 * @author Daniel
 * @param <ObservedType>: The type of the data being observed.
 */
public interface Observer<ObservedType> {
	/**
	 * Notifies the observer that the data has changes.
	 * @param data: The changed data.
	 */
	public void update(ObservedType data);
}
