package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerUtil {

    public static void log(String message) {
        try {
            FileWriter fw = new FileWriter("logs/app.log", true);
            fw.write(LocalDateTime.now() + " : " + message + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing log - LoggerUtil.java:15");
        }
    }
}