import javax.swing.*;
import java.awt.*;

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

        JButton button = new JButton("Click");
        button.setBounds(20, 50, 200, 60);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE); // цвет текста
        add(button);

        setVisible(true); // Вывод окна
    }

    public static void main(String[] args) {

        new App();

    }
}