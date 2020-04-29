package OJ1;

import java.sql.*;
import java.util.Vector;

public  class LinkDatabase  {
    private Statement stmt = null;
    private Connection conn;
    private String user;
    public LinkDatabase(String user,String dname){
        try {
            this.user=user;
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://121.36.171.81:3306/"+dname+"?useUnicode=true&characterEncoding=utf8";
            String dbUser = "root";
            String dbPassword = "syly";
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.println("连接成功");
            stmt= conn.createStatement();
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
                t=new Testquestion(rs.getString("title"),
                        "A."+rs.getString(3),"B."+rs.getString(4),
                        "C."+rs.getString(5),
                        "D."+rs.getString(6),rs.getString("ans"));
                qList.add(t);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return qList;
    }
    public boolean sUser(String user, String password){
        try {
            String sSQL = "SELECT password FROM user WHERE name='" + user + "'";//查询数据库表信息
            ResultSet rs = stmt.executeQuery(sSQL);//接收
            while (rs.next()){   //读取
                if(password.equals(rs.getString("password"))) {
                    return true;
                }
                else
                    return false;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void createUser(String user, String password) throws SQLException {
        String sql = "insert into user values(?,?)";//查询数据库表信息
        PreparedStatement pst = conn.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
        pst.setString(1, user);
        pst.setString(2, password);
        System.out.println(pst.executeUpdate());//解释在下

    }

}
