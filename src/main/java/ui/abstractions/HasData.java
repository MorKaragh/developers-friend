package ui.abstractions;

public interface HasData<T> {

    T gatherData();
    void mountData(T data);

}
