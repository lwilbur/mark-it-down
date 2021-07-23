import javax.swing.*;

/**
 * Contains the two panes, handles Swing window creation and presentation of panes
 */
public class GUI {
    RawPane rawPane;            // Holds raw text
    PreviewPane previewPane;    // Holds preview text
    String rawString;          // Holds full raw string

    public GUI() {
        // createWindow();
        initializeRawPane();      // save to variable above
        initializePreviewPane();  // save to variable above
        showGUI();                // present GUI to user
    }

    private RawPane initializeRawPane() {          // plaintext, writable, JTextArea
        rawPane = null;
        return rawPane;
    }

    private PreviewPane initializePreviewPane() {  // rendered HTML, only readable, JEditorPane
        previewPane = null;
        return previewPane;
    }

    private void updatePreview() {
        previewPane.update(rawString);

        return;
    }

    private void showGUI() {
        //TODO
    }

    public void setRawString(String rawString) {
        this.rawString = rawString;
        updatePreview();
    }
}

