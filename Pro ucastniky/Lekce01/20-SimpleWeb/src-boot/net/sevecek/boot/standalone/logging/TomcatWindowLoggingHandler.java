package net.sevecek.boot.standalone.logging;

import java.io.*;
import java.lang.ref.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class TomcatWindowLoggingHandler extends StreamHandler {

    private static TomcatWindowLoggingHandler instance;
    private List<WeakReference<LoggingListener>> listeners;
    private ByteArrayOutputStream buffer;

    public TomcatWindowLoggingHandler() {
        try {
            setEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // This never happens. UTF-8 is always supported
            throw new RuntimeException(e);
        }
        buffer = new ByteArrayOutputStream(8192);
        setOutputStream(buffer);
        listeners = new CopyOnWriteArrayList<>();
        instance = this;
    }

    public static TomcatWindowLoggingHandler getInstance() {
        return instance;
    }

    public synchronized String getBuffer() {
        try {
            String result = buffer.toString(getEncoding());
            buffer.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            // This never happens. UTF-8 is always supported
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void publish(LogRecord record) {
        super.publish(record);
        flush();

        String text = getBuffer();
        Iterator<WeakReference<LoggingListener>> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<LoggingListener> listenerRef = iterator.next();
            LoggingListener listener = listenerRef.get();
            if (listener == null) {
                iterator.remove();
            } else {
                listener.onLog(text);
            }
        }
    }

    public synchronized void addLoggingListener(LoggingListener lst) {
        listeners.add(new WeakReference<>(lst));
    }

    public synchronized void removeLoggingListener(LoggingListener lst) {
        Iterator<WeakReference<LoggingListener>> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<LoggingListener> listenerRef = iterator.next();
            LoggingListener listener = listenerRef.get();
            if (listener == null || listener.equals(lst)) {
                iterator.remove();
            }
        }
    }

    @Override
    public synchronized void flush() {
        super.flush();
    }

    @Override
    public synchronized void close() {
        super.flush();
        super.close();
    }

    //-------------------------------------------------------------------------

    public static interface LoggingListener {

        void onLog(String text);

    }
}
