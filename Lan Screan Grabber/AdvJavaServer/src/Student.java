import java.sql.*;
import java.util.ArrayList;


public class Student {
	private String userId;
	private String password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static ArrayList<Student> getAllStudents() throws SQLException{
		Connection con=DataConnection.connect();
		String sql="select * from student";
		Statement stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery(sql);
		
		ArrayList<Student> alist=new ArrayList<Student>();
		while(rs.next()){
			Student stud=new Student();
			stud.setUserId(rs.getString("userid"));
			stud.setPassword(rs.getString("password"));
			alist.add(stud);
		}
		return alist;
	}
}
