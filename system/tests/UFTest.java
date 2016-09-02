import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exception.UfException;
import junit.framework.TestCase;
import model.UF;

public class UFTest extends TestCase {

	private UF uf;

	public void testWithInvalidInitial() throws UfException {
		try {
			uf = new UF("AA");
			assert false;
		} catch (UfException e) {
			assert true;
		}catch (Exception e) {
			assert false;
		}
	}
	
	public void testWithValidInitial() throws UfException {
		uf = new UF(UF.STATES[12][0]);
	}
	
	public void testVerifyReturn() throws UfException {
		uf = new UF(UF.STATES[12][0]);
		assert uf.getState().equals(UF.STATES[12][1]);
	}

	public void testWithLowerCasel() throws UfException {
		uf = new UF(UF.STATES[12][0].toLowerCase());
	}
}
