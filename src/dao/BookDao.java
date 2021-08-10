package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import bean.IssueBookBean;

public class BookDao {

	static Connection con;

	public static int save(BookBean bean) {
		int status = 0;
		try {
			con = Connector.getConnection();
			String sql = "insert into e_book (CallNo,Name,Author,Publisher,Quantity,Issued) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getCallno());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getAuthor());
			ps.setString(4, bean.getPublisher());
			ps.setInt(5, bean.getQuantity());
			ps.setInt(6, 0);
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public static List<BookBean> viewBook() {
		List<BookBean> list = new ArrayList<BookBean>();
		try {
			con = Connector.getConnection();
			String sql = "select * from e_book";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BookBean bean = new BookBean();
				bean.setCallno(rs.getString(1));
				bean.setName(rs.getString(2));
				bean.setAuthor(rs.getString(3));
				bean.setPublisher(rs.getString(4));
				bean.setQuantity(rs.getInt(5));
				bean.setIssued(rs.getInt(6));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static int delete(String callno) {
		int status = 0;

		try {
			con = Connector.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from e_book where CallNo='" + callno + "'");
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static boolean checkIssue(String callno) {
		boolean status = false;
		try {
			con = Connector.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from e_book where CallNo=? and Quantity>Issued");
			ps.setString(1, callno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				status = true;
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static int issueBook(IssueBookBean bean) {
		String callno = bean.getCallNo();
		boolean checkstatus = checkIssue(callno);
		System.out.println("Check status: " + checkstatus);
		if (checkstatus) {
			int status = 0;
			try {
				con = Connector.getConnection();
				PreparedStatement ps = con.prepareStatement("insert into e_issuebook values(?,?,?,?,?,?)");
				ps.setString(1, bean.getCallNo());
				ps.setString(2, bean.getStudentId());
				ps.setString(3, bean.getStudentName());
				ps.setString(4, bean.getStudentMobile());
				java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
				ps.setDate(5, currentDate);
				ps.setString(6, "no");

				status = ps.executeUpdate();
				if (status > 0) {
					PreparedStatement ps2 = con.prepareStatement("update e_book set Issued=? where CallNo=?");
					ps2.setInt(1, getIssued(callno) + 1);
					ps2.setString(2, callno);
					status = ps2.executeUpdate();
				}
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

			return status;
		} else {
			return 0;
		}
	}

	public static int getIssued(String callno) {
		int issued = 0;
		try {
			con = Connector.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from e_book where CallNo=?");
			ps.setString(1, callno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				issued = rs.getInt("Issued");
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return issued;
	}

	public static int returnBook(String callno, String studentid) {
		int status = 0;
		try {
			con = Connector.getConnection();
			PreparedStatement ps = con
					.prepareStatement("update e_issuebook set ReturnStatus='yes' where CallNo=? and StudentId=?");
			ps.setString(1, callno);
			ps.setString(2, studentid);

			status = ps.executeUpdate();
			if (status > 0) {
				PreparedStatement ps2 = con.prepareStatement("update e_book set Issued=? where CallNo=?");
				ps2.setInt(1, getIssued(callno) - 1);
				ps2.setString(2, callno);
				status = ps2.executeUpdate();
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static List<IssueBookBean> viewIssuedBooks() {
		List<IssueBookBean> list = new ArrayList<IssueBookBean>();
		try {
			con = Connector.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from e_issuebook order by IssueDate");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				IssueBookBean bean = new IssueBookBean();
				bean.setCallNo(rs.getString("CallNo"));
				bean.setStudentId(rs.getString("StudentId"));
				bean.setStudentName(rs.getString("StudentName"));
				bean.setStudentMobile(rs.getString("StudentMobile"));
				bean.setIssueDate(rs.getDate("IssueDate"));
				bean.setReturnStatus(rs.getString("ReturnStatus"));
				list.add(bean);
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}
}
