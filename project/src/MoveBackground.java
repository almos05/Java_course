import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveBackground extends JPanel {
    private Image banner;
    private int x = 0; // start pos
    private int bannerHeight = 175;

    public MoveBackground() {
        //setBackground(new Color(165, 0, 0));
        //setOpaque(false);
        ImageIcon originalIcon = new ImageIcon("project/images/kfc_background.jpg");
        banner = originalIcon.getImage().getScaledInstance(500, bannerHeight, Image.SCALE_SMOOTH);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x -= 1;

                if (x <= -getWidth()) {
                    x = 0;
                }

                repaint();
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(banner, x, getHeight() - bannerHeight, this);
        g.drawImage(banner, x + getWidth(), getHeight() - bannerHeight, this);
    }
}
