package model;

import java.util.Vector;
import exception.NominataException;

public class Nominata {

	private Integer id;
	private Vector<Office> nominata;
	private Integer semester;
	private Integer year;

	private static final String REPEATED_POSITION = "Esse cargo já foi preenchido nesta nominata.";
	private static final String INVALID_ID = "Id não pode ser negativo.";

	public Nominata() throws NominataException {
		nominata = new Vector<Office>();
		setId(null);
	}

	/**
	 * 
	 * @param id
	 *            integer, only positive numbers
	 * @throws NominataException
	 */
	public Nominata(int id) throws NominataException {
		nominata = new Vector<Office>();
		setId(id);
	}
	
	/**
	 * 
	 * @param id
	 *            integer, only positive numbers
	 * @throws NominataException
	 */
	public Nominata(int id, int semester, int year) throws NominataException {
		nominata = new Vector<Office>();
		setId(id);
		setSemester(semester);
		setYear(year);
	}

	public void addOffice(Office office) throws NominataException {
		// Verify if office is repeated
		for (Office members : nominata) {
			if (members.getOffice().equals(office.getOffice())) {
				throw new NominataException(REPEATED_POSITION);
			}
		}

		nominata.addElement(office);
	}

	public int getId() {
		return id;
	}

	public Vector<Office> getNominataList() {
		return nominata;
	}

	private void setId(Integer id) throws NominataException {
		if (id != null) {
			if (id > 0) {
				this.id = id;
			} else {
				throw new NominataException(INVALID_ID);
			}
		} else {
			this.id = id;
		}
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
