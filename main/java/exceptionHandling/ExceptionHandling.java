package exceptionHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class ExceptionHandling extends Exception {


    public ExceptionHandling(String message) {
        super(message);
    }

    public static void logfile(Exception e) throws IOException {
        File file = new File("tracelog.txt");
        Date date = new Date();
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(date.toString() + " : " + e.toString()+"\n");
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
