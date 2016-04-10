package sk.tera.DAOTest;

/**
 * Functional interface which extended the Consumer interface about throwing of the Exception.
 *
 * @author z0rk1
 */
@FunctionalInterface
public interface ConsumerWithException<T, E extends  Exception> {
    void accept(T t) throws E;
}
