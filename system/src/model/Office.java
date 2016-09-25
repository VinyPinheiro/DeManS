package model;

import java.util.Arrays;
import java.util.Vector;

import exception.OfficeException;

public class Office {

	private Member member;
	private String office;

	/* Help */
	public static final String[] VALID_OFFICES = { "Mestre Conselheiro", "1º Conselheiro", "2º Conselheiro", "Tesoureiro",
			"Escrivão", "Orador", "1º Diácono", "2º Diácono", "1º Mordomo", "2º Mordomo", "Hospitaleiro", "Capelão",
			"Porta Bandeira", "Sentinela", "Mestre de Cerimônias", "1º Preceptor", "2º Preceptor", "3º Preceptor",
			"4º Preceptor", "5º Preceptor", "6º Preceptor", "7º Preceptor" };
	
	public static final String[] VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS = { "Mestre Conselheiro", "1 Conselheiro", "2 Conselheiro", "Tesoureiro",
			"Escrivao", "Orador", "1 Diacono", "2 Diacono", "1 Mordomo", "2 Mordomo", "Hospitaleiro", "Capelao",
			"Porta Bandeira", "Sentinela", "Mestre de Cerimonias", "1 Preceptor", "2 Preceptor", "3 Preceptor",
			"4 Preceptor", "5 Preceptor", "6 Preceptor", "7 Preceptor" };

	/* Exception */
	private final String NULL_MC_MEMBER = "Mestre Conselheiro não pode ser nulo.";
	private final String NULL_1C_MEMBER = "1º Conselheiro não pode ser nulo.";
	private final String NULL_2C_MEMBER = "2º Conselheiro não pode ser nulo.";
	private final String INVALID_OFFICE = "Cargo não existe.";

	/**
	 * 
	 * @param member
	 *            not null value
	 * @param office
	 *            only values in VALID_OFFICES
	 * @throws OfficeException
	 */
	public Office(Member member, String office) throws OfficeException {
		setOffice(office);
		setMember(member);
	}
	
	/**
	 * 
	 * @param member
	 *            not null value
	 * @param office
	 *            only values in VALID_OFFICES
	 * @throws OfficeException
	 */
	public Office(String office) throws OfficeException {
		setOffice(office);
		setMember(null);
	}

	public Member getMember() {
		return member;
	}

	private void setMember(Member member) throws OfficeException {

		if (member != null) {
			this.member = member;
		} else {
			if (this.office.equals(Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[0])) {
				throw new OfficeException(NULL_MC_MEMBER);
			} else {
				if (this.office.equals(Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[1])) {
					throw new OfficeException(NULL_1C_MEMBER);
				} else {
					if (this.office.equals(Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[2])) {
						throw new OfficeException(NULL_2C_MEMBER);
					} else {
						this.member = member;
					}
				}
			}
		}
	}

	public String getOffice() {
		return office;
	}

	private void setOffice(String office) throws OfficeException {
		if (Arrays.asList(Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS).contains(office)) {
			this.office = office;
		} else {
			throw new OfficeException(INVALID_OFFICE);
		}
	}
}
