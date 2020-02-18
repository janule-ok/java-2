package net.sevecek.boot.standalone;

import java.awt.*;
import java.awt.Container;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.List;
import java.util.logging.*;
import java.util.logging.Logger;
import javax.imageio.*;
import javax.servlet.*;
import javax.swing.*;
import javax.swing.border.*;
import org.apache.catalina.*;
import org.apache.catalina.core.*;
import org.apache.catalina.startup.*;
import org.apache.catalina.webresources.*;
import org.apache.tomcat.util.scan.*;
import net.sevecek.boot.standalone.logging.*;

public class TomcatApplication {

    private static Logger logger;
    private File projectFolderLocation;
    private File webServerBaseDirLocation;
    private File webInfClassesLocation;
    private File webInfLibLocation;
    private File webLocation;

    public static void main(String[] args) {
//        System.setProperty("java.util.logging.manager", "org.apache.juli.ClassLoaderLogManager");
//        System.setProperty("java.util.logging.config.file", "/logging.properties");
        String loggingConfigLocation = "/logging.properties";
        try {
            LogManager logManager = LogManager.getLogManager();
            logManager.readConfiguration(TomcatApplication.class.getResourceAsStream(loggingConfigLocation));
            logger = Logger.getLogger(TomcatApplication.class.getName());
        } catch (IOException e) {
            throw new RuntimeException(MessageFormat.format("Unable to read LogManager configuration from {0}: {1}", loggingConfigLocation, e.getMessage()), e);
        }

        TomcatApplication instance = new TomcatApplication();
        instance.start();
    }

    //-------------------------------------------------------------------------

    private Tomcat webServer;
    private JFrame mainWindow;
    private JScrollPane scrlTxtLoggingOutput;
    private JTextArea txtLoggingOutput;
    private JPanel pnlTop;
    private JLabel labServerStateTitle;
    private JLabel labServerStateValue;
    private JLabel labTomcatLogo;
    private TomcatWindowLoggingHandler.LoggingListener loggingListener;
    private String webHost;
    private int webPort;
    private JTextField txtAddress;
    private Font largerLabelFont;
    private JLabel labAddress;
    private Font largerTextFieldFont;
    private Color txtAddressBackground;
    private boolean closeRequested;

    public void start() {
        logger.info("Starting");
        try {
            projectFolderLocation = new File(".").getCanonicalFile();
            webServerBaseDirLocation = new File(projectFolderLocation, "build").getCanonicalFile();
            webInfClassesLocation = new File(projectFolderLocation, "build/classes").getCanonicalFile();
            webInfLibLocation = new File(projectFolderLocation, "build/lib").getCanonicalFile();
            webLocation = new File(projectFolderLocation, "src/main/resources/static").getCanonicalFile();
            webHost = "localhost";
            webPort = detectSparePort();
        } catch (IOException ex) {
            throw new RuntimeException("Unable to resolve project sub-folder", ex);
        }

        prepareWebServerManagementWindow();
        registerWindowLoggingListener();
        mainWindow.setVisible(true);

        logger.info("Starting project: " + projectFolderLocation);

        managedStartServer();
    }

    //-------------------------------------------------------------------------

