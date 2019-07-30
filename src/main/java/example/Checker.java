package example;

public interface Checker {
    String resultForRemove(String key, String value);
    String resultForGet(String key, String value);
    String resultForPut(String key, String value);
    boolean isValidKey(String key) throws Exception;
    boolean keyContains(String key) throws Exception;
}