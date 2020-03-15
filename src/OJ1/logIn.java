package OJ1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logIn extends examFrame implements ActionListener {
    private JButton pattern1,pattern2;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JTextField user;
    private JTextField password;
    public logIn(){
        super(520,340);
        this.setLayout(new GridLayout(5,5,5,5));
        JLabel title1;
        title1 = new JLabel("              欢迎使用XXXX");
        title1.setFont(new Font("宋体", Font.PLAIN, 25));
        this.add(title1,BorderLayout.NORTH);

        JLabel title2;
        title2 = new JLabel("                  请登录");
        title2.setFont(new Font("宋体", Font.PLAIN, 25));
        this.add(title2);

        panel1=new JPanel();
        JLabel label1=new JLabel("用户名：");
        label1.setFont(new Font("宋体", Font.PLAIN, 15));
        user=new JTextField();
        user.setColumns(20);
        user.setFont(new Font("宋体", Font.PLAIN, 15));
        panel1.add(label1);
        panel1.add(user);

        panel2=new JPanel();
        JLabel label2=new JLabel("  密码：");
        label2.setFont(new Font("宋体", Font.PLAIN, 15));
        password=new JPasswordField();
        password.setColumns(20);
        password.setFont(new Font("宋体", Font.PLAIN, 15));
        panel2.add(label2);
        panel2.add(password);

        pattern1 = new JButton("登陆");
        pattern2 = new JButton("注册");
        panel3 = new JPanel();

        pattern1.setFont(new Font("宋体", Font.PLAIN, 15));
        pattern2.setFont(new Font("宋体", Font.PLAIN, 15));

        pattern1.addActionListener(this);
        pattern2.addActionListener(this);

        panel3.add(pattern1);
        panel3.add(pattern2);


        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pattern1){
            String a=user.getText();
            String b=password.getText();
            LinkDatabase p=new LinkDatabase("test");
            if(p.sUser(a,b)){
                this.setVisible(false);
                new Test2();
            }
            else
                JOptionPane.showMessageDialog(null, "用户名错误");
        }
        if(e.getSource()==pattern2){
            this.setVisible(false);
            new Register();
        }
    }
}