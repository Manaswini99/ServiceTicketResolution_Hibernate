package pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ServiceEngineer {

@Id
private String serviceengineer;
@ManyToOne
@JoinColumn(name="department_id")
private DepartmentBean department; 
private int no_of_tickets_worked;
private String currentpriority_ticket;




public int getNo_of_tickets_worked() {
	return no_of_tickets_worked;
}
public void setNo_of_tickets_worked(int no_of_tickets_worked) {
	this.no_of_tickets_worked = no_of_tickets_worked;
}
public String getServiceengineer() {
	return serviceengineer;
}
public void setServiceengineer(String serviceengineer) {
	this.serviceengineer = serviceengineer;
}
public DepartmentBean getDepartment() {
	return department;
}
public void setDepartment(DepartmentBean department) {
	this.department = department;
}
public String getCurrentpriority_ticket() {
	return currentpriority_ticket;
}
public void setCurrentpriority_ticket(String currentpriority_ticket) {
	this.currentpriority_ticket = currentpriority_ticket;
}
@Override
public String toString() {
	return "ServiceEngineer [serviceengineer=" + serviceengineer + ", department=" + department
			+ ", no_of_tickets_worked=" + no_of_tickets_worked + ", currentpriority_ticket="
			+ currentpriority_ticket + "]";
}

}
