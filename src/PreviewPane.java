import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

public class PreviewPane extends JEditorPane {
    GUI gui;
    String htmlString = ""; 
    HTMLEditorKit kit = new HTMLEditorKit();

    public PreviewPane(GUI gui) {
        this.gui = gui;
        setEditable(false);
        setEditorKit(kit);
        applyMarkdownStyles();
        Document doc = kit.createDefaultDocument();
        setDocument(doc);
        setText(htmlString);
    }


    public void update(String rawString) {
        htmlString = createHTMLString(rawString);
        updatePane();
    }

    private String createHTMLString(String rawString) {
        return Converter.convertString(rawString);
    }

    private void updatePane() {
        setText(htmlString);
    }

    private void applyMarkdownStyles() {
        StyleSheet styleSheet = kit.getStyleSheet();

        String styles = 
            "h1, h2, h3, h4, h5, h6 { margin: 0 0 20px; font-weight 800; } "
        +   "h1 { font-size: 32px; } "
        +   "h2 { font-size: 20px; } "
        +   "h3 { font-size: 18px; } "
        +   "h4 { font-size: 16px; } "
        +   "h5 { font-size: 12px; } "
        +   "h6 { color: #777; font-size: 12px; } "
        +   "hr { margin: 0 0 100px; } "
        +   "p { margin: 0 0 20px; } "
        +   "blockquote { margin: 0 0 20px; padding: 0 0 0 10px; border-left: 4px solid #ccc; color: #666; } "
        +   "ul { margin: 0 0 10px 20px; } "
        +   "li { margin: 0 0 20px 0; } "
        +   "code { background-color: #eee; } "
        +   "html { margin: 5px; } "
        +   "body { font-size: 12px; font-family: Helvetica Neue; } ";
        
        styleSheet.addRule(styles);
    }
}
