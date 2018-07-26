package org.ls.entity;

/*
 * 地址
 */
public class Address {

	private String houseNum;// 门牌号  numero de la porte
	private String street;// 街道   Rue
	private String postcode;// 邮编   code poste
	private String city;// 城市   ville

	/**
	 * @param houseNum
	 * @param street
	 * @param postcode
	 * @param city
	 */
	public Address(String houseNum, String street, String postcode, String city) {
		super();
		this.houseNum = houseNum;
		this.street = street;
		this.postcode = postcode;
		this.city = city;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
