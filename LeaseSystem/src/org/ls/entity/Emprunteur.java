package org.ls.entity;

/**
 * 租客
 */
public class Emprunteur extends Admin {

	private String familyName;// 姓
	private String lastName;// 名
	private Address address;

	public Emprunteur(String id, String password) {
		super(id, password);
	}

	/**
	 * @param familyName
	 * @param lastName
	 * @param houseNum
	 * @param street
	 * @param postcode
	 * @param city
	 */
	public Emprunteur(String id, String password, String familyName,
			String lastName, String houseNum, String street, String postcode,
			String city) {
		super(id, password);
		this.familyName = familyName;
		this.lastName = lastName;
		address = new Address(houseNum, street, postcode, city);
	}       //  initialisation les attribut de Emprunteur 

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
