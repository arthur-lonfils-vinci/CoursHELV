package domaine;

public class QueryFactory {
    public static Query getQuery() {
        return new QueryImpl();
    }
}

