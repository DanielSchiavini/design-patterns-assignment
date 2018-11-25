package util;

public interface Observer<ObservedType> {
    public void update(ObservedType data);
}
