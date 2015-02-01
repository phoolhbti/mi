package org.yarlithub.yschool.repository.model.obj.yschool;

import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.yarlithub.yschool.repository.model.obj.yschool.iface.ISchoolHasStaffHasRolePK;


/** 
 * Object mapping for hibernate-handled table: school_has_staff_has_role.
 * @author autogenerated
 */

@Embeddable
public class SchoolHasStaffHasRolePK implements Cloneable, Serializable,  ISchoolHasStaffHasRolePK {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558977418L;

	

	/** Field mapping. */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@JoinColumn(name = "school_idschool", nullable = false , insertable = false, updatable = false )
	private School schoolIdschool;

	/** Field mapping. */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@JoinColumn(name = "staff_has_role_idstaff_has_role", nullable = false , insertable = false, updatable = false )
	private StaffHasRole staffHasRoleIdstaffHasRole;

 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return SchoolHasStaffHasRolePK.class;
	}
 

    /**
     * Return the value associated with the column: schoolIdschool.
	 * @return A School object (this.schoolIdschool)
	 */
	public School getSchoolIdschool() {
		return this.schoolIdschool;
		
	}
	

  
    /**  
     * Set the value related to the column: schoolIdschool.
	 * @param schoolIdschool the schoolIdschool value you wish to set
	 */
	public void setSchoolIdschool(final School schoolIdschool) {
		this.schoolIdschool = schoolIdschool;
	}

    /**
     * Return the value associated with the column: staffHasRoleIdstaffHasRole.
	 * @return A StaffHasRole object (this.staffHasRoleIdstaffHasRole)
	 */
	public StaffHasRole getStaffHasRoleIdstaffHasRole() {
		return this.staffHasRoleIdstaffHasRole;
		
	}
	

  
    /**  
     * Set the value related to the column: staffHasRoleIdstaffHasRole.
	 * @param staffHasRoleIdstaffHasRole the staffHasRoleIdstaffHasRole value you wish to set
	 */
	public void setStaffHasRoleIdstaffHasRole(final StaffHasRole staffHasRoleIdstaffHasRole) {
		this.staffHasRoleIdstaffHasRole = staffHasRoleIdstaffHasRole;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public SchoolHasStaffHasRolePK clone() throws CloneNotSupportedException {
		
        final SchoolHasStaffHasRolePK copy = (SchoolHasStaffHasRolePK)super.clone();

		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();		
	}


	/** Equals implementation. 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;
		
		if ( this == aThat ) {
			 return true;
		}

		if (aThat == null)  {
			 return false;
		}
		
		final SchoolHasStaffHasRolePK that; 
		try {
			that = (SchoolHasStaffHasRolePK) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((getSchoolIdschool() == null) && (that.getSchoolIdschool() == null)) || (getSchoolIdschool() != null && getSchoolIdschool().getId().equals(that.getSchoolIdschool().getId())));	
		result = result && (((getStaffHasRoleIdstaffHasRole() == null) && (that.getStaffHasRoleIdstaffHasRole() == null)) || (getStaffHasRoleIdstaffHasRole() != null && getStaffHasRoleIdstaffHasRole().getId().equals(that.getStaffHasRoleIdstaffHasRole().getId())));	
		return result;
	}
	
	/** Calculate the hashcode.
	 * @see java.lang.Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
	int hash = 0;
		hash = hash + getSchoolIdschool().hashCode();
		hash = hash + getStaffHasRoleIdstaffHasRole().hashCode();
	return hash;
	}
	

	
}
