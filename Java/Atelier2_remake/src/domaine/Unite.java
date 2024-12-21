package domaine;

public enum Unite {
    GRAMME("gr"), KILOGRAMME("kg"), LITRE("l"), MILLILITRE("ml"), CENTILITRE("cl"), DECILITRE("dl"),
    CUILLER_A_CAFE("cc"), CUILLER_A_THE("ct"), CUILLER_A_DESSERT("cd"), CUILLER_A_SOUPE("cs"), PINCEE("pincée"), UN_PEU("peu"), NEANT("");

    private String unite;

    Unite(String unite) {
        this.unite = unite;
    }

    public String getUnite() {
        return unite;
    }

    @Override
    public String toString() {
        return unite;
    }
}
