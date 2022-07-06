import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JFrame {

    public JTextField j = new JTextField();
    public DataOutputStream out ;

    public Gui(DataOutputStream dataOutputStream) {
        out= dataOutputStream;
        setTitle("producer");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(j, BorderLayout.CENTER);
        j.setHorizontalAlignment(JTextField.RIGHT);

        setLayout(new BorderLayout());
        add(p, BorderLayout.NORTH);
        j.addActionListener(new TextFieldListener());

    }

    private class TextFieldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
            	String ProducerMessage="";
            	ProducerMessage= j.getText().trim();
                out.writeUTF(ProducerMessage );
                j.setText("");
                out.flush();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

}
