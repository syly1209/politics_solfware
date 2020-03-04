package OJ1;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Test3 extends JFrame implements ActionListener{

    private JButton start,commit,back,next;
    private JRadioButton aButton,bButton,cButton,dButton;
    private ButtonGroup buttonGroup;
    private JLabel label,clock;
    private  JTextArea jTextArea;
    private JPanel panel,panel2,panel3;
    Testquestion t1;
    Testquestion[] questions;
    int examtime;
    int p=0;//设置题目数指针
    int topicnum=0;
    int right,error;                                                     //答对和答错
    ClockDisplay mt;                                                     //倒计时模块

    public Test3(){

        this.setTitle("学生在线考试系统v1");                                   //设置标题
        this.setSize(440,320);                                           //设置窗口大小
        this.setLocationRelativeTo(null);                                //设置显示位置居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             //设置关闭时关闭

        panel = new JPanel();                                            //初始化面板
        panel2 = new JPanel();
        panel3 = new JPanel();
        label = new JLabel("总考试时间:100分钟 ");                             //初始化并命名标签
        clock = new JLabel();
        jTextArea = new JTextArea(10,35);                                //初始化文本区域
        jTextArea.setEditable(false);                                    //设置文本不可修改

        aButton =  new JRadioButton("A");                                //初始化单选按钮
        bButton =  new JRadioButton("B");
        cButton =  new JRadioButton("C");
        dButton =  new JRadioButton("D");
        buttonGroup = new ButtonGroup();                                 //初始化选项组

        start = new JButton("开始考试");                                   //初始化按键
        back = new JButton("上一题");
        next  = new JButton("下一题");
        commit = new JButton("提交考试");

        aButton.addActionListener(this);                              //单选按钮添加监听事件
        bButton.addActionListener(this);
        cButton.addActionListener(this);
        dButton.addActionListener(this);

        start.addActionListener(this);                                //按钮添加监听事件
        back.addActionListener(this);
        next.addActionListener(this);
        commit.addActionListener(this);


        buttonGroup.add(aButton);                                     //把单选按钮放到选项组
        buttonGroup.add(bButton);
        buttonGroup.add(cButton);
        buttonGroup.add(dButton);

        panel.add(label);                                             //把标签放入面板panel
        panel.add(clock);
        panel.add(start);                                             //把按键放入面板panel
        panel2.add(jTextArea);                                        //把文本区域放入面板panel2
        panel3.add(aButton);                                          //把单选按钮放入面板panel3
        panel3.add(bButton);
        panel3.add(cButton);
        panel3.add(dButton);
        panel3.add(back);                                             //把按键放入面板panel3
        panel3.add(next);
        panel3.add(commit);

        this.add(panel,BorderLayout.NORTH);                           //设置面板panel放在上面
        this.add(panel2,BorderLayout.CENTER);                         //设置面板panel2放在中间
        this.add(panel3, BorderLayout.SOUTH);                         //设置面板panel放在下面

        this.setVisible(true);                                        //设置窗口可见

        mt = new ClockDisplay(clock, 100);                            //调用并设置倒计时的时间
    }

    public void createExam()
    {
        Testquestion t=null;
        String questionText="";
        String standardKey="";

        Vector <Testquestion> qList=new Vector <Testquestion>();
        //读取试题文件，获取考试时间和题目等内容
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://114.215.25.205:3306/test?useUnicode=true&characterEncoding=utf8";//驱动加载
            String dbUser = "root"; //数据库用户名
            String dbPassword = "syly";	//密码
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.println("JDBC驱动程序连接数据库成功!");
            stmt=conn.createStatement(); //创建连接方法
        }
        catch (Exception e){
            e.printStackTrace();//打印异常信息
        }

        try {
            String sSQL = "SELECT * FROM sx";//查询数据库表信息
            ResultSet rs = stmt.executeQuery(sSQL);//接收
            while (rs.next()){   //读取
                //System.out.print(rs.getString("tm")+"     ");
                questionText=rs.getString("title")+"\nA."+rs.getString(3)+"\nB."+rs.getString(4)+"\nC."+rs.getString(5)+"\nD."+rs.getString(6)+'\n';//获取表的列
                standardKey=rs.getString("ans");
                t=new Testquestion(questionText,standardKey);
                qList.add(t);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());

        }

        topicnum=qList.size();//确定题目数量
        questions=new Testquestion[topicnum];
        for (int i=0;i<qList.size();i++) {
            questions[i]=(Testquestion)qList.get(i);
        }
    }

    public void setSelected(String s) {//设置单选按钮不重复模块
        if (s.equals("B")) buttonGroup.setSelected(bButton.getModel(), true);
        if (s.equals("C")) buttonGroup.setSelected(cButton.getModel(), true);
        if (s.equals("D")) buttonGroup.setSelected(dButton.getModel(), true);
        if (s.equals("")) buttonGroup.clearSelection();	if (s.equals("A")) buttonGroup.setSelected(aButton.getModel(), true);

    }

    public void showQuestion() {//设置试题模块
        jTextArea.setText("");
        jTextArea.append(questions[p].getQuestionText());//在文本区域显示试题
        setSelected(questions[p].getSelectKey());
    }

    public void showScore() {//设置成绩模块
        right=0;error=0;
        for (int i = 0; i < topicnum; i++) {
            if (questions[i].check()) {//判断答案的正确与错误
                right++;
            }else {
                error++;
            }
        }
        int score = (int)(right*100/topicnum);            //设置分数
        JOptionPane.showMessageDialog(null, "答对"+right+"题，答错"+error+"题，分数为"+score);
    }


    @Override
    public void actionPerformed(ActionEvent e) {//动作监听事件

        if (e.getSource()==start) {//开始开始按键实现
            createExam();          //调用createExam模块
            p=0;                   //题目序号
            showQuestion();        //调用showQuestion模块
            start.setEnabled(false);//设置按钮不可点击
            mt.start();             //考试时间倒计时启动
        }
        if (e.getSource()==back) {//上一题的按键实现
            p--;
            if (p==-1) {
                JOptionPane.showMessageDialog(null, "已经是第一题");
                p++;
            }
            showQuestion();
        }
        if (e.getSource()==next) {//下一题的按键实现
            p++;
            if (p==topicnum) {
                JOptionPane.showMessageDialog(null, "已经是最后一题");
                p--;
            }
            showQuestion();
        }
        if (e.getSource()==commit) {//提交试卷的按键实现
            showScore();
            commit.setEnabled(false);
            System.exit(0);          //退出
        }

        if(e.getSource()==aButton) questions[p].setSelectKey("A");
        if(e.getSource()==bButton) questions[p].setSelectKey("B");
        if(e.getSource()==cButton) questions[p].setSelectKey("C");
        if(e.getSource()==dButton) questions[p].setSelectKey("D");

    }

    public static void main(String[] args) {
        new Test3();
    }
}
