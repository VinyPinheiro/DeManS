/*****************************
 * Class name: Member (.java)
 * 
 * Purpose: Class to create a member type with your attributes.
 *****************************/

package model;

import java.util.Date;

public class Member {
	
	// Constants
	

	// Attributes
	private Integer id = null;
	private String name = null;
	private Date birthdate = null;
	private String password = null;
	private String phone = null;
	private String dad_phone = null;
	private Address address = null;

	/**
	 * 
	 * @param id Not null value and no negative number
	 * @param name not null value and no more than 100 characters
	 * @param birthdate date with minimum value 12 years ago
	 * @param password range 6 to 20 characters
	 * @param phone not null in the format "(XX)XXXXX-XXXX" or "(XX)XXXX-XXXX"
	 * @param dad_phone not null in the format "(XX)XXXXX-XXXX" or "(XX)XXXX-XXXX"
	 * @param address not null Address
	 */
	public Member(Integer id, String name, Date birthdate, String password, String phone, String dad_phone,
			Address address) {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDad_phone() {
		return dad_phone;
	}

	public void setDad_phone(String dad_phone) {
		this.dad_phone = dad_phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
