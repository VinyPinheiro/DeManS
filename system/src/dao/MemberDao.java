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
	private static final String NULL_MEMBER = "Erro, objeto de Membro não pode ser nullo";
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
