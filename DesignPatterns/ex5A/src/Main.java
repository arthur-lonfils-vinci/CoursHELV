public class Main {
    public static void main(String[] args) {
        Album lonerism = new Album.AlbumBuilder("Lonerism",
                "Tame Impala").country("Australie").year(2012).type("indie rock").build(); Album
                orange = new Album.AlbumBuilder("channel ORANGE",
                "Frank Ocean").year(2012).type("RB").build();
        Album visions = new Album.AlbumBuilder("Visions",
                "Grimes").year(2012).label("4AD").type("Electronic").build();

        System.out.println(lonerism);
        System.out.println(visions);
    }
}