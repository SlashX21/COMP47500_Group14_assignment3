package hashmaps;

import java.util.HashMap;
import java.util.Map;
import util.Song;

public class StandardSongMap {
    private Map<String, Song> songMap = new HashMap<>();

    public void addSong(Song song) {
        songMap.put(song.getName(), song);
    }

    public Song getSong(String name) {
        return songMap.get(name);
    }

    public int size() {
        return songMap.size();
    }

    public Map<String, Song> getMap() {
        return songMap;
    }
}