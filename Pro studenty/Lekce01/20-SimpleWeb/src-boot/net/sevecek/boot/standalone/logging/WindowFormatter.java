package net.sevecek.boot.standalone.logging;

import java.io.*;
import java.util.logging.*;

public class WindowFormatter extends Formatter {

    private String format = "%s%s%n";
    private String errorFormat =
            "%n%3$s: %4$s%n[Logger: %2$s, Level: %3$s]%n[Class: %1$s]%5$s%n";

    @Override
    public String format(LogRecord record) {
        String source;
        if (record.getSourceClassName() != null) {
            source = record.getSourceClassName();
            if (record.getSourceMethodName() != null) {
                source += "::" + record.getSourceMethodName() + "()";
            }
        } else {
            source = record.getLoggerName();
        }
        String message = formatMessage(record);
        String throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = sw.toString();
        }
        if (record.getLevel().equals(Level.INFO)
                || record.getLevel().equals(Level.FINE)
                || record.getLevel().equals(Level.FINER)
                || record.getLevel().equals(Level.FINEST)
                || record.getLevel().equals(Level.CONFIG)) {
            return String.format(format,
                    message,
                    throwable);
        }
        return String.format(errorFormat,
                source,
                record.getLoggerName(),
                record.getLevel().getName(),
                message,
                throwable);
    }
}
