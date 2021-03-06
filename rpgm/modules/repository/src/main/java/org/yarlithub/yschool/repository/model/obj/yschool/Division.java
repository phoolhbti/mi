package org.yarlithub.yschool.repository.model.obj.yschool;

import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;
import org.yarlithub.yschool.repository.model.obj.yschool.Classroom;
import org.yarlithub.yschool.repository.model.obj.yschool.iface.IDivision;


/** 
 * Object mapping for hibernate-handled table: division.
 * @author autogenerated
 */

@Entity
@Table(name = "division", schema = "rpgm")
public class Division implements Cloneable, Serializable, IPojoGenEntity, IDivision {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558977430L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Integer> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Integer>());
	
	/** hashCode temporary storage. */
	private volatile Integer hashCode;
	

	/** Field mapping. */
	private Set<Classroom> classrooms = new HashSet<Classroom>();

	/** Field mapping. */
	private String division;
	/** Field mapping. */
	private Integer id = 0; // init for hibernate bug workaround
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Division() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Division(Integer id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param division String object;
	 * @param id Integer object;
	 */
	public Division(String division, Integer id) {

		this.division = division;
		this.id = id;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Division.class;
	}
 

    /**
     * Return the value associated with the column: classroom.
	 * @return A Set&lt;Classroom&gt; object (this.classroom)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "divisionIddivision"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@Column( nullable = false  )
	public Set<Classroom> getClassrooms() {
		return this.classrooms;
		
	}
	
	/**
	 * Adds a bi-directional link of type Classroom to the classrooms set.
	 * @param classroom item to add
	 */
	public void addClassroom(Classroom classroom) {
		classroom.setDivisionIddivision(this);
		this.classrooms.add(classroom);
	}

  
    /**  
     * Set the value related to the column: classroom.
	 * @param classroom the classroom value you wish to set
	 */
	public void setClassrooms(final Set<Classroom> classroom) {
		this.classrooms = classroom;
	}

    /**
     * Return the value associated with the column: division.
	 * @return A String object (this.division)
	 */
	@Basic( optional = false )
	@Column( nullable = false, length = 45  )
	public String getDivision() {
		return this.division;
		
	}
	

  
    /**  
     * Set the value related to the column: division.
	 * @param division the division value you wish to set
	 */
	public void setDivision(final String division) {
		this.division = division;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A Integer object (this.id)
	 */
    @Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic( optional = false )
	@Column( name = "iddivision", nullable = false  )
	public Integer getId() {
		return this.id;
		
	}
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final Integer id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null || this.id == 0) &&
				(id != null) &&
				(this.hashCode != null) ) {
		SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Division clone() throws CloneNotSupportedException {
		
        final Division copy = (Division)super.clone();

		if (this.getClassrooms() != null) {
			copy.getClassrooms().addAll(this.getClassrooms());
		}
		copy.setDivision(this.getDivision());
		copy.setId(this.getId());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("division: " + this.getDivision() + ", ");
		sb.append("id: " + this.getId());
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

		
		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation(); 
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}
		
		final Division that; 
		try {
			that = (Division) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getDivision() == null) && (that.getDivision() == null)) || (getDivision() != null && getDivision().equals(that.getDivision())));
		return result;
	}
	
	/** Calculate the hashcode.
	 * @see java.lang.Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
		if ( this.hashCode == null ) {
			synchronized ( this ) {
				if ( this.hashCode == null ) {
					Integer newHashCode = null;

					if ( getId() != null ) {
					newHashCode = SAVED_HASHES.get( getId() );
					}
					
					if ( newHashCode == null ) {
						if ( getId() != null && getId() != 0) {
							newHashCode = getId();
						} else {

						}
					}
					
					this.hashCode = newHashCode;
				}
			}
		}
	return this.hashCode;
	}
	

	
}
