import javax.swing.*;

public class PreviewPane {
    JEditorPane editorPane;
    String htmlString;

    public PreviewPane() {
        editorPane = new JEditorPane();
    }

    public void update(String rawString) {
        htmlString = createHTMLString(rawString);
        updatePane();
    }

    private String createHTMLString(String rawString) {
        return Converter.convertString(rawString);
    }

    private void updatePane() {
        // Update the pane with the newly created htmlString
    }
}
