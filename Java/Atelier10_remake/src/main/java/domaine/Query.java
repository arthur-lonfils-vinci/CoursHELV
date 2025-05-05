package domaine;

public interface Query {
    /**
     * Retrieves the URL of the query.
     *
     * @return the URL as a String
     */
    String getUrl();

    /**
     * Retrieves the HTTP method of the query.
     *
     * @return the HTTP method as a QueryMethod enum
     */
    QueryMethod getMethod();

    /**
     * Retrieves the domain of the query.
     *
     * @return the domain as a String
     */
    String getDomain();

    /**
     * Sets the URL of the query.
     *
     * @param url the URL to set
     */
    void setUrl(String url);

    /**
     * Sets the HTTP method of the query.
     *
     * @param method the HTTP method to set
     */
    void setMethod(QueryMethod method);

    /**
     * Enum representing HTTP methods.
     */
    enum QueryMethod {
        GET, POST, PUT, DELETE;

        /**
         * Converts a string to a QueryMethod enum.
         *
         * @param method the string representation of the HTTP method
         * @return the corresponding QueryMethod enum
         * @throws IllegalArgumentException if the method is invalid
         */
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
