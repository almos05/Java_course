import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

public class FallingWings extends JPanel {
    private List<Wing> wings = new ArrayList<>();
    private BufferedImage wingTexture;
    private int frame = 0; // Индекс текущего кадра анимации
    private Raster[] frames; // Массив текстур (для анимации)

    public FallingWings() {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File("project/images/m_kfc_wing.png"));
            int frameCount = 4; // Количество кадров в спрайте
            int frameWidth = spriteSheet.getWidth() / frameCount;
            int frameHeight = spriteSheet.getHeight();

            frames = new Raster[frameCount];
            for (int i = 0; i < frameCount; i++) {
                frames[i] = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight).getData();
            }

            wingTexture = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
            wingTexture.setData(frames[0]); // Начальный кадр

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Таймер для смены анимации
        Timer animationTimer = new Timer(500, e -> { // delay TODO
            frame = (frame + 1) % frames.length;
            wingTexture.setData(frames[frame]);
            repaint();
        });
        animationTimer.start();

        // Таймер для обновления положения крылышек
        Timer fallTimer = new Timer(10, e -> {
            Iterator<Wing> iterator = wings.iterator();
            while (iterator.hasNext()) {
                Wing w = iterator.next();
                w.y += w.speed;
                w.angle += 0.02; // Поворот при падении
                if (w.y > getHeight()) {
                    iterator.remove(); // Удаляем крылышки, если они упали за границы экрана
                }
            }
            repaint();
        });
        fallTimer.start();

        // Добавляем крылышки при движении мыши
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                wings.add(new Wing(e.getX(), e.getY()));
            }
        });

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Wing w : wings) {
            AffineTransform at = new AffineTransform();
            at.translate(w.x, w.y);
            at.rotate(w.angle, wingTexture.getWidth() / 2.0, wingTexture.getHeight() / 2.0);
            // Корректируем размер изображения
            g2d.drawImage(wingTexture, at, null);
        }
    }

    private static class Wing {
        int x, y;
        double angle = 0;
        int speed = 3 + (int) (Math.random() * 3); // Разная скорость падения

        Wing(int x, int y) {
            this.x = x - 15; // Небольшой сдвиг, чтобы выглядело естественнее
            this.y = y;
        }
    }
}
