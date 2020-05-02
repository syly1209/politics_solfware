package OJ1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;

@SuppressWarnings("serial")
public class Test3 extends examFrame implements ActionListener{

    private JButton start,commit,back,next;
    private JRadioButton aButton,bButton,cButton,dButton;
    private ButtonGroup buttonGroup;
    private JLabel label,clock;
    private  JLabel title;
    private JPanel panel,panel2,panel3;
    private String subject="";
    Testquestion[] questions;
    int p=0;
    int topicnum=0;
    int right,error;
    ClockDisplay mt;
    private LinkDatabase conn;
    private int time;
    private String user;

    public Test3(String user,String subject,int time){
        super(840,620);
        this.subject=subject;
        this.time=time;

        this.user=user;

        panel = new JPanel();

        panel3 = new JPanel();
        label = new JLabel("总考试时间:"+time+"分钟 ");
        clock = new JLabel();

        start = new JButton("开始考试");
        back = new JButton("上一题");
        next  = new JButton("下一题");
        commit = new JButton("提交考试");

        start.addActionListener(this);
        back.addActionListener(this);
        next.addActionListener(this);
        commit.addActionListener(this);

        panel.add(label);
        panel.add(clock);
        panel.add(start);

        panel3.add(back);
        panel3.add(next);
        panel3.add(commit);

        this.add(panel,BorderLayout.NORTH);

        this.add(panel3, BorderLayout.SOUTH);

        conn=new LinkDatabase(user,"test");

        panel2 = new JPanel(new GridLayout(5,1));
        title =new JLabel();

        title.setFont(new Font("宋体", Font.PLAIN, 23));
        title.setSize(740,0);

        aButton=new JRadioButton();
        bButton=new JRadioButton();
        cButton=new JRadioButton();
        dButton=new JRadioButton();

        aButton.setFont(new Font("宋体", Font.PLAIN, 20));
        bButton.setFont(new Font("宋体", Font.PLAIN, 20));
        cButton.setFont(new Font("宋体", Font.PLAIN, 20));
        dButton.setFont(new Font("宋体", Font.PLAIN, 20));

        aButton.addActionListener(this);
        bButton.addActionListener(this);
        cButton.addActionListener(this);
        dButton.addActionListener(this);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(aButton);
        buttonGroup.add(bButton);
        buttonGroup.add(cButton);
        buttonGroup.add(dButton);

        panel2.add(title);
        panel2.add(aButton);
        panel2.add(bButton);
        panel2.add(cButton);
        panel2.add(dButton);

        this.add(panel2,BorderLayout.CENTER);

        this.setVisible(true);

        mt = new ClockDisplay(clock, time);
    }

    public void createExam() {
        if(subject=="sj"){
            Random random=new Random();
            Vector <Testquestion> List=new Vector<>();
            List.addAll(conn.loadExam("sx"));
            List.addAll(conn.loadExam("my"));
            List.addAll(conn.loadExam("gy"));
            List.addAll(conn.loadExam("mg"));
            if(time==20){
                topicnum=20;
            }
            else {
                topicnum=5;
            }
            HashSet<Integer>set=new HashSet<>();
            questions=new Testquestion[topicnum];
            do{
                set.add(random.nextInt(List.size()));
            }while (set.size()!=topicnum);
            int p=0;
            for (int i:set){
                questions[p++]=(Testquestion)List.get(i);
            }
        }
        else {
            Vector <Testquestion> qList=conn.loadExam(subject);
            if(time==60){
                topicnum=qList.size();//确定题目数量
                questions=new Testquestion[topicnum];
                for (int i=0;i<qList.size();i++) {
                    questions[i]=(Testquestion)qList.get(i);
                }
            }
            else {
                Random random=new Random();
                topicnum=5;
                HashSet<Integer>set=new HashSet<>();
                questions=new Testquestion[topicnum];
                do{
                    set.add(random.nextInt(qList.size()));
                }while (set.size()!=topicnum);
                int p=0;
                for (int i:set){
                    questions[p++]=(Testquestion)qList.get(i);
                }
            }
        }
        System.out.println(questions.length);

    }

    public void setSelected(String s) {
        if (s.equals("B"))
            buttonGroup.setSelected(bButton.getModel(), true);
        if (s.equals("C"))
            buttonGroup.setSelected(cButton.getModel(), true);
        if (s.equals("D"))
            buttonGroup.setSelected(dButton.getModel(), true);
        if (s.equals(""))
            buttonGroup.clearSelection();
        if (s.equals("A"))
            buttonGroup.setSelected(aButton.getModel(), true);
    }

    public String get(JLabel jLabel,String longString){
        String b="";
        StringBuilder builder = new StringBuilder("");
        char[] chars = longString.toCharArray();
        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
        int start = 0;
        int len = 0;
        while (start + len < longString.length()) {
            while (true) {
                len++;
                if (start + len > longString.length())break;
                if (fontMetrics.charsWidth(chars, start, len)
                        > jLabel.getWidth()) {
                    break;
                }
            }
            builder.append(chars, start, len-1).append("<br/>");
            start = start + len - 1;
            len = 0;
        }
        builder.append(chars, start, longString.length()-start);
        //builder.append("</html>");
        return String.valueOf(builder.toString().subSequence(2,longString.length()));
    }

    public void showQuestion() {

        title.setText("<html><p class=\"p3\">&emsp;"+get(title,"呵呵"+questions[p].getQuestionText())+"</p></html>");
        System.out.println(questions[p].getQuestionText()+"     "+p);
        aButton.setText(questions[p].getA());
        bButton.setText(questions[p].getB());
        cButton.setText(questions[p].getC());
        dButton.setText(questions[p].getD());
        panel2.setVisible(true);
//        jTextArea.setText("");
//        jTextArea.append(questions[p].getQuestionText());
        setSelected(questions[p].getSelectKey());
    }
    public void showScore() {
        right=0;error=0;
        for (int i = 0; i < topicnum; i++) {
            if (questions[i].check()) {
                right++;
            }else {
                error++;
            }
        }
        conn.update(right);
        JOptionPane.showMessageDialog(null, "答对"+right+"题，答错"+error+"题");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==start) {
            createExam();
            p=0;
            showQuestion();
            start.setVisible(false);
            mt.start();

        }
        if (e.getSource()==back) {
            p--;
            if (p==-1) {
                JOptionPane.showMessageDialog(null, "已经是第一题");
                p++;
            }
            else
                panel2.setVisible(false);
            showQuestion();
        }
        if (e.getSource()==next) {
            p++;
            if (p==topicnum) {
                JOptionPane.showMessageDialog(null, "已经是最后一题");
                p--;
            }
            else
                panel2.setVisible(false);
            showQuestion();
        }
        if (e.getSource()==commit) {
            showScore();
            commit.setEnabled(false);
            this.setVisible(false);
            new Test2(user,conn);
        }

        if(e.getSource()==aButton) questions[p].setSelectKey("A");
        if(e.getSource()==bButton) questions[p].setSelectKey("B");
        if(e.getSource()==cButton) questions[p].setSelectKey("C");
        if(e.getSource()==dButton) questions[p].setSelectKey("D");

    }
}
