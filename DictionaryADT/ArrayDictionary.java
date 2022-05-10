package DictionaryADT;

import java.util.Iterator;

public class ArrayDictionary<K, V> implements DictionaryInterface<K, V> {
    private Entry<K,V>[] dictionary;
    private int numberOfEntries;

    private final static int DEFAULT_CAPACITY = 25;

    private void initializeDictionary(int initialCapacity) {
        if (initialCapacity < 0)
            initialCapacity = DEFAULT_CAPACITY;

        @SuppressWarnings("unchecked")
        Entry<K, V>[] tempDictionary = (Entry<K, V>[])new Entry[initialCapacity];
        dictionary = tempDictionary;
        numberOfEntries = 0;
    }

    public ArrayDictionary(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayDictionary(int initialCapacity){
        initializeDictionary(initialCapacity);
    }

    @Override
    public V add(K key, V value) {
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
                checkCapacity();
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
        int index = locateIndex(key);
        if (index == numberOfEntries)
            return null;
        return this.dictionary[index].getValue();
    }

    @Override
    public boolean contains(K key) {
        return locateIndex(key) != numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public int getSize() {
        return this.numberOfEntries;
    }

    @Override
    public void clear() {
        initializeDictionary(dictionary.length);
    }

    public void checkCapacity() {
        if (numberOfEntries == dictionary.length) {
            @SuppressWarnings("unchecked")
            Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[dictionary.length << 1];

            for (int i = 0; i < dictionary.length; ++i) {
                temp[i] = dictionary[i];
            }

            dictionary = temp;
        }
    }

    @Override
    public Iterator<V> getValueIterator() {
        return new Iterator<V>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < numberOfEntries;
            }

            @Override
            public V next() {
                return dictionary[currentIndex++].getValue();
            }
        };
    }

    @Override
    public Iterator<K> getKeyIterator() {
        return new Iterator<K>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < numberOfEntries;
            }

            @Override
            public K next() {
                return dictionary[currentIndex++].getKey();
            }
        };
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
