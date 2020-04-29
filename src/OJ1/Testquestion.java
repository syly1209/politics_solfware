package OJ1;

public class Testquestion {
    private  String questionText ="";
    private String a="";
    private String b="";
    private String c="";
    private String d="";

    private String standardkey = "";
    private String  selectKey ="";
    public Testquestion(String questionText,String a,String b,String c,String d, String standardkey) {
        super();
        this.questionText = questionText;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.standardkey = standardkey;
    }
    public String getQuestionText() {
        return questionText;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public String getStandardkey() {
        return standardkey;
    }
    public void setStandardkey(String standardkey) {
        this.standardkey = standardkey;
    }
    public String getSelectKey() {
        return selectKey;
    }
    public void setSelectKey(String selectKey) {
        this.selectKey = selectKey;
    }
    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }
    public boolean check() {
        if (this.selectKey.equals(this.standardkey)) {
            return true;
        }
        else {
            return false;
        }
    }
}



