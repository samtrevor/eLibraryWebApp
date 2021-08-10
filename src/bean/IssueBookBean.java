package bean;

import java.sql.Date;

public class IssueBookBean {

	private String callNo;
	private String studentId;
	private String studentName;
	private String studentMobile;
	private Date issueDate;
	private String returnStatus;

	public IssueBookBean() {

	}

	public IssueBookBean(String callNo) {
		super();
		this.callNo = callNo;
	}

	public IssueBookBean(String callNo, String studentId, String studentName, String studentMobile) {
		super();
		this.callNo = callNo;
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentMobile = studentMobile;
	}

	public String getCallNo() {
		return callNo;
	}

	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentMobile() {
		return studentMobile;
	}

	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date date) {
		this.issueDate = date;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

}
