import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exception.AddressException;
import exception.MemberException;
import exception.OfficeException;
import exception.UfException;
import junit.framework.TestCase;
import model.Address;
import model.Member;
import model.Office;
import model.UF;

public class OfficeTest extends TestCase {

	private Member member;
	
	public void setUp() throws UfException, ParseException, AddressException, MemberException{
		int id = 44199;
		String name = "Vinicius Pinheiro da Silva Correa";
		Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse("14/02/1995");
		String password = "1234567";
		String phone = "(61)98145-8085";
		String dad_phone = "(61)98145-8085";

		String street = "Rua dos alveneiros";
		Integer number = 25;
		String complement = "A2065";
		String zipcode = "11520-000";
		String city = "Santos";
		UF uf = new UF(UF.STATES[2][0]);

		Address address = new Address(street, number, complement, zipcode, city, uf);
		
		member = new Member(id, name, birthdate, password, phone, dad_phone, address);
	}
	
	public void testCreateObject() throws OfficeException{
		Office office = new Office(member, Office.VALID_OFFICES[0]);
		assert (office.getOffice().equals(Office.VALID_OFFICES[0]));
		assert (office.getMember().equals(member));
	}
	
	public void testCreateObjectWithNullMember() throws OfficeException{
		Office office = new Office(null, Office.VALID_OFFICES[5]);
		assert (office.getOffice().equals(Office.VALID_OFFICES[5]));
		assert (office.getMember() == null);
	}

	public void testCreateWithNullMC(){
		try {
			new Office(null, Office.VALID_OFFICES[0]);
			fail();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void testCreateWithNull1C(){
		try {
			new Office(null, Office.VALID_OFFICES[1]);
			fail();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void testCreateWithNull2C(){
		try {
			new Office(null, Office.VALID_OFFICES[2]);
			fail();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
