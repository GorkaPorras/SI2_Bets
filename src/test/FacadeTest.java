package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

public class FacadeTest {
	static BLFacadeImplementation sut;
	static TestFacadeImplementation testBL;

	private Event ev;

	@BeforeClass
	public static void setUpClass() {
		// sut= new BLFacadeImplementation();

		// you can parametrize the DataAccess used by BLFacadeImplementation
		DataAccess da = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		sut = new BLFacadeImplementation(da);

		testBL = new TestFacadeImplementation();
	}

	@Test
	// sut.createQuestion: The event has one question with a queryText.
	public void test1() {
		try {

			// define paramaters
			String queryText = "proba galdera";
			Float betMinimum = new Float(2);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// configure the state of the system (create object in the dabatase)
			ev = testBL.addEvent(queryText, oneDate);
			sut.createQuestion(ev, queryText, betMinimum);

			// invoke System Under Test (sut)
			sut.createQuestion(ev, queryText, betMinimum);

			// if the program continues fail
			fail();
		} catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			// if the program goes to this point OK
			assertTrue(true);
		} catch (EventFinished e) {
			// if the program goes to this point fail
			fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeEvent(ev);
			System.out.println("Finally " + b);
		}
	}

	@Test
	// sut.createQuestion: The event has NOT one question with a queryText.
	public void test2() {
		try {

			// define paramaters
			String queryText = "proba galdera";
			Float betMinimum = new Float(2);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// configure the state of the system (create object in the dabatase)
			ev = testBL.addEvent(queryText, oneDate);

			// invoke System Under Test (sut)
			Question q = sut.createQuestion(ev, queryText, betMinimum);

			// verify the results
			assertTrue(q != null);
			assertEquals(q.getQuestion(), queryText);
			assertEquals(q.getBetMinimum(), betMinimum, 0);

		} catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail
			fail();
		} catch (EventFinished e) {
			// if the program goes to this point fail
			fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeEvent(ev);
			System.out.println("Finally " + b);
		}
	}

	@Test
	// Usuario berdina da, ez da erregistratuko eta false itzuliko du
	public void registerTest0() {
		try {
			boolean result, expectedvalue;
			expectedvalue = false;

			result = sut.Register(false, "aa", "aa", "aa", "Mikel", "Mikel", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa", "aa");
			System.out.println(result);
			System.out.println(result);
			assertTrue(result == expectedvalue);
		} catch (Exception e) {
			System.out.println(e);
			fail();
		} finally {
			testBL.removeUser(testBL.getUser("Mikel"));
			System.out.println("Finally ");

		}

	}

	 @Test
	// Usuario desberdina da, langilea false, erregistratuko da eta true itzuliko du
	public void registerTest1() {
		try {
			boolean result, expectedvalue;
			expectedvalue = true;

			// testBL.register(u);
			result = sut.Register(false, "aa", "aa", "aa", "mikel", "mikel", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa", "aa");
			assertTrue(result == expectedvalue);
		} catch (Exception e) {
			System.out.println(e);
			fail();
		} finally {
			testBL.removeUser(testBL.getUser("mikel"));
			System.out.println("Finally ");

		}

	}
	 @Test
		// Usuario desberdina da, langilea true, erregistratuko da eta true itzuliko du
		public void registerTest2() {
			try {
				boolean result, expectedvalue;
				expectedvalue = true;

				// testBL.register(u);
				result = sut.Register(true, "aa", "aa", "aa", "mikel", "mikel", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
						"aa", "aa");
				assertTrue(result == expectedvalue);
			} catch (Exception e) {
				System.out.println(e);
				fail();
			} finally {
				testBL.removeUser(testBL.getUser("mikel"));
				System.out.println("Finally ");

			}

		}

	

	@Test
	// izena = null
	public void registerTest3() {
		try {
			sut.Register(false, null, "aa", "aa", "Grka", "123", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa");
			fail();

		} catch (Exception e) {
			System.out.println(e + "aaaaaaa");
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("grka"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// ab1 = null
	public void registerTest4() {
		try {
			sut.Register(false, "aa", null, "aa", "Gorka", "1234", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("Gorka"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// ab2 = null
	public void registerTest5() {
		try {
			sut.Register(false, "aa", "aa", null, "Gorka", "1234", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("Gorka"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// erabiz = null
	public void registerTest6() {
		try {
			sut.Register(false, "aa", "aa", "Gorka", null, "1234", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		} finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser(null));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}

		}
	}

	@Test
	// pass = null
	public void registerTest7() {
		try {
			sut.Register(false, "aa", "aa", "Gorka", "1234", null, "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("1234"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// NAN = null
	public void registerTest8() {
		try {
			sut.Register(false, "aa", "aa", "Gorka", "1234", "aa", null, "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		} finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("1234"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// jd = null
	public void registerTest9() {
		try {
			sut.Register(false, "aa", "aa", "Gorka", "1234", "aa", null, "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("1234"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// email = null
	public void registerTest10() {
		try {
			sut.Register(false, "aa", "aa", "Gorka", "1234", "aa", null, "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("1234"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// tlf = null
	public void registerTest11() {
		try {
			sut.Register(false, "iz", "ab1", "ab2", "erabiz", "pass", "NAN", "jd", "email", null, "helb", "pstkod",
					"hrld", "prob", "herria");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("erabiz"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// helb = null
	public void registerTest12() {
		try {
			sut.Register(false, "iz", "ab1", "ab2", "erabiz", "pass", "NAN", "jd", "email", "tlf", null, "pstkod",
					"hrld", "prob", "herria");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("erabiz"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// pstkod = null
	public void registerTest13() {
		try {
			sut.Register(false, "iz", "ab1", "ab2", "erabiz", "pass", "NAN", "jd", "email", "tlf", "helb", null, "hrld",
					"prob", "herria");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("erabiz"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// hrld = null
	public void registerTest14() {
		try {
			sut.Register(false, "iz", "ab1", "ab2", "erabiz", "pass", "NAN", "jd", "email", "tlf", "helb", "pstkod",
					null, "prob", "herria");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("erabiz"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// prob = null
	public void registerTest15() {
		try {
			sut.Register(false, "iz", "ab1", "ab2", "erabiz", "pass", "NAN", "jd", "email", "tlf", "helb", "pstkod",
					"hrld", null, "herria");
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("erabiz"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// herria = null
	public void registerTest16() {
		try {
			sut.Register(false, "iz", "ab1", "ab2", "erabiz", "pass", "NAN", "jd", "email", "tlf", "helb", "pstkod",
					"hrld", "prob", null);
			fail();
		} catch (Exception e) {
			System.out.println(e);
			assertTrue(true);
		}finally {
			try {
				boolean b = testBL.removeUser(testBL.getUser("erabiz"));
				System.out.println("Finally " + b);
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}
	@Test
	// erabiltzailea datubasean dago
	public void isRegisterText0() {
		User u = new User("aa", "aa", "aa", "ibon", "777", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa");
		try {
			testBL.register(u);
			boolean expectedvalue = true;

			boolean result = sut.isRegister("ibon", "777");
			assertTrue(expectedvalue == result);

		} catch (Exception e) {
			fail();

		} finally {
			testBL.removeUser(u);
		}
	}

	@Test
	// erabiltzailea ez dago datubasean
	public void isRegisterText1() {
		User u = new User("aa", "aa", "aa", "ibon", "777", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa");
		try {
			testBL.register(u);
			boolean expectedvalue = false;

			boolean result = sut.isRegister("Gorka", "1234");
			assertTrue(expectedvalue == result);

		} catch (Exception e) {
			fail();

		} finally {
			testBL.removeUser(u);
		}
	}

	@Test
	// erabiltzailea null da
	public void isRegisterText2() {
		User u = new User("aa", "aa", "aa", "ibon", "777", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa");
		try {
			testBL.register(u);

			sut.isRegister(null, "1234");

			fail();

		} catch (Exception e) {
			assertTrue(true);

		} finally {
			testBL.removeUser(u);
		}
	}
	@Test
	// pasahitza null da
	public void isRegisterText3() {
		User u = new User("aa", "aa", "aa", "ibon", "777", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa");
		try {
			testBL.register(u);

			sut.isRegister("ibon", null);

			fail();

		} catch (Exception e) {
			assertTrue(true);

		} finally {
			testBL.removeUser(u);
		}
	}

}
