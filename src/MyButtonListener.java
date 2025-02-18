import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;

public class MyButtonListener implements ActionListener { // Добавляем класс для обработки нажатия кнопки
    //private JButton button;
    private JComponent component; // меняем на JComponent (родительский класс)
    private int counter = 0;
    private int dx;

    public MyButtonListener(JComponent component, int dx) {
        this.component = component;
        this.dx = dx;
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     this.button.setText("Clicked " + ++counter); // АХХАХАХАХХАХАХАХАХХА (меняем счётчик)
    // }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.component.setLocation(component.getX() + this.dx, component.getY()); // АХХАХАХАХХАХАХАХАХХА (меняем счётчик)
    }
}