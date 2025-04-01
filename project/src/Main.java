import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// TODO сделать падающие крылья кфс (вокруг курсора)

public class Main extends JFrame {
    private JTextField inputField;
    private JLabel displayTextLabel;

    Main() {
        super("KFC clicker");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // закрытие по умолчанию (тут типо define ...)
        setSize(800, 539);
        setLayout(null);
        getContentPane().setBackground(new Color(165, 0, 0));

        inputField = new JTextField();
        inputField.setBounds(150, 100, 500, 40);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        add(inputField);

        displayTextLabel = new JLabel();
        displayTextLabel.setBounds(100, 20, 500, 40);
        displayTextLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        displayTextLabel.setForeground(Color.WHITE);
        add(displayTextLabel);

        ClickButton mainClickButton = new ClickButton();
        mainClickButton.setBounds(150, 100, 200, 200);
        mainClickButton.setOpaque(false);
        mainClickButton.setVisible(false); // изначально невидимый кликер
        add(mainClickButton);

        FallingWings fallingWings = new FallingWings();
        fallingWings.setBounds(0, 0, 800, 539);
        add(fallingWings);

        MoveBackground backgroundPanel = new MoveBackground();
        backgroundPanel.setBounds(0, 320, 1000, 180); // width по сути будет менять размер панели, а не изображения (отступ между картинками увеличиться, при увеличении width)
        backgroundPanel.setOpaque(false);
        add(backgroundPanel);

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    displayTextLabel.setText(inputField.getText());
                    inputField.setVisible(false);
                    mainClickButton.setVisible(true);
                }
            }
        });

        //setBackground(new Color(165, 0, 0));
        setVisible(true);
    }

    public static void main(String[] args) {

        new Main();

    }
}