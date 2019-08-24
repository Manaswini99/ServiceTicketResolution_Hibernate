
package pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DepartmentBean {
@Id
private int department_id;
private String department_name;
public int getDepartment_id() {
	return department_id;
}
public String getDepartment_name() {
	return department_name;
}


public void setDepartment_id(int department_id) {
	this.department_id = department_id;
}
public void setDepartment_name(String department_name) {
	this.department_name = department_name;
}



}



