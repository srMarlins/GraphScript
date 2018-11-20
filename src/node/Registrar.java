package node;

public interface Registrar<T> {
    boolean register(T candidate);
}