import javax.swing.*;

public class RawPane {
    JTextArea textArea;
    GUI gui;

    public RawPane(GUI gui) {
        this.gui = gui;

        textArea = new JTextArea();
    }

    public void listenerFunction() {   // Will be replaced by swing-based listener function from textArea
        String getStringFromTextArea = null;
        gui.setRawString(getStringFromTextArea);
    }

    // TODO: add listener for change in Raw Pane contents
}
