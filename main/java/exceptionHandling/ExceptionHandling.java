package exceptionHandling;

import java.io.*;
import java.util.Date;

public class ExceptionHandling extends Exception {


    public ExceptionHandling(Exception e) throws Exception {
        super(e);
        logfile(e);
    }

    public static void logfile(Exception e) throws Exception {
        File file = new File("Errorlog.txt");
        Date date = new Date();
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(date.toString() + " : " + errors + "\n");
        fileWriter.close();
    }
}