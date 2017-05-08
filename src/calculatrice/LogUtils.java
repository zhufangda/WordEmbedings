package calculatrice;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** Cette classe me petmet de simplifier la utilisation de logger */
public class LogUtils {
    public static final String LOG_PATH = "log" + File.separator;

    public static Logger getLogger(String logName,
                                   Level level) {
        try {
            Logger logger = Logger.getLogger(logName);
            logger.setUseParentHandlers(false);
            logger.setLevel(level);
            Handler consoleHander = new ConsoleHandler();
            consoleHander.setLevel(Level.OFF);
            FileHandler fileHandler;
            fileHandler = new FileHandler(LOG_PATH + logName + ".xml");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(consoleHander);
            logger.addHandler(fileHandler);
            return logger;
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
