package bookStore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegistrationTest {

	@Test
	void test() {
		Registration ltest = new Registration();
		int result = ltest.userSignup("admin", "admin", "admin", "admin");
		assertEquals(1, result);
	}

}
