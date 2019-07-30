package example;

import java.util.Map;

public interface Dictionary {
    Map<String, String> getDictionary();
    String getName();
    String getKeyRegex();
    int getKeyLength();
    String remove(String key) throws Exception;
    String get(String key) throws Exception;
    String put(String key, String value) throws Exception;
}
