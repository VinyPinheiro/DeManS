/*****************************
 * Class name: Member (.java)
 * 
 * Purpose: Class to create a member type with your attributes.
 *****************************/

package model;

import java.util.Date;

import exception.AddressException;
import exception.MemberException;

public class Member {

	// Constants
	public static final String NULL_NUMBER = "ID não pode ser nulo";
	public static final String NEGATIVE_NUMBER = "ID não pode ser negativo";
	public static final String NULL_NAME = "Nome não pode ser nulo";
	public static final String LARGE_NAME = "Nome não pode ter mais que 100 caracteres";
	public static final String NULL_BIRTHDATE = "Data de nascimento não pode ser nulo";
	public static final String NULL_PASSWORD = "Senha não pode ser nulo";
	public static final String PASSWORD_NOT_IN_RANGE = "Senha deve conter de 6 à 20 caracteres";
	public static final String NULL_ADDRESS = "Endereço não pode ser nulo";
	public static final String NULL_PHONE = "Telefone não pode ser nulo";
	public static final String INVALID_PHONE = "Telefone não esta no formato solicitado";
	public static final String NULL_DAD_PHONE = "telefone do pai não pode ser nulo";
	public static final String INVALID_DAD_PHONE = "telefone do pai não esta no formato solicitado";

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
	 * @param id
	 *            Not null value and no negative number
	 * @param name
	 *            not null value and no more than 100 characters
	 * @param birthdate
	 *            date with minimum value 12 years ago
	 * @param password
	 *            range 6 to 20 characters
	 * @param phone
	 *            not null in the format "(XX)XXXXX-XXXX" or "(XX)XXXX-XXXX"
	 * @param dad_phone
	 *            not null in the format "(XX)XXXXX-XXXX" or "(XX)XXXX-XXXX"
	 * @param address
	 *            not null Address
	 * @throws MemberException
	 *             It occurs if a parameter does not comply with the
	 *             restrictions
	 */
	public Member(Integer id, String name, Date birthdate, String password, String phone, String dad_phone,
			Address address) throws MemberException {
		setId(id);
		setName(name);
		setBirthdate(birthdate);
		setPassword(password);
		setPhone(phone);
		setDad_phone(dad_phone);
		setAddress(address);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) throws MemberException {
		if (id != null) {
			if (id >= 0) {
				this.id = id;
			} else {
				throw new MemberException(Member.NEGATIVE_NUMBER);
			}
		} else {
			throw new MemberException(Member.NULL_NUMBER);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws MemberException {
		if (name != null) {
			if (name.length() <= 100) {
				this.name = name;
			} else {
				throw new MemberException(Member.LARGE_NAME);
			}
		} else {
			throw new MemberException(Member.NULL_NAME);
		}
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) throws MemberException {
		if (birthdate != null) {
			this.birthdate = birthdate;
		} else {
			throw new MemberException(Member.NULL_BIRTHDATE);
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws MemberException {
		if (password != null) {
			if (password.length() >= 6 && password.length() <= 20) {
				this.password = password;
			} else {
				throw new MemberException(Member.PASSWORD_NOT_IN_RANGE);
			}
		} else {
			throw new MemberException(Member.NULL_PASSWORD);
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws MemberException {
		if (phone != null) {
			final String regex = "^/([1-9]{2}/)[2-9][0-9]{4,5}(/-|)[0-9]{4}$";
			if (phone.matches(regex)) {
				this.phone = phone;
			} else {
				throw new MemberException(Member.INVALID_PHONE);
			}
		} else {
			throw new MemberException(Member.NULL_PHONE);
		}
	}

	public String getDad_phone() {
		return dad_phone;
	}

	public void setDad_phone(String dad_phone) throws MemberException {
		if (dad_phone != null) {
			final String regex = "^/([1-9]{2}/)[2-9][0-9]{4,5}(/-|)[0-9]{4}$";
			if (dad_phone.matches(regex)) {
				this.dad_phone = dad_phone;
			} else {
				throw new MemberException(Member.INVALID_DAD_PHONE);
			}
		} else {
			throw new MemberException(Member.NULL_DAD_PHONE);
		}
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) throws MemberException {
		if (address != null) {
			this.address = address;
		} else {
			throw new MemberException(Member.NULL_ADDRESS);
		}
	}

}
