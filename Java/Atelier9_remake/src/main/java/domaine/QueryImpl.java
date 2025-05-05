package domaine;

class QueryImpl implements Query {
    private String url;
    private QueryMethod method;

    QueryImpl(){}

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public QueryMethod getMethod() {
        return method;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setMethod(QueryMethod method) {
        this.method = method;
    }

}
