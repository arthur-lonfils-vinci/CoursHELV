package domaine;

public interface Query {
    String getUrl();

    QueryMethod getMethod();

    void setUrl(String url);

    void setMethod(QueryMethod method);

    // Enum for HTTP Methods
    enum QueryMethod {
        GET, POST, PUT, DELETE;

        public static QueryMethod fromString(String method) {
            for (QueryMethod m : Query.QueryMethod.values()) {
                if (m.name().equalsIgnoreCase(method)) {
                    return m;
                }
            }
            throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
    }
}
