import java.util.Map;
import java.io.File;

public class Main {

	public static void main(String[] args) {
		String mode = "production"; 	// default mode

		if (args.length > 0) {          // assign mode if supplied from the command line argument
			mode = args[0].toLowerCase();
		}

		File configFile;

		// construct a path depending on mode variable
		if ("production".equals(mode)) {
			configFile = constructPath("src/config.txt");
		} else  {
			configFile = constructPath("src/config-" + mode + ".txt");
			// verify if config file for the mode exists in path
			if (!configFile.exists()) {
				System.err.println("File is not in src folder or does not exist");
				return;
			}
		}

		ConfigParser config = new ConfigParser(configFile);  // create configParser object

		// Print all the entries in the config file
		System.out.printf("%-25s VALUE%n-----------------------------------%n", "KEY");
		for (Map.Entry<String, String> entry : config.getConfigEntryMap().entrySet()) {
			System.out.printf("%-25s %s%n", entry.getKey(), entry.getValue());
		}
	}

	// construct path to file from absolute path(System agnostic)
	public static File constructPath(String path) {
		File relFilePath = new File(path);
		String absFilePath = relFilePath.getAbsolutePath();
		path = absFilePath.substring(0, absFilePath.indexOf("src"))
				+ absFilePath.substring(absFilePath.lastIndexOf("src"));
		return new File(path);
	}
}