package blacklist;

import domaine.Query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

public class BlacklistService {

    private static Set<String> blacklistDomains;

    static {
        try {
            FileInputStream inputStream = new FileInputStream("blacklist.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            blacklistDomains = Set.of(properties.getProperty("blacklistedDomains").split(";"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean check(Query query) {
        System.out.println("Checking if domain is blacklisted");
        System.out.println("Domain: " + query.getDomain());
        return blacklistDomains.stream().anyMatch(d -> query.getDomain().contains(d));
    }
}
