package hashmaps;

import util.Song;

public class CustomSongMap {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    
    private Entry[] table;
    private int size;
    
    private static class Entry {
        String key;
        Song value;
        Entry next;
        
        Entry(String key, Song value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public CustomSongMap() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }
    
    private int hash(String key) {
        return Math.abs(key.hashCode() % table.length);
    }
    
    public void put(String key, Song value) {
        if ((double) size / table.length >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        
        int index = hash(key);
        Entry entry = table[index];
        
        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        
        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
    }
    
    private void resize() {
        Entry[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;
        
        for (Entry entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }
    
    public Song get(String key) {
        int index = hash(key);
        Entry entry = table[index];
        
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }
    
    public int size() {
        return size;
    }
}