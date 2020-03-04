package OJ1;

public class Testquestion {
    private  String questionText ="";
    private String standardkey = "";
    private String  selectKey ="";
    public Testquestion(String questionText, String standardkey) {
        super();
        this.questionText = questionText;
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
    public boolean check() {
        if (this.selectKey.equals(this.standardkey)) {
            return true;
        }
        else {
            return false;
        }
    }
}



