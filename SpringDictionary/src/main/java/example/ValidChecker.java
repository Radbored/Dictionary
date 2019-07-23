package example;

public class ValidChecker implements Checker {
    private String result;
    private Dictionary dictionary;

    public ValidChecker(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String resultForRemove(String key, String value) {
        return result = key + "=" + value + " удален";
    }

    @Override
    public String resultForGet(String key, String value) {
        return result = key + "=" + value;
    }

    @Override
    public String resultForPut(String key, String value) {
        return result = key + "=" + value + " добавлен";
    }

    @Override
    public boolean isValidKey(String key) {
        if (key.length() < dictionary.getKeyLength()) {
            result = "Ключ слишком короткий";
            return false;
        }

        if (key.length() > dictionary.getKeyLength()) {
            result = "Ключ слишком длинный";
            return false;
        }

        if (!key.matches(dictionary.getKeyRegex())) {
            result = "Не соответствует длинне";
            return false;
        }
        return true;
    }
    @Override
    public boolean keyContains(String key) {
        if (!dictionary.getDictionary().containsKey(key)) {
            result = "Не содержит ключ";
            return false;
        }
        return true;
    }
}
