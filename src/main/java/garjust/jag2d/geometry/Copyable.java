package garjust.jag2d.geometry;

/**
 * Deep copy interface
 * 
 * All classes implementing this interface MUST provide a full deep copy
 * implementation
 * 
 * @param <E>
 */
public interface Copyable<E> {

    public E copy();
}
