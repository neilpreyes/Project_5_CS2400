package ListADT;

import java.util.Iterator;

public interface ListIteratorInterface<T> extends ListInterface<T>, Iterable<T>
{
   public Iterator<T> getIterator();
} 
