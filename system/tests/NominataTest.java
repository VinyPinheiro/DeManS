import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import exception.AddressException;
import exception.MemberException;
import exception.NominataException;
import exception.OfficeException;
import exception.UfException;
import junit.framework.TestCase;
import model.Address;
import model.Member;
import model.Nominata;
import model.Office;
import model.UF;

public class NominataTest extends TestCase {
	private Vector<Office> offices;
	private Member member1;
	private Member member2;
	private Member member3;
	private Member member4;

	public void setUp() throws MemberException, ParseException, AddressException, UfException {
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

		member1 = new Member(id, name, birthdate, password, phone, dad_phone, address);
		member2 = new Member(id + 1, name, birthdate, password, phone, dad_phone, address);
		member3 = new Member(id + 2, name, birthdate, password, phone, dad_phone, address);
		member4 = new Member(id + 3, name, birthdate, password, phone, dad_phone, address);

	}

	public void testCreateValidNominata() throws NominataException, OfficeException {
		Nominata nominata = new Nominata();
		int i = 0;
		nominata.addOffice(new Office(member1, Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[i++]));
		nominata.addOffice(new Office(member2, Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[i++]));
		nominata.addOffice(new Office(member3, Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[i++]));
		nominata.addOffice(new Office(member4, Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[i]));
	}

	public void testCreateNullMC() throws NominataException, OfficeException {
		try {
			Nominata nominata = new Nominata();
			nominata.addOffice(new Office(Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[0]));
			fail();
		} catch (Exception e) {
		}
	}

	public void testCreateNull1C() throws NominataException, OfficeException {
		try {
			Nominata nominata = new Nominata();
			nominata.addOffice(new Office(Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[1]));
			fail();
		} catch (Exception e) {
		}
	}
	
	public void testCreateNull2C() throws NominataException, OfficeException {
		try {
			Nominata nominata = new Nominata();
			int i = 0;
			nominata.addOffice(new Office(member1, Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[i++]));
			nominata.addOffice(new Office(member1, Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS[i]));
			fail();
		} catch (Exception e) {
		}
	}
}
