package OJ1;

import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ClockDisplay extends Thread {
    private JLabel lefttimer;
    private int testtime;

    public ClockDisplay(JLabel lt,int time) {
        lefttimer = lt;
        testtime = time * 60;
    }
    public void run(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumIntegerDigits(2);
        int h,m,s;
        while (testtime >= 0) {
            h = testtime / 3600;
            m = testtime % 3600 / 60;
            s = testtime % 60;
            StringBuffer stringBuffer = new StringBuffer("");
            stringBuffer.append("考试剩余时间为："+numberFormat.format(h)+":"+numberFormat.format(m)+":"+numberFormat.format(s));
            lefttimer.setText(stringBuffer.toString());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            testtime = testtime - 1;
        }
        if (testtime <= 0) {
            JOptionPane.showMessageDialog(null, "考试结束");
            System.exit(0);
        }
    }
}
