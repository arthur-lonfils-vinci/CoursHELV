package domaine;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
    public String getDomain() {
        try {
        URI url = new URI(this.getUrl());

        if (url.getHost() == null) {
            return null;
        }

        if (url.getHost().startsWith("www.")) {
            return url.getHost().substring(4);
        }

        return url.getHost();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
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
