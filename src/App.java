import javax.swing.*;

public class App extends JFrame {

    App() {
        super("First java app(name)"); // Обращение к методу родительского класса
        setDefaultCloseOperation(EXIT_ON_CLOSE); // закрытие по умолчанию (тут типо define ...)
        setSize(400, 300);
        setLayout(null);

        JLabel label = new JLabel("☻ Hello my friend!");
        label.setBounds(20, 20, 40, 30);
        this.add(label);
        setVisible(true); // Вывод окна
    }

    public static void main(String[] args) {

        new App();

    }
}