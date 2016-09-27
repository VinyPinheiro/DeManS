import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import exception.AddressException;
import exception.MemberException;
import exception.UfException;
import junit.framework.TestCase;
import model.Address;
import model.Member;
import model.UF;

public class MemberTest extends TestCase {

	private Member member;
	private Integer id;
	private String name;
	private Date birthdate;
	private String password;
	private String phone;
	private String dad_phone;
	private Address address;
	private String degree;
	private String situation;

	protected void setUp() throws UfException, AddressException, ParseException {
		id = 44199;
		name = "Vinicius Pinheiro da Silva Correa";
		birthdate = new SimpleDateFormat("dd/MM/yyyy").parse("14/02/1995");
		password = "1234567";
		phone = "(61)98145-8085";
		dad_phone = "(61)98145-8085";
		degree = "DeMolay";
		situation = "Ativo";

		String street = "Rua dos alveneiros";
		Integer number = 25;
		String complement = "A2065";
		String zipcode = "11520-000";
		String city = "Santos";
		UF uf = new UF(UF.STATES[2][0]);

		address = new Address(street, number, complement, zipcode, city, uf);
	}

	public void testCreateMember() throws MemberException {
		member = new Member(id, name, birthdate, password, phone, dad_phone, address);

		assertEquals(member.getId(), id);
		assertEquals(member.getName(), name);
		assertEquals(member.getBirthdate(), birthdate);
		assertEquals(member.getPassword(), password);
		assertEquals(member.getPhone(), phone);
		assertEquals(member.getDad_phone(), dad_phone);
		assertEquals(member.getAddress(), address);
	}
	

	public void testCreateMemberWithDegreeAndSituation() throws MemberException {
		member = new Member(id, name, birthdate, password, phone, dad_phone, address,degree,situation);

		assertEquals(member.getId(), id);
		assertEquals(member.getName(), name);
		assertEquals(member.getBirthdate(), birthdate);
		assertEquals(member.getPassword(), password);
		assertEquals(member.getPhone(), phone);
		assertEquals(member.getDad_phone(), dad_phone);
		assertEquals(member.getAddress(), address);
		assertEquals(member.getDegree(), degree);
		assertEquals(member.getSituation(), situation);
	}

	public void testNullID() {
		try {
			member = new Member(null, name, birthdate, password, phone, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testNegativeID() {
		try {
			member = new Member(-615, name, birthdate, password, phone, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testNullName() {
		try {
			member = new Member(id, null, birthdate, password, phone, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testLargeName() {
		try {
			member = new Member(id,
					"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
					birthdate, password, phone, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testNullBirthdate() {
		try {
			member = new Member(id, name, null, password, phone, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testInvaidBirthdate() {
		try {
			member = new Member(id, name, Calendar.getInstance().getTime(), password, phone, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testNullPassword() {
		try {
			member = new Member(id, name, birthdate, null, phone, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testPasswordLess6Characters() {
		try {
			member = new Member(id, name, birthdate, "12", phone, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testPasswordMore20Characters() {
		try {
			member = new Member(id, name, birthdate, "012345678901234567891", phone, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testNullPhone() {
		try {
			member = new Member(id, name, birthdate, password, null, dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testInvalidPhone() {
		try {
			member = new Member(id, name, birthdate, password, "(13) 988452000", dad_phone, address);
			fail();
		} catch (MemberException e) {
		}
	}
	
	public void testNullDadPhone() {
		try {
			member = new Member(id, name, birthdate, password, phone, null, address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testInvalidDadPhone() {
		try {
			member = new Member(id, name, birthdate, password,  phone,"(13) 988452000", address);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testNullAddress() {
		try {
			member = new Member(id, name, birthdate, password, phone, dad_phone, null);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testInvalidDegree() {
		try {
			member = new Member(id, name, birthdate, password, phone, dad_phone, address,"ciático",situation);
			fail();
		} catch (MemberException e) {
		}
	}

	public void testInvalidsituation() {
		try {
			member = new Member(id, name, birthdate, password, phone, dad_phone, address,degree,"reg");
			fail();
		} catch (MemberException e) {
		}
	}
}
