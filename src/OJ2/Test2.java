package OJ2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import OJ1.*;

@SuppressWarnings("serial")

public class Test2 extends JFrame implements ActionListener {
    private JButton pattern1,pattern2;
    private JPanel panel;
    public Test2() {
        this.setTitle("hhhh");
        this.setSize(440,320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pattern1 = new JButton("练习模式");
        pattern2 = new JButton("限时训练");
        panel = new JPanel();

        pattern1.addActionListener(this);
        pattern2.addActionListener(this);

        panel.add(pattern1);
        panel.add(pattern2);

        this.add(panel,BorderLayout.SOUTH);
        this.setVisible(true);

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Test2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==pattern1)	{
            new Test3("sx");
            this.setVisible(false);
        }
        if(e.getSource()==pattern2) {
            System.exit(0);
        }
    }

}
