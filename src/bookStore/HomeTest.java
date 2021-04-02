package bookStore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HomeTest {

	@Test
	void test() {
		Login ltest = new Login();
		int ao = ltest.userLogin("diptan", "regmi321");
		assertEquals(2, ao);
	}

}
