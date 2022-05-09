package DictionaryADT;

import java.util.Iterator;

import GraphADT.*;

public interface DictionaryInterface<K, V> {
    public V add(K key, V value);
    public V remove(K key);
    public boolean contains(K key);
    public boolean isEmpty();
    public int getSize();
    public void clear();
    public K getKeyIterator();
    public Iterator<V> getValueIterator();
}
