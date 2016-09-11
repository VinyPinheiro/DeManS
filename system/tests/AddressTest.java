import exception.AddressException;
import exception.UfException;
import junit.framework.TestCase;
import model.Address;
import model.UF;

public class AddressTest extends TestCase {
	
	private Address address;
	private UF uf;
	private String street;
	private Integer number;
	private String complement;
	private String zipcode;
	private String city;
	
	protected void setUp() throws UfException
	{
		street = "Rua dos alveneiros";
		number = 25;
		complement = "A2065";
		zipcode = "11520-000";
		city = "Santos";
		uf = new UF(UF.STATES[2][0]);
	}
	
	public void testValidAddress() throws AddressException {
		address = new Address(street, number, complement, zipcode, city, uf);
		assertEquals(address.getStreet(), street);
		assertEquals(address.getNumber(), number);
		assertEquals(address.getComplement(), complement);
		assertEquals(address.getZipcode(), zipcode);
		assertEquals(address.getCity(), city);
		assertEquals(address.getUf(), uf);
	}
	
	public void testValidAddressWithNullComplement() throws AddressException {
		address = new Address(street, number, null, zipcode, city, uf);
		assertEquals(address.getStreet(), street);
		assertEquals(address.getNumber(), number);
		assertEquals(address.getComplement(), null);
		assertEquals(address.getZipcode(), zipcode);
		assertEquals(address.getCity(), city);
		assertEquals(address.getUf(), uf);
	}
	
	public void testNullStreet(){
		try {
			address = new Address(null, number, complement, zipcode, city, uf);
			fail();
		} catch (AddressException e) {
			
		}
	}
	
	public void testLargerStreetString(){
		try {
			address = new Address(street + street + street + street + street + street +
					 street + street + street + street + street + street + street + street
					 , number, complement, zipcode, city, uf);
			fail();
		} catch (AddressException e) {
			
		}
	}
	

	public void testNullNumber(){
		try {
			address = new Address(street, null, complement, zipcode, city, uf);
			fail();
		} catch (AddressException e) {
			
		}
	}
	
	public void testNegativeNumber(){
		try {
			address = new Address(street, -52, complement, zipcode, city, uf);
			fail();
		} catch (AddressException e) {
			
		}
	}

	
	public void testLargerComplement(){
		try {
			address = new Address(street, number, complement + complement + complement + complement +
					 complement + complement + complement + complement + complement + complement + "1",
					 zipcode, city, uf);
			fail();
		} catch (AddressException e) {
			
		}
	}

	public void testNullZipcode(){
		try {
			address = new Address(street, number, complement, null, city, uf);
			fail();
		} catch (AddressException e) {
			
		}
	}
	
	public void testWrongZipcode(){
		try {
			address = new Address(street, number, complement, "08520-448", city, uf);
			fail();
			address = new Address(street, number, complement, "1520-448", city, uf);
			fail();
		} catch (AddressException e) {
			
		}
	}

	public void testNullCity(){
		try {
			address = new Address(street, number, complement, zipcode, null, uf);
			fail();
		} catch (AddressException e) {
			
		}
	}
	
	public void testLargeCityString(){
		try {
			address = new Address(street, number, complement, zipcode, 
					"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
					uf);
			fail();
		} catch (AddressException e) {
			
		}
	}


	public void testNullUf(){
		try {
			address = new Address(street, number, complement, zipcode, city, null);
			fail();
		} catch (AddressException e) {
			
		}
	}
}
