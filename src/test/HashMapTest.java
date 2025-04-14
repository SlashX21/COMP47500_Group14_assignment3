package test;

import util.Song;
import java.util.HashMap;
import java.util.Map;
import util.CSVReader;
import hashmaps.StandardSongMap;
import hashmaps.CustomSongMap;
import java.util.ArrayList;
import java.util.List;

public class HashMapTest {
    public static void compareImplementations(String filePath, int dataSize) {
        StandardSongMap standardMap = new StandardSongMap();
        CustomSongMap customMap = new CustomSongMap();
        
        // Load all songs first
        Map<String, Song> allSongs = new HashMap<>();
        CSVReader.loadSongs(filePath, allSongs);
        List<Song> testData = new ArrayList<>(allSongs.values());
        
        // Use only specified size
        int actualSize = Math.min(dataSize, testData.size());
        System.out.println("\nTesting with data size: " + actualSize);
        System.out.println("-----------------------------------");
        
        // Test StandardSongMap
        long start = System.nanoTime();
        for (int i = 0; i < actualSize; i++) {
            Song song = testData.get(i);
            standardMap.addSong(song);
        }
        long standardLoadTime = System.nanoTime() - start;
        
        // Test CustomSongMap
        start = System.nanoTime();
        for (int i = 0; i < actualSize; i++) {
            Song song = testData.get(i);
            customMap.put(song.getName(), song);
        }
        long customLoadTime = System.nanoTime() - start;
        
        // Test retrieval performance
        List<String> songNames = new ArrayList<>();
        for (int i = 0; i < actualSize; i++) {
            songNames.add(testData.get(i).getName());
        }
        
        // Test StandardSongMap retrieval
        start = System.nanoTime();
        for (String songName : songNames) {
            standardMap.getSong(songName);
        }
        long standardRetrievalTime = System.nanoTime() - start;
        
        // Test CustomSongMap retrieval
        start = System.nanoTime();
        for (String songName : songNames) {
            customMap.get(songName);
        }
        long customRetrievalTime = System.nanoTime() - start;
        
        // Print results
        System.out.println("\nPerformance Results:");
        System.out.println("Total songs processed: " + actualSize);
        System.out.println("\nStandard HashMap Implementation:");
        System.out.println("Loading time: " + standardLoadTime / 1_000_000.0 + " ms");
        System.out.println("Average insertion time: " + (standardLoadTime / actualSize) + " ns per song");
        System.out.println("Average retrieval time: " + (standardRetrievalTime / actualSize) + " ns per song");
        
        System.out.println("\nCustom HashMap Implementation:");
        System.out.println("Loading time: " + customLoadTime / 1_000_000.0 + " ms");
        System.out.println("Average insertion time: " + (customLoadTime / actualSize) + " ns per song");
        System.out.println("Average retrieval time: " + (customRetrievalTime / actualSize) + " ns per song");
        
        System.out.println("\nPerformance Comparison (Custom/Standard):");
        System.out.println("Loading time ratio: " + String.format("%.2f", (double)customLoadTime/standardLoadTime));
        System.out.println("Retrieval time ratio: " + String.format("%.2f", (double)customRetrievalTime/standardRetrievalTime));
    }

    public static void warmUp(String filePath) {
        System.out.println("Warming up JVM...");
        // Load data once and reuse it for warm-up
        Map<String, Song> warmupData = new HashMap<>();
        CSVReader.loadSongs(filePath, warmupData);
        List<Song> songs = new ArrayList<>(warmupData.values()).subList(0, 100);
        
        // Increased to 5000 iterations
        for (int i = 0; i < 5000; i++) {
            StandardSongMap warmupMap1 = new StandardSongMap();
            CustomSongMap warmupMap2 = new CustomSongMap();
            
            for (Song song : songs) {
                warmupMap1.addSong(song);
                warmupMap2.put(song.getName(), song);
            }
            
            for (Song song : songs) {
                warmupMap1.getSong(song.getName());
                warmupMap2.get(song.getName());
            }
            
            if (i % 250 == 0) {  // Adjusted progress indicator frequency
                System.out.print(".");
            }
        }
        System.out.println("\nWarm-up complete\n");
    }

    public static void main(String[] args) {
        String filePath = "C:/Users/User/Documents/UNI/UCD/Java/Assignment4/src/MusicMoodFinal.csv";
        System.out.println("Starting HashMap Implementation Comparison...");
        
        // Warm up the JVM first
        warmUp(filePath);
        
        // Test with different data sizes
        int[] dataSizes = {10, 100, 1000, 10000, 586672};
        for (int size : dataSizes) {
            compareImplementations(filePath, size);
        }
    }
}