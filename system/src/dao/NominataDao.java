package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import exception.AddressException;
import exception.MemberException;
import exception.NominataException;
import exception.OfficeException;
import exception.UfException;
import model.Nominata;
import model.Office;

public class NominataDao extends Dao {

	private Nominata nominata;

	public NominataDao(Nominata nominata) {
		super();
		setNominata(nominata);
	}

	public static Nominata findNominata(int code)
			throws SQLException, NominataException, OfficeException, AddressException, UfException, MemberException {
		final String query = "SELECT NOMINATA.code,NOMINATA.semester, NOMINATA.year " + "FROM NOMINATA WHERE code = "
				+ code;

		Nominata nominata = null;

		ResultSet data = Dao.executeQuery(query);
		while (data.next()) {
			int id = data.getInt("code");
			int year = data.getInt("year");
			int semester = data.getInt("semester");

			nominata = new Nominata(id, semester, year);

			final String secundaryQuery = "SELECT belongs.office, belongs.id FROM belongs WHERE code = " + id;

			ResultSet secundaryData = Dao.executeQuery(secundaryQuery);

			while (secundaryData.next()) {
				nominata.addOffice(
						new Office(MemberDao.findById(secundaryData.getInt("id")), secundaryData.getString("office")));
			}
		}

		return nominata;
	}
	
	public static String findNominataBySemester(int User, int desiredSemester, int desiredYear)
			throws SQLException, NominataException, OfficeException, AddressException, UfException, MemberException {
		final String query = "SELECT NOMINATA.code, NOMINATA.semester, NOMINATA.year " + "FROM NOMINATA WHERE NOMINATA.semester = "+
			desiredSemester+" and NOMINATA.year = " + desiredYear;
		System.out.println("User ="+User);
		String cargo = "";
		ResultSet data = Dao.executeQuery(query);
		while (data.next()) {
			int code = data.getInt("code");
			
			final String secundaryQuery = "SELECT belongs.office, belongs.id FROM belongs WHERE code = " + code;

			ResultSet secundaryData = Dao.executeQuery(secundaryQuery);
			int idMember = MemberDao.findById(secundaryData.getInt("id")).getId();
			System.out.println("Membro"+idMember);
			if(User == idMember)
				cargo = secundaryData.getString("office");
			
		}

		return cargo;
	}

	public static Vector<Nominata> returnAllNominatas()
			throws SQLException, NominataException, OfficeException, AddressException, UfException, MemberException {
		final String query = "SELECT NOMINATA.code,NOMINATA.semester, NOMINATA.year FROM NOMINATA";

		Vector<Nominata> nominatas = new Vector<Nominata>();

		ResultSet data = Dao.executeQuery(query);

		while (data.next()) {
			int id = data.getInt("code");
			int year = data.getInt("year");
			int semester = data.getInt("semester");
			System.out.println(id + " " + year + " " + semester);
			Nominata aux = new Nominata(id, semester, year);

			final String secundaryQuery = "SELECT belongs.office, belongs.id FROM belongs WHERE code = " + id;

			ResultSet secundaryData = Dao.executeQuery(secundaryQuery);

			while (secundaryData.next()) {
				aux.addOffice(
						new Office(MemberDao.findById(secundaryData.getInt("id")), secundaryData.getString("office")));
			}

			nominatas.add(aux);
		}
		return nominatas;
	}

	public void update(Nominata newNominata) throws SQLException {
		try {
			final String nominataQuery = "DELETE FROM belongs WHERE code = " + this.getNominata().getId();
			Dao.executeUpdate(nominataQuery);
			this.auxUpdate(newNominata);
		} catch (Exception e) {
			this.auxUpdate(getNominata());
		}
	}

	public void register() throws SQLException {
		final int semester = (Calendar.getInstance().get(Calendar.MONTH) < 6) ? 2 : 1;
		final int semesterYear = (semester == 1) ? (Calendar.getInstance().get(Calendar.YEAR) + 1)
				: (Calendar.getInstance().get(Calendar.YEAR));

		System.out.println(semester);
		System.out.println(semesterYear);

		final String nominataQuery = "INSERT INTO NOMINATA(code,year,semester) VALUES(NULL," + semesterYear + ",'"
				+ semester + "')";
		final int nominataCode = lastId(nominataQuery);

		try {
			for (Office office : getNominata().getNominataList()) {

				if (office.getMember() != null) {
					final String officeQuery = "INSERT INTO belongs VALUES('" + office.getOffice() + "'," + nominataCode
							+ " ," + office.getMember().getId() + ")";

					Dao.executeUpdate(officeQuery);
				} else {
					// Nothing to do
				}
			}

		} catch (SQLException e) {
			final String rollback = "DELETE FROM NOMINATA WHERE code = " + nominataCode;
			Dao.executeUpdate(rollback);
			throw e;
		}
	}

	private void auxUpdate(Nominata nominata) throws SQLException {
		String officeQuery = "INSERT INTO belongs VALUES";
		int i = 0;
		while (i < nominata.getNominataList().size()) {
			Office office = nominata.getNominataList().get(i);
			if (office.getMember() != null) {
				if (i != 0)
					officeQuery += ",";
				officeQuery += "('" + office.getOffice() + "'," + this.getNominata().getId() + " ,"
						+ office.getMember().getId() + ")";

			} else {
				// Nothing to do
			}
			i++;
		}
		System.out.println(officeQuery);
		Dao.executeUpdate(officeQuery);
	}

	public Nominata getNominata() {
		return nominata;
	}

	private void setNominata(Nominata nominata) {
		this.nominata = nominata;
	}

}
