import javax.swing.*;
import java.awt.*;

public class RawPane extends JEditorPane {
    GUI gui;

    public RawPane(GUI gui) {
        this.gui = gui;
        setBackground(Color.PINK);
        setSelectedTextColor(Color.GREEN);
    }
}
