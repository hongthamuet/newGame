package Tower_Defense.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JLabel {
    public static final Color c1 = new Color(230, 138, 0);
    public static final Color c2 = new Color(128, 128, 128);
    public static final Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    public static final Font font = new Font("Dialog", Font.BOLD, 20);

    public  Button(int x, int y, int w, int h , String contentBtn) {
        super(contentBtn, JLabel.CENTER);
        this.setFont(font);
        initButton(x,y,w,h);
    }

    public Button(int x, int y, int w, int h, Icon icon) {
        super(icon);
        this.setBounds(x,y,w,h);
    }

    public Button(int x, int y, int w, int h, Icon icon, int horizontalAlignment) {
        super(icon, horizontalAlignment);
        initButton(x,y,w,h);
    }

    public void initButton(int x, int y, int w, int h) {
        this.setBorder(new LineBorder(c2, 2, true));
        this.setBounds(x,y,w,h);
        this.setForeground(c2);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setBorder(new LineBorder(c1, 3, true));
                setForeground(c1);
                setCursor(handCursor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBorder(new LineBorder(c2, 2, true));
                setForeground(c2);
                setCursor(null);
            }
        });
    }
}
