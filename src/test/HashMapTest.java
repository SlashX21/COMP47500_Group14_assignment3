package test;

import util.Song;
import util.CSVReader;
import hashmaps.StandardSongMap;
import hashmaps.CustomSongMap;

public class HashMapTest {
    public static void compareImplementations(String filePath) {
        StandardSongMap standardMap = new StandardSongMap();
        CustomSongMap customMap = new CustomSongMap();
        
        // Test loading and insertion
        System.out.println("\nTesting Song Loading and Insertion:");
        System.out.println("-----------------------------------");
        
        // Test StandardSongMap
        long start = System.nanoTime();
        CSVReader.loadSongs(filePath, standardMap.getMap());
        long standardLoadTime = System.nanoTime() - start;
        
        // Test CustomSongMap
        start = System.nanoTime();
        for (Song song : standardMap.getMap().values()) {
            customMap.put(song.getName(), song);
        }
        long customLoadTime = System.nanoTime() - start;
        
        // Test retrieval performance
        System.out.println("\nTesting Retrieval Performance:");
        System.out.println("-----------------------------");
        
        // Test StandardSongMap retrieval
        start = System.nanoTime();
        for (String songName : standardMap.getMap().keySet()) {
            standardMap.getSong(songName);
        }
        long standardRetrievalTime = System.nanoTime() - start;
        
        // Test CustomSongMap retrieval
        start = System.nanoTime();
        for (String songName : standardMap.getMap().keySet()) {
            customMap.get(songName);
        }
        long customRetrievalTime = System.nanoTime() - start;
        
        // Print results
        int totalSongs = standardMap.size();
        System.out.println("\nPerformance Results:");
        System.out.println("Total songs processed: " + totalSongs);
        System.out.println("\nStandard HashMap Implementation:");
        System.out.println("Loading time: " + standardLoadTime / 1_000_000.0 + " ms");
        System.out.println("Average insertion time: " + (standardLoadTime / totalSongs) + " ns per song");
        System.out.println("Average retrieval time: " + (standardRetrievalTime / totalSongs) + " ns per song");
        
        System.out.println("\nCustom HashMap Implementation:");
        System.out.println("Loading time: " + customLoadTime / 1_000_000.0 + " ms");
        System.out.println("Average insertion time: " + (customLoadTime / totalSongs) + " ns per song");
        System.out.println("Average retrieval time: " + (customRetrievalTime / totalSongs) + " ns per song");
        
        System.out.println("\nPerformance Comparison (Custom/Standard):");
        System.out.println("Loading time ratio: " + String.format("%.2f", (double)customLoadTime/standardLoadTime));
        System.out.println("Retrieval time ratio: " + String.format("%.2f", (double)customRetrievalTime/standardRetrievalTime));
    }

    public static void main(String[] args) {
        String filePath = "C:/Users/User/Documents/UNI/UCD/Java/Assignment4/src/MusicMoodFinal.csv";
        System.out.println("Starting HashMap Implementation Comparison...");
        compareImplementations(filePath);
    }
}