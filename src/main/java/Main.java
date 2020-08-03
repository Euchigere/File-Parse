import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mode = "production";
		if (args.length > 0) {
			mode = args[0].toLowerCase();
		}

		ConfigParser config = null;

		switch (mode) {
			case "production":
				config = new ConfigParser(new File("src/main/java/config.txt"));
				break;
			case "staging":
				config = new ConfigParser(new File("src/main/java/config.txt.dev"));
				break;
			case "development":
				config = new ConfigParser(new File("src/main/java/config.txt.staging"));
				break;
			default:
				System.err.println("Invalid argument");
		}
	}
}

class ConfigParser{
	Map<String, String> configKeyValuePair;

	public ConfigParser(File configFilePath) {
		configKeyValuePair = new HashMap<>();
		parseFile(configFilePath);
	}

	private void parseFile(File configFilePath) {
		try (Scanner in = new Scanner(configFilePath);) {
			String prefix = "";
			while (in.hasNext()) {
				String lineText = in.nextLine();
				if (lineText.isBlank()) {
					prefix = "";
					continue;
				} else if (!lineText.contains("=")) {
					prefix = lineText.substring(1, lineText.length() - 1) + ".";
				} else {
					String[] keyValuePair = lineText.split("=");
					if (configKeyValuePair.containsKey(prefix + keyValuePair[0])) {
						continue;
					}
					configKeyValuePair.put(
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
		return configKeyValuePair.get(key);
	}
}
