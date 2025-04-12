package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CSVReader {
    public static void loadSongs(String filename, Map<String, Song> map) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String name = data[0].trim();
                    String artist = data[1].trim();
                    String mood = data[2].trim();

                    Song song = new Song.SongBuilder()
                            .setName(name)
                            .setArtists(artist)
                            .setMood(mood)
                            .build();

                    map.put(name, song);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
