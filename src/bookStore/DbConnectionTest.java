package bookStore;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Statement;
import org.junit.jupiter.api.Test;

class DbConnectionTest {

	@Test
	void test() {
		DbConnection db = new DbConnection();
		Statement ao = db.connection();
		assertEquals(null, ao);
	}

}
