package OJ1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Test3 extends examFrame implements ActionListener{

    private JButton start,commit,back,next;
    private JRadioButton aButton,bButton,cButton,dButton;
    private ButtonGroup buttonGroup;
    private JLabel label,clock;
    private  JTextArea jTextArea;
    private JPanel panel,panel2,panel3;
    private String subject="";
    Testquestion t1;
    Testquestion[] questions;
    int p=0;
    int topicnum=0;
    int right,error;
    ClockDisplay mt;

    public Test3(String subject){
        super(740,520);
        this.subject=subject;

        panel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        label = new JLabel("总考试时间:100分钟 ");
        clock = new JLabel();
        jTextArea = new JTextArea(10,35);
        jTextArea.setEditable(false);

        aButton =  new JRadioButton("A");
        bButton =  new JRadioButton("B");
        cButton =  new JRadioButton("C");
        dButton =  new JRadioButton("D");
        buttonGroup = new ButtonGroup();

        start = new JButton("开始考试");
        back = new JButton("上一题");
        next  = new JButton("下一题");
        commit = new JButton("提交考试");

        aButton.addActionListener(this);
        bButton.addActionListener(this);
        cButton.addActionListener(this);
        dButton.addActionListener(this);

        start.addActionListener(this);
        back.addActionListener(this);
        next.addActionListener(this);
        commit.addActionListener(this);

        buttonGroup.add(aButton);
        buttonGroup.add(bButton);
        buttonGroup.add(cButton);
        buttonGroup.add(dButton);

        panel.add(label);
        panel.add(clock);
        panel.add(start);
        panel2.add(jTextArea);
        panel3.add(aButton);
        panel3.add(bButton);
        panel3.add(cButton);
        panel3.add(dButton);
        panel3.add(back);
        panel3.add(next);
        panel3.add(commit);

        this.add(panel,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);

        this.setVisible(true);

        mt = new ClockDisplay(clock, 100);
    }

    public void createExam() {
        LinkDatabase conn=new LinkDatabase("test");
        Vector <Testquestion> qList=conn.loadExam(subject);
        topicnum=qList.size();//确定题目数量
        questions=new Testquestion[topicnum];
        for (int i=0;i<qList.size();i++) {
            questions[i]=(Testquestion)qList.get(i);
        }
    }

    public void setSelected(String s) {
        if (s.equals("B")) buttonGroup.setSelected(bButton.getModel(), true);
        if (s.equals("C")) buttonGroup.setSelected(cButton.getModel(), true);
        if (s.equals("D")) buttonGroup.setSelected(dButton.getModel(), true);
        if (s.equals("")) buttonGroup.clearSelection();
        if (s.equals("A")) buttonGroup.setSelected(aButton.getModel(), true);

    }

    public void showQuestion() {
        jTextArea.setText("");
        jTextArea.append(questions[p].getQuestionText());
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
        int score = (int)(right*100/topicnum);
        JOptionPane.showMessageDialog(null, "答对"+right+"题，答错"+error+"题，分数为"+score);
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
            showQuestion();
        }
        if (e.getSource()==next) {
            p++;
            if (p==topicnum) {
                JOptionPane.showMessageDialog(null, "已经是最后一题");
                p--;
            }
            showQuestion();
        }
        if (e.getSource()==commit) {
            showScore();
            commit.setEnabled(false);
            System.exit(0);
        }

        if(e.getSource()==aButton) questions[p].setSelectKey("A");
        if(e.getSource()==bButton) questions[p].setSelectKey("B");
        if(e.getSource()==cButton) questions[p].setSelectKey("C");
        if(e.getSource()==dButton) questions[p].setSelectKey("D");

    }

}
