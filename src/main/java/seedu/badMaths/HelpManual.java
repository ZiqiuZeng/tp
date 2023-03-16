package seedu.badMaths;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class HelpManual {

    protected static String filePath = "docs/HelpManual.txt";
    protected static String logFilePath = HelpManual.class.getName();
    protected static String content;
    private static final Logger logger = Logger.getLogger(logFilePath);

    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            if (!new File(logFilePath).exists()) {
                new File(logFilePath).createNewFile();
            }
            FileHandler logFile = new FileHandler(logFilePath, true);
            logFile.setLevel(Level.FINE);
            logger.addHandler(logFile);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * This method reads the content from docs/HelpManual.txt file.
     */
    public static void readHelpManual() {
        setUpLogger();
        try {
            logger.log(Level.INFO, "going to start processing");
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                StringBuilder contentBuilder = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                    contentBuilder.append(lineTxt).append("\n");
                }
                content = contentBuilder.toString();
                read.close();
                // Add an assertion to check if the contents of the file match the expected contents
                String expectedContent = new String(Files.readAllBytes(Paths.get(filePath)));
                String actualContent = contentBuilder.toString();
                assert actualContent.equals(expectedContent) : "Contents of file do not match expected contents.";
                // Log successful read to console and log file
                logger.log(Level.INFO, "Successfully read Help Manual file.");
            } else {
                System.out.println("Cannot find Help Manual. Please try again.");
                logger.log(Level.WARNING, "Cannot find Help Manual.");
            }
        } catch (Exception e) {
            System.out.println("Error while loading files. Please try again.");
            e.printStackTrace();
        }
    }
}
