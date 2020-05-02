package OJ1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

@SuppressWarnings("serial")

public class Test2 extends examFrame implements ActionListener {
    private JButton pattern1,pattern2;
    private JPanel panel,panel1,panel2;
    private String user;
    public Test2(String user,LinkDatabase link) {
        super(840,620);
        this.user=user;
        //TextArea a=new TextArea("hhhh");
        JLabel title,title2;
        title = new JLabel();
        title.setFont(new Font("宋体", Font.PLAIN, 30));
        title.setText("排行榜");
        title.setHorizontalAlignment(JLabel.CENTER);
        pattern1 = new JButton("快速练习");
        pattern2 = new JButton("限时训练");
        panel = new JPanel();
        panel1=new JPanel(new GridLayout(8, 1));
        panel2=new JPanel();
        panel1.add(title);
        pattern1.addActionListener(this);
        pattern2.addActionListener(this);

        panel.add(pattern1);
        panel.add(pattern2);
        title2=new JLabel();
        title2.setHorizontalAlignment(JLabel.CENTER);
        title2.setText("<html><p>请选择练习模式</p></html>");

        panel2.add(title2);
        try {
            JLabel k=new JLabel();
            Vector<String>l=link.showRank();
            String s="<html><p>";
            for(int i=0;i<Math.min(6,l.size());i++){
               s=s+""+(i+1)+". "+l.get(i)+"<br />";
            }
            s+="</p></html>";
            k.setText(s);
            k.setHorizontalAlignment(JLabel.CENTER);
            panel1.add(k);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        panel1.add(new JLabel(" "));
        panel1.add(title2);

        this.add(panel,BorderLayout.SOUTH);
        this.add(panel1,BorderLayout.NORTH);
        //this.add(panel2,BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==pattern1)	{
            this.setVisible(false);
            this.dispose();
            new Choose(user,false);
        }
        if(e.getSource()==pattern2) {
            //new Test3(user,"sx");
            this.setVisible(false);
            this.dispose();
            new Choose(user,true);
        }
    }

}
