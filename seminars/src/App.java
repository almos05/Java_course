import java.awt.*;
import javax.swing.*;

public class App extends JFrame {

    App() {
        super("First java app(name)"); // Обращение к методу родительского класса
        setDefaultCloseOperation(EXIT_ON_CLOSE); // закрытие по умолчанию (тут типо define ...)
        setSize(400, 300);
        setLayout(null);

        JLabel label = new JLabel("Hello my friend!"); // Создаем метку, поле
        label.setBounds(20, 20, 200, 30);
        label.setFont(new Font("Papyrus", Font.ITALIC, 24));
        label.setForeground(Color.BLACK);
        label.setBackground(Color.YELLOW);
        label.setOpaque(true);
        this.add(label);

        JLabel imageClown = new JLabel(new ImageIcon("src/images/clown.png"));
        imageClown.setBounds(20, 110, 116, 90); // размер клоуна 116x90
        this.add(imageClown);

        JButton button = new JButton("Click");
        button.setBounds(20, 50, 200, 60);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE); // цвет текста
        add(button);

        //MyButtonListener listener = new MyButtonListener(button, 10);
        MyButtonListener listener = new MyButtonListener(imageClown, 52); // используем image, чтобы именно эта компонента уезжала
        button.addActionListener(listener);

        // JButton button2 = new JButton("Click"); // Вторая кнопка
        // button2.setBounds(20, 110, 200, 60);
        // button2.setBackground(Color.BLACK);
        // button2.setForeground(Color.WHITE); // цвет текста
        // add(button2);

        // MyButtonListener listener2 = new MyButtonListener(button2);
        // button2.addActionListener(listener2);
        // button2.addActionListener(listener);

        //button.addActionListener((ActionEvent e) -> {}); // lambda функция

        setVisible(true); // Вывод окна
    }

    public static void main(String[] args) {

        new App();

    }
}