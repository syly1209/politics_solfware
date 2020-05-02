package OJ1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.*;

public class Register extends examFrame implements ActionListener {
    private JButton pattern1;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JTextField user;
    private JTextField password;
    public Register(){
        super(520,340);
        this.setLayout(new GridLayout(7,5,5,5));
        JLabel title1;
        title1 = new JLabel("欢迎来到用户注册");
        title1.setHorizontalAlignment(JLabel.CENTER);
        title1.setFont(new Font("宋体", Font.PLAIN, 25));
        this.add(title1,BorderLayout.NORTH);

        JLabel title2;
        title2 = new JLabel("用户名可以是任何字符");
        title2.setHorizontalAlignment(JLabel.CENTER);
        title2.setFont(new Font("宋体", Font.PLAIN, 20));
        this.add(title2,BorderLayout.CENTER);

        JLabel title3;
        title3 = new JLabel("密码格式：8位字符(数字或字母)");
        title3.setHorizontalAlignment(JLabel.CENTER);
        title3.setFont(new Font("宋体", Font.PLAIN, 20));
        this.add(title3);

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

        pattern1 = new JButton("注册");
        panel3 = new JPanel();

        pattern1.setFont(new Font("宋体", Font.PLAIN, 15));

        pattern1.addActionListener(this);

        panel3.add(pattern1);

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

            if(a.isEmpty()||b.isEmpty()) {
                JOptionPane.showMessageDialog(null, "输入为空");
                return;
            }
            if(!isPassword(b)){
                JOptionPane.showMessageDialog(null, "密码不合法");
                return;
            }
            LinkDatabase k=new LinkDatabase(a,"test");
            try {
                k.createUser(a,b);
                JOptionPane.showMessageDialog(null, "注册成功");
                this.setVisible(false);
                logIn a1=new logIn();
                a1.init();
                a1.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "创建失败");
            }

        }
    }
    private boolean isPassword(String a){
        if(a.length()!=8)
            return false;
        String pattern = "[a-zA-z0-9]+";
        if(Pattern.matches(pattern, a))
            return true;
        else
            return false;
    }
    private boolean isUser(String a){
        String pattern = "\\w{0,}\\@\\w{0,}\\.{1}\\w{0,}";
        if(Pattern.matches(pattern, a))
            return true;
        else
            return false;
    }
}
