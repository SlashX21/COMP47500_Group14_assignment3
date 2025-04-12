package util;

public class Song implements PopularityComparable {
    String id;
    String name;
    int popularity;
    int durationMs;
    String artists;
    String releaseDate;
    String mood;
    private Song(SongBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.popularity = builder.popularity;
        this.durationMs = builder.durationMs;
        this.artists = builder.artists;
        this.releaseDate = builder.releaseDate;
        this.mood = builder.mood;
    }
    @Override
    public int getPopularity() {
        return popularity;
    }
    @Override
    public void setPopularity(int newPopularity) {
        this.popularity = newPopularity;
    }
    public String getName() {
        return name;
    }
    public String getMood() {
        return mood;
    }
    public String getArtists() {
        return artists;
    }
    public static class SongBuilder {
        private String id;
        private String name;
        private int popularity;
        private int durationMs;
        private String artists;
        private String releaseDate;
        private String mood;

        public SongBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public SongBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SongBuilder setPopularity(int popularity) {
            this.popularity = popularity;
            return this;
        }

        public SongBuilder setDurationMs(int durationMs) {
            this.durationMs = durationMs;
            return this;
        }

        public SongBuilder setArtists(String artists) {
            this.artists = artists;
            return this;
        }

        public SongBuilder setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public SongBuilder setMood(String mood) {
            this.mood = mood;
            return this;
        }
        public Song build() {
            return new Song(this);
        }
    }
}
