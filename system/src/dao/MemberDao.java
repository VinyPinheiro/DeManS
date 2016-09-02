/*****************************
 * Class name: MemberDao (.java)
 * 
 * Purpose: Class to persist member data.
 *****************************/

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.AddressException;
import exception.DaoException;
import exception.MemberException;
import exception.UfException;
import model.Member;
import model.UF;
import model.Address;;

public class MemberDao extends Dao {

	private static final String CLASS_NAME = "MemberDao";
	private static final String NULL_MEMBER = "Erro, objeto de Membro não pode ser nullo.";
	private static final String EXISTS_ID = "Impossível registrar, id já cadastrada.";
	private Member member;

	public MemberDao(Member member) throws DaoException {
		setMember(member);
	}

	/**
	 * 
	 * @param id
	 * @return null if not find else member object
	 * @throws SQLException
	 * @throws AddressException
	 * @throws UfException
	 * @throws MemberException
	 */
	public static Member findById(Integer id) throws SQLException, AddressException, UfException, MemberException {
		final String query = "SELECT MEMBER.id, MEMBER.name, MEMBER.birthdate, MEMBER.password, "
				+ "MEMBER.phone, MEMBER.dad_phone, ADDRESS.street, ADDRESS.number, ADDRESS.complement, "
				+ "ADDRESS.zipcode, CITY.name, CITY.initials FROM "
				+ "MEMBER INNER JOIN ADDRESS ON MEMBER.address_code = ADDRESS.code " + "WHERE MEMBER.id = " + id + "";
		ResultSet data = Dao.executeQuery(query);

		Member member = null;
		if (data.next()) {
			UF uf = new UF(data.getString("initials"));
			Address address = new Address(data.getString("street"), data.getInt("number"), data.getString("complement"),
					data.getString("zipcode"), data.getString("city"), uf);
			member = new Member(id, data.getString("name"), data.getDate("birthdate"), data.getString("password"),
					data.getString("phone"), data.getString("dad_phone"), address);
		} else {
			member = null;
		}
		return member;
	}

	/**
	 * Method to verify if UF exists, if not exists then register that
	 * 
	 * @param initials
	 *            String with 2 characters with the initials of state
	 * @throws UfException
	 * @throws SQLException
	 */
	private void registerIfUfExists(String initial) throws UfException, SQLException {
		final UF uf = new UF(initial);
		final String query = "SELECT COUNT(*) FROM UF WHERE initial = '" + initial + "'";

		final ResultSet resultset = Dao.executeQuery(query);

		if (!resultset.next()) {
			registerUf(uf);
		} else {
			// Nothing to do.
		}
	}

	/**
	 * Method to persist UF
	 * 
	 * @param uf
	 *            UF object
	 * @throws SQLException
	 */
	private void registerUf(UF uf) throws SQLException {
		final String queryUF = "INSERT INTO UF('" + getMember().getAddress().getUf().getInitials() + "','"
				+ getMember().getAddress().getUf().getState() + "')";
		Dao.executeQuery(queryUF);
	}

	/**
	 * Method to verify if City exists, if not exists then register that
	 * @param address Address object 
	 * @return integer code of the city
	 * @throws UfException
	 * @throws SQLException
	 */
	private Integer registerIfCityExists(Address address) throws UfException, SQLException {
		final String query = "SELECT code FROM CITY WHERE name = '" + address.getCity() + "'";

		final ResultSet resultset = Dao.executeQuery(query);

		int code = -1;

		if (resultset.next()) {
			code = resultset.getInt("code");
		} else {
			code = registerCity(address);
		}

		return code;
	}

	/**
	 * Method to persist a city
	 * @param address Address Object
	 * @return integer code of the city
	 * @throws NumberFormatException not return the last id
	 * @throws SQLException
	 */
	private int registerCity(Address address) throws NumberFormatException, SQLException {
		final String query = "INSERT INTO CITY VALUES(NULL, '" + address.getCity() + "','"
				+ address.getUf().getInitials() + "')";
		
		final ResultSet resultset = Dao.executeQuery(query);
		
		
		return Integer.parseInt(resultset.getString("last_insert_id()"));
	}

	/**
	 * Method to register a Member
	 * @throws SQLException
	 * @throws AddressException
	 * @throws MemberException
	 * @throws DaoException
	 * @throws UfException
	 */
	public void register() throws SQLException, AddressException, MemberException, DaoException, UfException {
		if (MemberDao.findById(getMember().getId()) != null) {
			registerIfUfExists(getMember().getAddress().getUf().getInitials());

			int cityCode = registerIfCityExists(getMember().getAddress());

		} else {
			throw new DaoException(MemberDao.EXISTS_ID, MemberDao.CLASS_NAME);
		}
	}

	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param member
	 *            the member to set
	 * @throws DaoException
	 *             if member receive null parameter
	 */
	private void setMember(Member member) throws DaoException {
		if (member != null) {
			this.member = member;
		} else {
			throw new DaoException(MemberDao.NULL_MEMBER, MemberDao.CLASS_NAME);
		}
	}

}
