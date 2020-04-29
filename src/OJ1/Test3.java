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
    private  JLabel title;
    private JPanel panel,panel2,panel3;
    private String subject="";
    Testquestion[] questions;
    int p=0;
    int topicnum=0;
    int right,error;
    ClockDisplay mt;
    private LinkDatabase conn;

    public Test3(String user,String subject){
        super(740,520);
        this.subject=subject;

        panel = new JPanel();
        panel2 = new JPanel(new GridLayout(5,1));
        panel3 = new JPanel();
        label = new JLabel("总考试时间:100分钟 ");
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

        this.setVisible(true);

        mt = new ClockDisplay(clock, 100);
    }

    public void createExam() {

        Vector <Testquestion> qList=conn.loadExam(subject);
        topicnum=qList.size();//确定题目数量
        questions=new Testquestion[topicnum];
        for (int i=0;i<qList.size();i++) {
            questions[i]=(Testquestion)qList.get(i);
        }
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

        title =new JLabel();
        //title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("宋体", Font.PLAIN, 23));
        title.setSize(740,0);
        title.setText("<html><p class=\"p3\">&emsp;"+get(title,"呵呵"+questions[p].getQuestionText())+"</p></html>");
        aButton =  new JRadioButton(questions[p].getA()+"\n");
        bButton =  new JRadioButton(questions[p].getB()+"\n");
        cButton =  new JRadioButton(questions[p].getC()+"\n");
        dButton =  new JRadioButton(questions[p].getD()+"\n");
        buttonGroup = new ButtonGroup();

        aButton.setFont(new Font("宋体", Font.PLAIN, 20));
        bButton.setFont(new Font("宋体", Font.PLAIN, 20));
        cButton.setFont(new Font("宋体", Font.PLAIN, 20));
        dButton.setFont(new Font("宋体", Font.PLAIN, 20));

        aButton.addActionListener(this);
        bButton.addActionListener(this);
        cButton.addActionListener(this);
        dButton.addActionListener(this);

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
