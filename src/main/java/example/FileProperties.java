package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Component
public class FileProperties implements Dictionary {
    private final int keyLength;
    private final String keyRegex;
    private final String name;
    private Checker checker;
    private Path source;
    private Map<String, String> dictionary;

    @Autowired()
    public FileProperties(String filePath, int keyLength, String keyRegex, String name) {
        if (keyLength <= 0) this.keyLength = 1;
        else this.keyLength = keyLength;
        this.keyRegex = keyRegex + "{" + keyLength + "}";
        this.name = name;
        this.checker =new ValidChecker(this);
        initSource(filePath);
        initDictionary();
    }

    @Override
    public Map<String, String> getDictionary() {
        return new LinkedHashMap<>(dictionary);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getKeyRegex() {
        return keyRegex;
    }

    @Override
    public int getKeyLength() {
        return keyLength;
    }

    @Override
    public String remove(String key) throws Exception {
        if (checker.keyContains(key)) {
            String value = dictionary.remove(key);
            writeToFile();
            return checker.resultForRemove(key, value);
        } else return "Ключ не соответствует условиям";
    }

    @Override
    public String get(String key) throws Exception {
        if (checker.keyContains(key)) {
            String value = dictionary.get(key);

            return checker.resultForGet(key, value);
        } else return "Ключ не соответствует условиям";
    }

    @Override
    public String put(String key, String value) throws Exception {
        if (checker.isValidKey(key)) {
            dictionary.put(key, value);

            writeToFile();

            return checker.resultForPut(key, value);
        } else return "Ключ не соответствует условиям";
    }

    private String writeToFile() {
        String a = "";
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(source)) {
            for (Map.Entry<String, String> entry : dictionary.entrySet())
                bufferedWriter.write(entry.getKey() + "=" + entry.getValue() + "\n");
        } catch (IOException e) {
            a = e.getMessage();
        }
        return a;
    }

    private void initDictionary() {
        dictionary = new LinkedHashMap<>();
        try {
            List<String> lines = Files.readAllLines(source, StandardCharsets.UTF_8);

            for (String line : lines) {
                String[] keyValue = line.split("=");
                dictionary.put(keyValue[0], keyValue[1]);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private String initSource(String filePath) {
        source = Paths.get(filePath);
        String a = "";
        if (!Files.exists(source)) try {
            source = Files.createFile(source);
        } catch (IOException e) {
            a = e.getMessage();
        }
        return a;
    }
}
