import java.util.Map;
import java.io.File;

public class Main {

	public static void main(String[] args) {
		String mode = "production"; 	// default mode

		if (args.length > 0) {          // assign mode if supplied from the command line argument
			mode = args[0].toLowerCase();
		}

		ConfigParser config;
		File configFile;

		// create a new configParser object depending on mode variable
		if ("production".equals(mode)) {
			configFile = new File("src/config.txt");
			config = new ConfigParser(configFile.getAbsoluteFile());
		} else  {
			configFile = new File("src/config-" + mode + ".txt");
			// verify if config file for the mode exists in relative path
			if (!configFile.exists()) {
				System.err.println("File does not exist");
				return;
			}
			System.out.println(configFile.getAbsoluteFile());
			config = new ConfigParser(configFile.getAbsoluteFile());
		}

		// Print all the entries in the config file
		System.out.printf("%-25s VALUE%n-----------------------------------%n", "KEY");
		for (Map.Entry<String, String> entry : config.getConfigEntryMap().entrySet()) {
			System.out.printf("%-25s %s%n", entry.getKey(), entry.getValue());
		}
	}
}