import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Contains the two panes, handles Swing window creation and presentation of
 * panes
 */
public class GUI extends JFrame implements KeyListener {
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
        setTitle("Mark It Down");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Quit when GUI closes
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screenSize);                   // Fit to screen
        setLayout(new GridLayout(1, 2));
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
    public void keyReleased(KeyEvent e) { 
        updatePreview(rawPane.getText());
    }
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) { }
}

