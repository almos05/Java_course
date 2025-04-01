import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Sort extends JLabel implements Runnable {
    int N;
    int[] array;
    JButton genButton, bubbleSortButton, quickSortButton;
    JFrame frame;
    int J, P, Q; // J - текущий элемент, P и Q - границы для быстрой сортировки
    boolean isQuickSort = false;

    public Sort(int N) {
        this.N = N;
        array = new int[N];
        gen();

        genButton = new JButton("Generate");
        bubbleSortButton = new JButton("Bubble Sort");
        quickSortButton = new JButton("Quick Sort");

        genButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gen();
                J = -1;
                P = -1;
                Q = -1;
                repaint();
            }
        });

        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                J = -1;
                P = -1;
                Q = -1;
                isQuickSort = false;
                bubbleSort();
            }
        });

        quickSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                J = -1;
                P = -1;
                Q = -1;
                isQuickSort = true;
                quickSort();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(genButton);
        buttonPanel.add(bubbleSortButton);
        buttonPanel.add(quickSortButton);

        frame = new JFrame("Sort Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        setBounds(0, 0, 1000, 250);
        frame.setSize(1000, 400);
        frame.setVisible(true);
    }

    void gen() {
        Random rng = new Random();
        for (int i = 0; i < N; i++) {
            array[i] = rng.nextInt(200) + 50;
        }
    }

    void bubbleSort() {
        new Thread(this).start();
    }

    void quickSort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                quickSort(0, array.length - 1);
                J = -1;
                P = -1;
                Q = -1;
                repaint();
            }
        }).start();
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);

            P = low;
            Q = high;
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                // Своп элементов
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                // Визуализация
                J = j;
                P = i;
                Q = high;
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Своп с опорным элементом
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        int barWidth = getWidth() / N;
        for (int i = 0; i < N; i++) {
            if (i == J) {
                g.setColor(Color.red); // Текущий элемент
            } else if (isQuickSort && i >= P && i <= Q) {
                g.setColor(Color.green); // Текущий подмассив в быстрой сортировке
            } else if (isQuickSort && i == Q) {
                g.setColor(Color.orange); // Опорный элемент
            } else {
                g.setColor(Color.blue); // Обычный элемент
            }

            g.fillRect(i * barWidth, getHeight() - array[i], barWidth - 1, array[i]);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    J = j;
                    repaint();

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        J = -1;
        repaint();
    }

    public static void main(String[] args) {
        new Sort(100);
    }
}