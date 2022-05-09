package DictionaryADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDictionary<K, V> implements DictionaryInterface<K, V> {
    private Entry<K,V>[] dictionary;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private final static int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public ArrayDictionary(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayDictionary(int initialCapacity){
        checkCapacity(initialCapacity);

        @SuppressWarnings("unchecked")
        Entry<K, V>[] tempDictionary = (Entry<K, V>[])new Entry[initialCapacity];
        dictionary = tempDictionary;
        numberOfEntries = 0;
        integrityOK = true;
    }

    @Override
    public V add(K key, V value) {
        checkIntegrity();
        if((key == null) || (value == null)){
            throw new IllegalArgumentException("Cannot add null to this dictionary");
        }else{
            V result = null;
            int keyIndex = locateIndex(key);
            if(keyIndex < numberOfEntries){
                result = dictionary[keyIndex].getValue();
                dictionary[keyIndex].setValue(value);
            }else{
                dictionary[numberOfEntries] = new Entry<>(key, value);
                numberOfEntries++;
                ensureCapacity();
            }
            return result;
        }
    }

    private int locateIndex(K key){
        int index = 0;
        while((index < numberOfEntries) && !key.equals(dictionary[index].getKey())){
            index++;
        }
        return index;
    }

    @Override
    public V remove(K key) {
        checkIntegrity();
        V result = null;
        int keyIndex = locateIndex(key);
   
        if (keyIndex < numberOfEntries){
            result = dictionary[keyIndex].getValue();
            dictionary[keyIndex] = dictionary[numberOfEntries - 1];
            dictionary[numberOfEntries - 1] = null;
            numberOfEntries--;
        }

        return result;
    }


    @Override
    public boolean contains(K key) {
        
    }

    @Override
    public boolean isEmpty() {
        int num = 0;
        while(num < numberOfEntries){
            if(dictionary[num].equals(null)){
                num++;
            }else{
                return false;
            }
        }
        return true;
    }

    @Override
    public int getSize() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        for(int i = 0; i < numberOfEntries; i++){
            dictionary[i] = null;
        }
        
    }

    public void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY){
            throw new IllegalArgumentException("Too many extries. Exceed maximum capacity");
        }else if(capacity < DEFAULT_CAPACITY){
            Entry<K,V>[] dictionary = (Entry<K, V>[])new Entry[DEFAULT_CAPACITY];
        }else{
            Entry<K,V>[] dictionary = (Entry<K, V>[])new Entry[capacity];
        }
    }

    public void checkIntegrity(){
        if(!integrityOK){
            throw new IllegalStateException("Dictionary is not or has not been properly initialized.");
        }
    }

    public void ensureCapacity(){
        checkCapacity(dictionary.length);
    }

    @Override
    public Iterator<V> getValueIterator() {
        return null;
    }

    @Override
    public K getKeyIterator() {
        // TODO Auto-generated method stub
        return null;
    }
    
    private class Entry<K, V>{
        private K key;
        private V value;
        
        
        private Entry(K searchKey, V dataValue){
            key = searchKey;
            value = dataValue;
        }

        
        private K getKey(){
            return key;
        }

        
        private V getValue(){
            return value;
        }

        private void setValue(V dataValue){
            value = dataValue;
        }
    }

   
}
