package domaine;

public class QueryFactoryImpl implements QueryFactory {

    public Query getQuery() {
        return new QueryImpl();
    }
}

