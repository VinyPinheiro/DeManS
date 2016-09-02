/*****************************
 * Class name: Member (.java)
 * 
 * Purpose: Class to create a member type with your attributes.
 *****************************/

package model;

import java.util.Date;

public class Member {

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
	
}
