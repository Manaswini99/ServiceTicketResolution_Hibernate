package pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Roles {
	@Id
	private int roleID;
	private String roleName;
	
//	@OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
//	private List<Credentials> credentials;
	
	
//	public List<Credentials> getCredentials() {
//		return credentials;
//	}
//	public void setCredentials(List<Credentials> credentials) {
//		this.credentials = credentials;
//	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Roles()
	{
		
	}
}