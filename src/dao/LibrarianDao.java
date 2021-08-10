package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.LibrarianBean;

public class LibrarianDao {
	 static Connection con;
	 
	 public int save(LibrarianBean bean){
		 int status=0;
		 try{
			 con = Connector.getConnection();
			 String sql = "insert into e_librarian(Id,Name,Password,Email,Mobile) values(?,?,?,?,?)";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, bean.getId());
			 ps.setString(2, bean.getName());
			 ps.setString(3, bean.getPassword());
			 ps.setString(4, bean.getEmail());
			 ps.setString(5, bean.getMobile());
			 status = ps.executeUpdate();
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 
		 return status;
	 }
	 
	 public static List<LibrarianBean> viewlibrarian(){
		 List<LibrarianBean> list = new ArrayList<LibrarianBean>();
		 try{
			 con = Connector.getConnection();
			 String sql = "select * from e_librarian";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 LibrarianBean bean = new LibrarianBean();
				 bean.setId(rs.getInt(1));
				 bean.setName(rs.getString(2));
				 bean.setPassword(rs.getString(3));
				 bean.setEmail(rs.getString(4));
				 bean.setMobile(rs.getString(5));
				 list.add(bean);
			 }
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 
		 return list;
	 }
	 
	 public static LibrarianBean viewById(int id){
		 LibrarianBean bean = new LibrarianBean();
		 try{
			 con = Connector.getConnection();
			 String sql = "select * from e_librarian where id='"+id+"'";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 bean.setId(rs.getInt(1));
				 bean.setName(rs.getString(2));
				 bean.setPassword(rs.getString(3));
				 bean.setEmail(rs.getString(4));
				 bean.setMobile(rs.getString(5));
			 }
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 
		 return bean;
	 }
	 
	 public static int update(LibrarianBean bean){
		 int status = 0;
		 try{
			 con = Connector.getConnection();
			 String sql ="update e_librarian set Name=?,Password=?,Email=?,Mobile=? where id=?";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, bean.getName());
			 ps.setString(2, bean.getPassword());
			 ps.setString(3, bean.getEmail());
			 ps.setString(4, bean.getMobile());
			 ps.setInt(5, bean.getId());
			 status = ps.executeUpdate();
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 return status;
	 }
	 
	 public static int delete(int id){
		 int status = 0;
		 try{
			 con = Connector.getConnection();
			 String sql ="delete from e_librarian where id='"+id+"'";
			 PreparedStatement ps = con.prepareStatement(sql);
			 status = ps.executeUpdate();
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 return status;
	 }
	 
	 public boolean validate(String email,String password){
		 boolean status = false;
		 try{
			 con = Connector.getConnection();
			 String sql ="select * from e_librarian where Email=? and Password=?";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1,email);
			 ps.setString(2, password);
			 ResultSet rs = ps.executeQuery();
			 status = rs.next();
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 
		 return status;
	 }
}
