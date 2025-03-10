import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickButton extends JPanel {
    private JLabel label;
    private JLabel countLabel;
    private ImageIcon originalIcon;
    private int originalWidth = 171;
    private int originalHeight = 151;
    private int width = originalWidth;
    private int height = originalHeight;
    private int count = 0;

    ClickButton() {
        originalIcon = new ImageIcon("project/images/kfc_button.png");
        updateImage();

        countLabel = new JLabel("  Clicks: 0");
        countLabel.setFont(new Font("Georgia", Font.BOLD, 28));
        countLabel.setHorizontalAlignment(SwingConstants.CENTER); // По умолчанию текст в JLabel выравнивается по левому краю, но setHorizontalAlignment(SwingConstants.CENTER) изменяет его на центр.
        countLabel.setForeground(Color.WHITE); // TODO границы вокруг текста
        // TODO сделать словарь для чисел больше 1000 - 1к и тд
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                count++;
                countLabel.setText("  Clicks: " + count);
                //System.out.println("counts: " + count);

                width *= 1.1;
                height *= 1.1;
                updateImage();

                Timer timer = new Timer(100, event -> {
                    width = originalWidth;
                    height = originalHeight;
                    updateImage();
                });
                timer.setRepeats(false); // 1 повторение (небесконечный timer)
                timer.start();
            }
        });

        setLayout(new BorderLayout()); // смешной Layout
        add(label, BorderLayout.CENTER); // помещаем на середину панели
        add(countLabel, BorderLayout.SOUTH); // помещаем снизу на панели
    }

    private void updateImage() {
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        if (label == null) {
            label = new JLabel(resizedIcon); // Если label еще не создана, создаем
        } else {
            label.setIcon(resizedIcon);
        }
        revalidate(); // пересчитываем размер кнопки
        repaint();
    }
}
