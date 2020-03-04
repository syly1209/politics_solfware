package OJ1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")

public class Test2 extends examFrame implements ActionListener {
    private JButton pattern1,pattern2;
    private JPanel panel;
    public Test2() {
        super();

        //TextArea a=new TextArea("hhhh");

        JLabel title;
        title = new JLabel("             请选择模式");
        title.setFont(new Font("宋体", Font.PLAIN, 40));
        pattern1 = new JButton("练习模式");
        pattern2 = new JButton("限时训练");
        panel = new JPanel();

        pattern1.addActionListener(this);
        pattern2.addActionListener(this);

        panel.add(pattern1);
        panel.add(pattern2);

        this.add(panel,BorderLayout.SOUTH);
        this.add(title,BorderLayout.CENTER);
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
            System.exit(0);

        }
        if(e.getSource()==pattern2) {
            new Test3("sx");
            this.setVisible(false);
        }
    }

}
