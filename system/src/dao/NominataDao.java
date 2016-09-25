package dao;

import java.sql.SQLException;
import java.util.Calendar;

import model.Nominata;
import model.Office;

public class NominataDao extends Dao {

	private Nominata nominata;

	public NominataDao(Nominata nominata) {
		super();
		setNominata(nominata);
	}

	public void register() throws SQLException {
		final int semester = Calendar.getInstance().get(Calendar.MONTH) / 7 + 1;
		final int semesterYear = (semester == 1) ? 1 + Calendar.YEAR : Calendar.YEAR;

		final String nominataQuery = "INSERT INTO NOMINATA(code,year,semester) VALUES(NULL," + semesterYear + ",'" + semester
				+ "')";
		final int nominataCode = lastId(nominataQuery);

		for (Office office : getNominata().getNominataList()) {
			
			if(office.getMember() != null){
				final String officeQuery = "INSERT INTO belongs VALUES('" + office.getOffice() + "'," + 
						nominataCode + " ," + office.getMember().getId() + ")";
	
				Dao.executeUpdate(officeQuery);
			} else {
				// Nothing to do
			}
		}
	}

	public Nominata getNominata() {
		return nominata;
	}

	private void setNominata(Nominata nominata) {
		this.nominata = nominata;
	}

}
