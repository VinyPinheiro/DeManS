/*****************************
 * Class name: MemberDao (.java)
 * 
 * Purpose: Class to persist member data.
 *****************************/

package dao;

import exception.DaoException;
import model.Member;

public class MemberDao extends Dao {

	private static final String CLASS_NAME = "MemberDao";
	private static final String NULL_MEMBER = "Erro, objeto de Membro não pode ser nullo";
	private Member member;
	
	public MemberDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 * @throws DaoException if member receive null parameter
	 */
	public void setMember(Member member) throws DaoException {
		if(member != null){
			this.member = member;
		} else{
			throw new DaoException(MemberDao.NULL_MEMBER, MemberDao.CLASS_NAME);
		}
	}

}
