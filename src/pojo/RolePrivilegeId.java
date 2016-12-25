package pojo;

import java.io.Serializable;

public class RolePrivilegeId implements Serializable {
	private Integer roleid;
	private Integer privilegeId;
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public RolePrivilegeId(){}
	public RolePrivilegeId(Integer roleid, Integer privilegeId) {
		super();
		this.roleid = roleid;
		this.privilegeId = privilegeId;
	}
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((privilegeId == null) ? 0 : privilegeId.hashCode());
		result = PRIME * result + ((roleid == null) ? 0 : roleid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RolePrivilegeId other = (RolePrivilegeId) obj;
		if (privilegeId == null) {
			if (other.privilegeId != null)
				return false;
		} else if (!privilegeId.equals(other.privilegeId))
			return false;
		if (roleid == null) {
			if (other.roleid != null)
				return false;
		} else if (!roleid.equals(other.roleid))
			return false;
		return true;
	}
}
