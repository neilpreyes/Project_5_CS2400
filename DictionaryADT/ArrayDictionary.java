package DictionaryADT;

import java.util.Iterator;

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
    public V getValue(K key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean contains(K key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    public void checkCapacity(int capacity){

    }

    public void checkIntegrity(){

    }

    public void ensureCapacity(){
        
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
