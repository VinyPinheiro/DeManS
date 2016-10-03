/*****************************
 * Class name, UF (.java)
 * 
 * Purpose, Class to create and validate Uf"s
 *****************************/

package model;

import exception.UfException;

public class UF {

	// Constants
	public static final String NOT_EXISTS_STATE = "Estado não encontrado.";
	public static final String INVALID_STATE = "Sigla não encontrada.";
	public static final String[][] STATES = { { "AC", "Acre" }, { "AL", "Alagoas" }, { "AP", "Amapá" },
			{ "AM", "Amazonas" }, { "BA", "Bahia" }, { "CE", "Ceará" }, { "DF", "Distrito Federal" },
			{ "ES", "Espírito Santo" }, { "GO", "Goiás" }, { "MA", "Maranhão" }, { "MT", "Mato Grosso" },
			{ "MS", "Mato Grosso do Sul" }, { "MG", "Minas Gerais" }, { "PA", "Pará" }, { "PB", "Paraíba" },
			{ "PR", "Paraná" }, { "PE", "Pernambuco" }, { "PI", "Piauí" }, { "RJ", "Rio de Janeiro" },
			{ "RN", "Rio Grande do Norte" }, { "RS", "Rio Grande do Sul" }, { "RO", "Rondônia" }, { "RR", "Roraima" },
			{ "SC", "Santa Catarina" }, { "SP", "São Paulo" }, { "SE", "Sergipe" }, { "TO", "Tocantins" } };

	// Attributes
	private String state = null;
	private String initials = null;

	/**
	 * Constructor method
	 * @param initials String with 2 characters with the initials of state
	 * @throws UfException Invalid initials or initials not exists
	 */
	public UF(final String initials) throws UfException {
		setState(initials);
	}

	/**
	 * Method to set state and initials with the parameter initials
	 * @param initials String with 2 characters with the initials of state
	 * @throws UfException Invalid initials or initials not exists
	 */
	private void setState(String initials) throws UfException {
		if(initials.length() == 2)
		{
			boolean inArray = false;
			int index = -1;
	
			initials = initials.toUpperCase();
			for (int i = 0; i < UF.STATES.length; i++) {
				if (UF.STATES[i][0].equals(initials)) {
					inArray = true;
					index = i;
					break;
				} else {
					// Nothig to do.
				}
			}
			if (inArray) {
				this.initials = UF.STATES[index][0];
				this.state = UF.STATES[index][1];
			} else {
				throw new UfException(UF.NOT_EXISTS_STATE);
			}
		} else{
			throw new UfException(UF.INVALID_STATE);
		}
	}

	public String getInitials() {
		return initials;
	}

	public String getState() {
		return state;
	}
	
	@Override
	public String toString() {
		return getInitials();
	}
}
