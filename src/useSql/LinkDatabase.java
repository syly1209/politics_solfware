package useSql;

import OJ1.Testquestion;

import java.sql.*;
import java.util.Vector;

public  class LinkDatabase  {
    private Connection conn = null;
    private Statement stmt = null;
    private String dbUser = "root";
    private String dbPassword = "syly";
    public LinkDatabase(String dname){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://114.215.25.205:3306/"+dname+"?useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.println("连接成功");
            stmt=conn.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();//打印异常信息
        }
    }
    public Vector<Testquestion> loadExam(String tname){
        Testquestion t=null;
        String questionText="";
        String standardKey="";
        Vector <Testquestion> qList=new Vector <Testquestion>();
        try {
            String sSQL = "SELECT * FROM "+tname;
            ResultSet rs = stmt.executeQuery(sSQL);
            while (rs.next()){
                questionText=rs.getString("title")+"\nA."+rs.getString(3)+"\nB."+rs.getString(4)+"\nC."+rs.getString(5)+"\nD."+rs.getString(6)+'\n';
                standardKey=rs.getString("ans");
                t=new Testquestion(questionText,standardKey);
                qList.add(t);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return qList;
    }
    public String getOp(){
        String op="";
        try {
            String sSQL = "SELECT * FROM operator";//查询数据库表信息
            ResultSet rs = stmt.executeQuery(sSQL);//接收
            while (rs.next()){   //读取
                //System.out.print(rs.getString("tm")+"     ");
                op=rs.getString("op");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return op;
    }

}
