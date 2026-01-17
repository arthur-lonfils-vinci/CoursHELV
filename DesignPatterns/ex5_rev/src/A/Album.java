package A;

public class Album {
    private final String title;
    private final String artist;
    private final int year;
    private final String type;
    private final String label;
    private final String productor;
    private final String country;
    private final String version;
    private final Boolean isRemastered;
    private final String originalYear;
    private final String standardQuality;
    private final String premiumQuality;

    private Album(AlbumBuilder builder) {
        this.title = builder.title;
        this.artist = builder.artist;
        this.year = builder.year;
        this.type = builder.type;
        this.label = builder.label;
        this.productor = builder.productor;
        this.country = builder.country;
        this.version = builder.version;
        this.isRemastered = builder.isRemastered;
        this.originalYear = builder.originalYear;
        this.standardQuality = builder.standardQuality;
        this.premiumQuality = builder.premiumQuality;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public String getProductor() {
        return productor;
    }

    public String getCountry() {
        return country;
    }

    public String getVersion() {
        return version;
    }

    public Boolean getRemastered() {
        return isRemastered;
    }

    public String getOriginalYear() {
        return originalYear;
    }

    public String getStandardQuality() {
        return standardQuality;
    }

    public String getPremiumQuality() {
        return premiumQuality;
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", label='" + label + '\'' +
                ", productor='" + productor + '\'' +
                ", country='" + country + '\'' +
                ", version='" + version + '\'' +
                ", isRemastered=" + isRemastered +
                ", originalYear='" + originalYear + '\'' +
                ", standardQuality='" + standardQuality + '\'' +
                ", premiumQuality='" + premiumQuality + '\'' +
                '}';
    }

    public static class AlbumBuilder {
        private final String title;
        private final String artist;
        private int year;
        private String type;
        private String label;
        private String productor;
        private String country;
        private String version;
        private Boolean isRemastered;
        private String originalYear;
        private String standardQuality;
        private String premiumQuality;

        public AlbumBuilder(String title, String artist) {
            this.title = title;
            this.artist = artist;
        }

        public AlbumBuilder year(int year) {
            this.year = year;
            return this;
        }

        public AlbumBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AlbumBuilder label(String label) {
            this.label = label;
            return this;
        }

        public AlbumBuilder productor(String productor) {
            this.productor = productor;
            return this;
        }

        public AlbumBuilder country(String country) {
            this.country = country;
            return this;
        }

        public AlbumBuilder version(String version) {
            this.version = version;
            return this;
        }

        public AlbumBuilder isRemastered(Boolean isRemastered) {
            this.isRemastered = isRemastered;
            return this;
        }

        public AlbumBuilder originalYear(String originalYear) {
            this.originalYear = originalYear;
            return this;
        }

        public AlbumBuilder premiumQuality(String premiumQuality) {
            this.premiumQuality = premiumQuality;
            return this;
        }

        public AlbumBuilder standardQuality(String standardQuality) {
            this.standardQuality = standardQuality;
            return this;
        }

        public Album build() {
            return new Album(this);
        }

    }
}
