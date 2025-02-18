import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MyButtonListener implements ActionListener { // Добавляем класс для обработки нажатия кнопки
    private JButton button;
    private int counter = 0;

    public MyButtonListener(JButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.button.setText("Clicked " + ++counter); // АХХАХАХАХХАХАХАХАХХА (меняем счётчик)
    }
}