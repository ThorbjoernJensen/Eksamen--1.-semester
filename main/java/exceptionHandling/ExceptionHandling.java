package exceptionHandling;

import java.io.*;
import java.util.Date;

public class ExceptionHandling extends Exception {

    public ExceptionHandling(Object e) throws Exception {
        super((Exception)e);
        logfile((Exception)e);
    }

    public static void logfile(Exception e) throws Exception {
        File file = new File("Errorlog.txt");
        Date date = new Date();

        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));

        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(date.toString() + " : " + e.getMessage() + "\n" + errors.toString());
        fileWriter.close();
    }
}