import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ConfigParser {
    Map<String, String> configEntryMap;

    public ConfigParser(File configFilePath) {
        configEntryMap = new HashMap<>();
        parseFile(configFilePath);
    }

    private void parseFile(File configFilePath) {
        try (Scanner scan = new Scanner(configFilePath)) {  // create new scanner object to read file
            String prefix = "";   // prefix variable used to form key name in map
            while (scan.hasNext()) {
                String line = scan.nextLine();    // read file line by line
                if (line.isBlank()) {
                    if (!prefix.isBlank()) prefix = "";      // reset prefix at line breaks
                } else if (!line.contains("=")) {
                    prefix = line.substring(1, line.length() - 1) + ".";    // create prefix from file
                } else {
                    String[] keyValuePair = line.split("=");
                    if (configEntryMap.containsKey(prefix + keyValuePair[0])) {  // continue loop if HashMap contains key name already
                        continue;
                    }
                    configEntryMap.put(
                            prefix + keyValuePair[0],
                            keyValuePair[1]
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return configEntryMap.get(key);
    }

    public Map<String, String> getConfigEntryMap() {
        return (HashMap) ((HashMap) configEntryMap).clone();
    }
}
