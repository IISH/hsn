package nl.iisg.ref;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ref_birth_address")
public class Ref_BirthAddress {
		
		@Id @Column(name = "IDNR")    				private int       idnr;
	    @Column(name = "ROLE_CONTEXT_ADDRESS")     	private String    roleContextAddress;
		@Column(name = "DELIVERY_LOCATION")			private String    deliveryLocation;
		
		public int getIdnr() {
			return idnr;
		}
		public void setIdnr(int idnr) {
			this.idnr = idnr;
		}
		public String getRoleContextAddress() {
			return roleContextAddress;
		}
		public void setRoleContextAddress(String roleContextAddress) {
			this.roleContextAddress = roleContextAddress;
		}
		public String getDeliveryLocation() {
			return deliveryLocation;
		}
		public void setDeliveryLocation(String deliveryLocation) {
			this.deliveryLocation = deliveryLocation;
		}
		
		
	
}
