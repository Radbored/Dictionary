package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidChecker implements Checker {
    private Dictionary dictionary;
    @Autowired
    public ValidChecker(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String resultForRemove(String key, String value) {
        return key + "=" + value + " удален";
    }

    @Override
    public String resultForGet(String key, String value) {
        return "Искомый элемент: " + key + "=" + value;
    }

    @Override
    public String resultForPut(String key, String value) {
        return key + "=" + value + " добавлен";
    }

    @Override
    public boolean isValidKey(String key) {
        if (key.length() < dictionary.getKeyLength() || key.length() > dictionary.getKeyLength() || !key.matches(dictionary.getKeyRegex())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean keyContains(String key) {
        if (!dictionary.getDictionary().containsKey(key)) {
            return false;
        }
        return true;
    }
}
