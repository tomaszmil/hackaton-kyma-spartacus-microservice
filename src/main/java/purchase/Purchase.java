package purchase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nameSpace;
	private String postalCode;
	private String town;
	private double total;

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}


	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}