    private void prepareWebServerManagementWindow() {
        mainWindow = new JFrame("Tomcat Web Server");
        mainWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                onWebServerManagementWindowClosing(evt);
            }
        });

        scrlTxtLoggingOutput = new JScrollPane();
        txtLoggingOutput = new JTextArea();
        pnlTop = new JPanel(new BorderLayout(5, 5));
        labServerStateTitle = new JLabel();
        labServerStateValue = new JLabel();
        txtAddress = new JTextField();
        labTomcatLogo = new JLabel();

        //======== this ========
        try {
            List<Image> icons = Arrays.asList(
                    ImageIO.read(getClass().getResource("tomcat-16.png")),
                    ImageIO.read(getClass().getResource("tomcat-20.png")),
                    ImageIO.read(getClass().getResource("tomcat-24.png")),
                    ImageIO.read(getClass().getResource("tomcat-32.png")),
                    ImageIO.read(getClass().getResource("tomcat-40.png")),
                    ImageIO.read(getClass().getResource("tomcat-64.png")),
                    ImageIO.read(getClass().getResource("tomcat-128.png")),
                    ImageIO.read(getClass().getResource("tomcat-256.png")),
                    ImageIO.read(getClass().getResource("tomcat-260.png"))
            );
            mainWindow.setIconImages(icons);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Container contentPane = mainWindow.getContentPane();
        contentPane.setLayout(new BorderLayout(5, 5));

        //======== scrlTxtLoggingOutput ========
        {
            scrlTxtLoggingOutput.setBorder(new EmptyBorder(5, 5, 5, 5));
            scrlTxtLoggingOutput.setViewportBorder(new BevelBorder(BevelBorder.LOWERED));

            //---- txtLoggingOutput ----
            txtLoggingOutput.setBackground(labServerStateTitle.getBackground());
            txtLoggingOutput.setBorder(null);
            txtLoggingOutput.setFont(txtLoggingOutput.getFont().deriveFont(txtLoggingOutput.getFont().getSize() + 10f));
            txtLoggingOutput.setText("Server status");
            txtLoggingOutput.setColumns(90);
            txtLoggingOutput.setRows(18);
            scrlTxtLoggingOutput.setViewportView(txtLoggingOutput);
        }
        contentPane.add(scrlTxtLoggingOutput, BorderLayout.CENTER);

        //======== pnlTop ========
        {
            pnlTop.setBorder(new EmptyBorder(5, 10, 5, 5));

            pnlTop.setLayout(new GridBagLayout());
            ((GridBagLayout) pnlTop.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
            ((GridBagLayout) pnlTop.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout) pnlTop.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 0};
            ((GridBagLayout) pnlTop.getLayout()).rowWeights = new double[] {1.0, 0.0, 0};

            largerLabelFont = labServerStateValue.getFont().deriveFont(labServerStateValue.getFont().getSize() + 5f);
            largerTextFieldFont = txtAddress.getFont().deriveFont(txtAddress.getFont().getSize() + 5f);
            txtAddress.setFont(largerTextFieldFont);
            txtAddressBackground = txtAddress.getBackground();
            txtAddress.setEnabled(false);
            txtAddress.setEditable(false);
            txtAddress.setBackground(txtAddressBackground);

            //---- labServerStateTitle ----
            labServerStateTitle.setText("Server State:");
            labServerStateTitle.setFont(largerLabelFont);
            labServerStateTitle.setHorizontalAlignment(SwingConstants.TRAILING);
            pnlTop.add(labServerStateTitle, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- labServerStateValue ----
            labServerStateValue.setText("INITIALIZING");
            labServerStateValue.setFont(largerLabelFont);
            pnlTop.add(labServerStateValue, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- labTomcatLogo ----
            labTomcatLogo.setIcon(new ImageIcon(getClass().getResource("/net/sevecek/boot/standalone/tomcat-logo.png")));
            pnlTop.add(labTomcatLogo, new GridBagConstraints(2, 0, 1, 2, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

            //---- labAdress ----
            labAddress = new JLabel();
            labAddress.setText("Address for browser:");
            labAddress.setFont(largerLabelFont);
            labAddress.setHorizontalAlignment(SwingConstants.TRAILING);
            pnlTop.add(labAddress, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
            pnlTop.add(txtAddress, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

        }
        contentPane.add(pnlTop, BorderLayout.NORTH);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int maxWidth = (int) (gd.getDisplayMode().getWidth() * 0.8);
        int maxHeight = (int) (gd.getDisplayMode().getHeight() * 0.8);

        mainWindow.pack();
        if (mainWindow.getWidth() < maxWidth) {
            maxWidth = mainWindow.getWidth();
        }
        if (mainWindow.getHeight() < maxHeight) {
            maxHeight = mainWindow.getHeight();
        }
        mainWindow.setSize(maxWidth, maxHeight);
        txtLoggingOutput.setColumns(1);
        txtLoggingOutput.setRows(1);
        mainWindow.setLocationRelativeTo(null);
    }

    private void onWebServerManagementWindowClosing(WindowEvent evt) {
        if (!closeRequested) {
            closeRequested = true;
            labServerStateValue.setText("STOPPING");
            new Thread(this::stopServer).start();
        }
    }

    public void stopServer() {
        try {
            if (webServer != null) {
                webServer.stop();
            }
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        } finally {
            SwingUtilities.invokeLater(mainWindow::dispose);
        }
    }

    //-------------------------------------------------------------------------

    private void registerWindowLoggingListener() {
        TomcatWindowLoggingHandler handler = TomcatWindowLoggingHandler.getInstance();
        if (handler != null) {
            synchronized (handler) {
                txtLoggingOutput.setText(handler.getBuffer());
                loggingListener = new WindowLoggingListener();
                handler.addLoggingListener(loggingListener);
            }
        }
    }

    private class WindowLoggingListener implements TomcatWindowLoggingHandler.LoggingListener {

        @Override
        public void onLog(String text) {
            SwingUtilities.invokeLater(() -> {
                onLogMessageUI(text);
            });
        }
    }

    private void onLogMessageUI(String text) {
        String text1 = txtLoggingOutput.getText();
        text1 = text1 + text;
        txtLoggingOutput.setText(text1);
    }

    //-------------------------------------------------------------------------

    public void managedStartServer() {
        try {
            logger.info("Tomcat is initializing");
            startServer();
            logger.info("Tomcat is running");
            SwingUtilities.invokeLater(this::notifyServerStarted);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Server failed to start", e);
            SwingUtilities.invokeLater(this::notifyServerStartFailed);
        }
//      openWebBrowser(webHost, webPort);
    }

    private void notifyServerStarted() {
        txtAddress.setText(constructUrl().toString());
        txtAddress.setEnabled(true);
        txtAddress.setBackground(txtAddressBackground);
        labServerStateValue.setText("RUNNING");
    }

    private void notifyServerStartFailed() {
        labServerStateValue.setText("FAILED TO START");
    }

    private void startServer() {
        try {
//          System.setProperty("tomcat.util.scan.StandardJarScanFilter.jarsToSkip", "*");

            webServer = new Tomcat();
            webServer.setBaseDir(webServerBaseDirLocation.getAbsolutePath());
            webServer.setPort(webPort);

            StandardContext ctx = (StandardContext) webServer.addWebapp("", webLocation.getAbsolutePath());

            WebResourceRoot mappedFolders = new StandardRoot(ctx);
            mappedFolders.addPreResources(
                    new DirResourceSet(mappedFolders, "/WEB-INF/classes", webInfClassesLocation.getAbsolutePath(), "/"));

            mappedFolders.addJarResources(
                    new DirResourceSet(mappedFolders, "/WEB-INF/lib", webInfLibLocation.getAbsolutePath(), "/"));
            ctx.setResources(mappedFolders);

            ((StandardJarScanner) ctx.getJarScanner()).setScanClassPath(false);

            webServer.start();
        } catch (ServletException | LifecycleException ex) {
            throw new RuntimeException("Failed to start Tomcat", ex);
        }
    }

    //-------------------------------------------------------------------------

    private int detectSparePort() {
        List<Integer> candidates = Arrays.asList(80, 8080, 8000, 8888);

        int webPort = -1;
        for (Integer candidatePort : candidates) {
            try {
                ServerSocket serverSocket = new ServerSocket(candidatePort);
                serverSocket.close();
                webPort = candidatePort;
                break;
            } catch (IOException e) {
                // continue
            }
        }

        if (webPort == -1) {
            logger.log(Level.SEVERE, "No spare TCP/IP port available for HTTP server");
        }

        return webPort;
    }

    private void openWebBrowser() {
        URI completeUrl = constructUrl();
        try {
            if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
                Runtime.getRuntime().exec("cmd /c start " + completeUrl);
            } else {
                logger.info("Opening browser using Desktop");
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(completeUrl);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to open web browser to " + completeUrl, e);
        }
    }

    private URI constructUrl() {
        URI completeUrl = null;
        try {
            if (webPort == 80) {
                completeUrl = new URI(MessageFormat.format("http://{0}/", webHost));
            } else {
                completeUrl = new URI(MessageFormat.format("http://{0}:{1,number,0}/", webHost, webPort));
            }
        } catch (URISyntaxException e) {
            // This never happens
            throw new AssertionError(e);
        }
        return completeUrl;
    }

}
