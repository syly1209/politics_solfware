package OJ1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choose extends examFrame implements ActionListener{
    public String user;
    public JPanel panel1,panel2,panel3;
    public JLabel title;
    public JButton button1,button2,button3,button4,button5;
    boolean bool=true;
    public Choose(String user,boolean bool){
        super(840,620);
        this.user=user;
        this.bool=bool;

        panel1=new JPanel();
        title=new JLabel();
        title.setText("请选择练习科目");
        title.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(title,BorderLayout.NORTH);
        panel1.add(new JLabel(" "),BorderLayout.SOUTH);

        button1=new JButton("思修");
        button2=new JButton("纲要");
        button3=new JButton("马原");
        button4=new JButton("毛概");
        button5=new JButton("随机练习");

        panel2=new JPanel(new GridLayout(3, 3));

        panel2.add(button1);
        panel2.add(new JLabel(""));
        panel2.add(button2);
        panel2.add(new JLabel(""));
        panel2.add(button5);
        panel2.add(new JLabel(""));
        panel2.add(button3);
        panel2.add(new JLabel(""));
        panel2.add(button4);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);

        panel3=new JPanel();
        panel3.add(new JLabel(" "));

        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.CENTER);
        this.add(panel3,BorderLayout.SOUTH);
        this.add(new JLabel("    "),BorderLayout.EAST);
        this.add(new JLabel("    "),BorderLayout.WEST);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1) {
            this.setVisible(false);
            if(bool){
                new Test3(user,"sx",60);
            }
            else{
                new Test3(user,"sx",5);
            }
        }
        if (e.getSource()==button2) {
            this.setVisible(false);
            if(bool){
                new Test3(user,"gy",60);
            }
            else{
                new Test3(user,"gy",5);
            }
        }
        if (e.getSource()==button3) {
            this.setVisible(false);
            if(bool){
                new Test3(user,"my",60);
            }
            else{
                new Test3(user,"my",5);
            }
        }
        if (e.getSource()==button4) {
            this.setVisible(false);
            if(bool){
                new Test3(user,"mg",60);
            }
            else{
                new Test3(user,"mg",5);
            }
        }
        if (e.getSource()==button5) {
            this.setVisible(false);
            if(bool){
                new Test3(user,"sj",20);
            }
            else{
                new Test3(user,"sj",5);
            }
        }
    }
}
