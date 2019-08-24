package pojo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TicketBean {
	@Id
	private int ticketid;
	private String description;
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentBean department;
	private String priority;
	private LocalDate start_date;
	private LocalDate completed_date;
	private LocalDate requestedend_date;
	private String acutal_priority;
	private String customer;
	@ManyToOne
	@JoinColumn(name="serviceengineer")
	private ServiceEngineer service_engineer;
	private String status;
	
	
	public int getTicketid() {
		return ticketid;
	}
	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public DepartmentBean getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public LocalDate getCompleted_date() {
		return completed_date;
	}
	public void setCompleted_date(LocalDate completed_date) {
		this.completed_date = completed_date;
	}
	public String getAcutal_priority() {
		return acutal_priority;
	}
	public void setAcutal_priority(String acutal_priority) {
		this.acutal_priority = acutal_priority;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getRequestedend_date() {
		return requestedend_date;
	}
	public void setRequestedend_date(LocalDate requestedend_date) {
		this.requestedend_date = requestedend_date;
	}
	public ServiceEngineer getService_engineer() {
		return service_engineer;
	}
	public void setService_engineer(ServiceEngineer service_engineer) {
		this.service_engineer = service_engineer;
	}
	@Override
	public String toString() {
		return "TicketBean [ticketid=" + ticketid + ", description=" + description + ", department=" + department
				+ ", priority=" + priority + ", start_date=" + start_date + ", completed_date=" + completed_date
				+ ", requestedend_date=" + requestedend_date + ", acutal_priority=" + acutal_priority + ", customer="
				+ customer + ", service_engineer=" + service_engineer + ", status=" + status + "]";
	}
	

}
