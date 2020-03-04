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
        NumberFormat numberFormat = NumberFormat.getInstance();//控制时间的显示格式
        numberFormat.setMinimumIntegerDigits(2);//设置数值的整数部分允许的最小位数
        int h,m,s;//定义时分秒
        while (testtime >= 0) {
            h = testtime / 3600;
            m = testtime % 3600 / 60;
            s = testtime % 60;
            StringBuffer stringBuffer = new StringBuffer("");
            //增加到lefttimer标签
            stringBuffer.append("考试剩余时间为："+numberFormat.format(h)+":"+numberFormat.format(m)+":"+numberFormat.format(s));
            lefttimer.setText(stringBuffer.toString());
            try {
                Thread.sleep(1000);//延时一秒
            } catch (Exception e) {
                //ignore error
            }
            testtime = testtime - 1;
        }
        if (testtime <= 0) {
            JOptionPane.showMessageDialog(null, "考试结束");
            System.exit(0);
        }
    }
}
