/*****************************
 * Class name: Address (.java)
 * 
 * Purpose: Class to create and validate addresses
 *****************************/
package model;

import exception.AddressException;

public class Address {

	// Constants
	public static final String NULL_STREET = "Rua não pode ser nulo";
	public static final String LARGE_STREET = "Rua não pode ter mais que 100 caracteres";
	public static final String NULL_NUMBER = "Numero não pode ser nulo";
	public static final String NEGATIVE_NUMBER = "Numero não pode ser negativo";
	public static final String LARGE_COMPLEMENT = "Complemento não pode ter mais que 50 caracteres";
	public static final String NULL_ZIPCODE = "CEP não pode ser nulo";
	public static final String INVALID_ZIPCODE_FORMAT = "CEP não está no formato solicitado xxxxx-xxx";
	public static final String NULL_CITY = "Cidade não pode ser nulo";
	public static final String LARGE_CITY = "Cidade não pode ter mais que 50 caracteres";
	public static final String NULL_UF = "UF não pode ser nulo";

	// Attributes
	private String street = null;
	private Integer number = null;
	private String complement = null;
	private String zipcode = null;
	private String city = null;
	private UF uf = null;

	/**
	 * Construct Method
	 * 
	 * @param street
	 *            not null String and no more 100 characters
	 * @param number
	 *            not null Integer and only number than 0
	 * @param complement
	 *            no more 50 characters
	 * @param zipcode
	 *            String with the format XXXXX-XXX
	 * @param city
	 *            not null String and no more 50 characters
	 * @param uf
	 *            not null String and exactly 2 characters
	 * @throws AddressException
	 *             It occurs if a parameter does not comply with the
	 *             restrictions
	 */
	public Address(final String street, final Integer number, final String complement, final String zipcode,
			final String city, final UF uf) throws AddressException {
		setStreet(street);
		setNumber(number);
		setComplement(complement);
		setZipcode(zipcode);
		setCity(city);
		setUf(uf);
	}

	public String getStreet() {
		return street;
	}

	private void setStreet(final String street) throws AddressException {
		if (street != null) {
			if (street.length() <= 100) {
				this.street = street;
			} else {
				throw new AddressException(Address.LARGE_STREET);
			}
		} else {
			throw new AddressException(Address.NULL_STREET);
		}
	}

	public Integer getNumber() {
		return number;
	}

	private void setNumber(final Integer number) throws AddressException {
		if (number != null) {
			if (number >= 0) {
				this.number = number;
			} else {
				throw new AddressException(Address.NEGATIVE_NUMBER);
			}
		} else {
			throw new AddressException(Address.NULL_NUMBER);
		}
	}

	public String getComplement() {
		return complement;
	}

	private void setComplement(final String complement) throws AddressException {
		if (complement != null) {
			if (complement.length() <= 50) {
				this.complement = complement;
			} else {
				throw new AddressException(Address.LARGE_COMPLEMENT);
			}
		} else {
			this.complement = null;
		}
	}

	public String getZipcode() {
		return zipcode;
	}

	private void setZipcode(final String zipcode) throws AddressException {
		if (zipcode != null) {
			final String regex = "^[1-9][0-9]{4}-[0-9]{3}$";
			if (zipcode.matches(regex)) {

				this.zipcode = zipcode;
			} else {
				throw new AddressException(Address.INVALID_ZIPCODE_FORMAT);
			}
		} else {
			throw new AddressException(Address.NULL_ZIPCODE);
		}
	}

	public String getCity() {
		return city;
	}

	private void setCity(String city) throws AddressException {
		if (city != null) {
			if (city.length() <= 50) {
				this.city = city;
			} else {
				throw new AddressException(Address.LARGE_CITY);
			}
		} else {
			throw new AddressException(Address.NULL_CITY);
		}
	}

	public UF getUf() {
		return uf;
	}

	private void setUf(UF uf) throws AddressException {
		if (uf != null) {
			this.uf = uf;
		} else {
			throw new AddressException(Address.NULL_UF);
		}
	}
}
