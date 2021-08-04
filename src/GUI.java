import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Contains the two panes, handles Swing window creation and presentation of
 * panes
 */
public class GUI extends JFrame implements KeyListener, ActionListener {
    RawPane rawPane;            // Holds raw text
    PreviewPane previewPane;    // Holds preview text

    public GUI() {
        setupGUI();
        addComponents();
        rawPane.addKeyListener(this);
        pack();
        setVisible(true);         // show completed GUI
    }

    /**
     * Set window traits
     */
    private void setupGUI() {
        // Determining window traits
        setTitle("Mark It Down");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Quit when GUI closes
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screenSize);                   // Fit to screen
        setLayout(new GridLayout(1, 2));

        // Move menu bar onto top bar, if on a mac
        if (System.getProperty("os.name").equals("Mac OS X"))
            System.setProperty("apple.laf.useScreenMenuBar", "true");

        // Create menu bar contents
        JMenuBar bar   = new JMenuBar();
        JMenu file     = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        save.addActionListener(this);
        load.addActionListener(this);
        file.add(save);
        file.add(load);
        bar.add(file);
        setJMenuBar(bar);
    }

    /**
     * Add the raw and preview panes, as well as needed buttons, to the window
     */
    private void addComponents() {
        rawPane = new RawPane(this);
        previewPane = new PreviewPane(this);
        getContentPane().add(new JScrollPane(rawPane));
        getContentPane().add(new JScrollPane(previewPane));
    }

    /**
     * Trigger an update of the previewPane, sending it the latest contents of
     * the rawPane
     * @param rawString the raw, unrendered markdown string from the rawPane,
     */
    private void updatePreview(String rawString) {
        previewPane.update(rawString);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Markdown Files", "md");
        fc.setFileFilter(filter);

        String action = ae.getActionCommand();


        switch (action) {
            case "Save":
                if (fc.showDialog(this, "Save") == JFileChooser.APPROVE_OPTION) {
                    try {
                        String fileName = fc.getSelectedFile().getName();
                        String fileDir  = fc.getSelectedFile().getParent();

                        // Strip out any extensions added by user
                        if (fileName.contains("."))
                            fileName = fileName.substring(0, fileName.indexOf("."));

                        fc.getSelectedFile().getName();
                        // Add markdown extension
                        if (!fileName.endsWith(".md"))
                            fileName += ".md";
                        IO.writeMarkdownFile(fileDir + "/" + fileName, rawPane.getText());
                    }
                    catch (NullPointerException npe) {
                        String msg = "File selection error.\nPlease select an " +
                                     "extant file or choose a new file name.";
                        JOptionPane.showMessageDialog(this,
                                msg,
                                "Save Error",
                                JOptionPane.ERROR_MESSAGE);

                    }
                }
                break;
            case "Load":
                if (fc.showDialog(this, "Load") == JFileChooser.APPROVE_OPTION) {
                    try {
                        String filePath = fc.getSelectedFile().getPath();
                        if (filePath.endsWith(".md"))
                            rawPane.setText(IO.readMarkdownFile(filePath));
                        else
                            throw new Exception("Invalid Vile");
                    }
                    catch (Exception e) {
                        String msg = "File selection error.\nPlease select an" +
                                " extant .md file.";
                        JOptionPane.showMessageDialog(this,
                                msg,
                                "Load Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }


                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) { 
        updatePreview(rawPane.getText());
    }
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) { }
}

