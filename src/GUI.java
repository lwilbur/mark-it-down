import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

/**
 * Contains the two panes, handles Swing window creation and presentation of
 * panes
 */
public class GUI extends JFrame implements KeyListener, ActionListener {
    RawPane rawPane;            // Holds raw text
    PreviewPane previewPane;    // Holds preview text
    JTextArea test;             // TODO: delete this

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

        // Creating menu bar entries
        System.setProperty("apple.laf.useScreenMenuBar", "true"); // only works on macs?
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
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
        test = new JTextArea(); // TODO: remove test, add previewPane
        getContentPane().add(rawPane);
        getContentPane().add(test);
    }

    /**
     * Trigger an update of the previewPane, sending it the latest contents of
     * the rawPane
     * @param rawString the raw, unrendered markdown string from the rawPane,
     */
    private void updatePreview(String rawString) {
        //previewPane.setText(rawString);  // TODO: uncomment preview, remove test
        test.setText(rawString);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();

        String action = e.getActionCommand();


        switch (action) {
            case "Save":  // TODO: make only .txt selectable
                if (fc.showDialog(this, "Save") == JFileChooser.APPROVE_OPTION) {
                    IO.writeTextFile(fc.getSelectedFile().getPath(),
                                     test.getText()); // TODO: change to preview pane
                }
                break;
            case "Load":  // TODO: finish loading process
                System.out.println("Load " + action + "activated");
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

