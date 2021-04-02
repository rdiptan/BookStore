package bookStore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddBookTest {

	@Test
	void test() {
		AddBook ltest = new AddBook();
		int result = ltest.newBooks("BookName", "Publisher", "2020", "1000", "1");
		assertEquals(1, result);
	}

}
