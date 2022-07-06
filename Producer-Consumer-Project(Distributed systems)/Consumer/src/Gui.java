import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui extends JFrame {

    String msg ;

    public Gui(JTextArea j)
    {
        setTitle("Consumer");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new JScrollPane(j), BorderLayout.CENTER);
        setVisible(true);
    }
}