package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FacadeMockTest {
	DataAccess dataAccess = Mockito.mock(DataAccess.class);
	Event mockedEvent = Mockito.mock(Event.class);
	User mockedUser = Mockito.mock(User.class);
	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);

	// sut.createQuestion: The event has one question with a queryText.

	@Test
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

			// configure Mock
			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			Mockito.when(dataAccess.createQuestion(Mockito.any(Event.class), Mockito.any(String.class),
					Mockito.any(Integer.class))).thenThrow(QuestionAlreadyExist.class);

			// invoke System Under Test (sut)
			sut.createQuestion(mockedEvent, queryText, betMinimum);

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
		}
	}

	@Test
	// sut.createQuestion: The event has NOT a question with a queryText.
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

			// configure Mock
			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			Mockito.doReturn(new Question(queryText, betMinimum, mockedEvent)).when(dataAccess)
					.createQuestion(Mockito.any(Event.class), Mockito.any(String.class), Mockito.any(Integer.class));

			// invoke System Under Test (sut)
			Question q = sut.createQuestion(mockedEvent, queryText, betMinimum);

			// verify the results
			// Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class),
			// Mockito.any(Integer.class));

			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			ArgumentCaptor<String> questionStringCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Float> betMinimunCaptor = ArgumentCaptor.forClass(Float.class);

			Mockito.verify(dataAccess, Mockito.times(1)).createQuestion(eventCaptor.capture(),
					questionStringCaptor.capture(), betMinimunCaptor.capture());
			Float f = betMinimunCaptor.getValue();

			assertEquals(eventCaptor.getValue(), mockedEvent);
			assertEquals(questionStringCaptor.getValue(), queryText);
			assertEquals(betMinimunCaptor.getValue(), betMinimum);

		} catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			assertTrue(true);
		} catch (EventFinished e) {
			fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void registerTest0() {

		try {
			boolean result, expectedvalue;
			expectedvalue = false;

			// testBL.register(u);
			// configure Mock
			// User u = new User("iz", "ab1", "ab2", "erabiz", "pass", "NAN", "jd", "email",
			// "tlf", "helb", "pstkod", "hrld", "prob", "herria");
			Mockito.doReturn(false).when(dataAccess).Register(Mockito.anyBoolean(), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class));

			result = sut.Register(false, "aa", "aa", "aa", "Prueba", "Prueba", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa", "aa");
			assertTrue(result == expectedvalue);
		} catch (Exception e) {
			System.out.println(e + "AAAAAAAAAAA");
			fail();
		} finally {
			// boolean b = testBL.removeUser(u);
			System.out.println("Finally ");

		}
	}

	@Test
	public void registerTest1() {

		try {
			boolean result, expectedvalue;
			expectedvalue = true;

			// testBL.register(u);
			// configure Mock
			// User u = new User("iz", "ab1", "ab2", "erabiz", "pass", "NAN", "jd", "email",
			// "tlf", "helb", "pstkod", "hrld", "prob", "herria");
			Mockito.doReturn(true).when(dataAccess).Register(Mockito.anyBoolean(), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class));

			result = sut.Register(false, "aa", "aa", "aa", "Prueba", "Prueba", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa", "aa");
			assertTrue(result == expectedvalue);
		} catch (Exception e) {
			System.out.println(e + "AAAAAAAAAAA");
			fail();
		} finally {
			// boolean b = testBL.removeUser(u);
			System.out.println("Finally ");

		}
	}

	@Test
	public void registerTest2() {

		try {
			boolean result, expectedvalue;
			expectedvalue = true;

			// testBL.register(u);
			// configure Mock
			// User u = new User("iz", "ab1", "ab2", "erabiz", "pass", "NAN", "jd", "email",
			// "tlf", "helb", "pstkod", "hrld", "prob", "herria");
			Mockito.doReturn(true).when(dataAccess).Register(Mockito.anyBoolean(), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class));

			result = sut.Register(true, "aa", "aa", "aa", "Prueba", "Prueba", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa", "aa");
			assertTrue(result == expectedvalue);
		} catch (Exception e) {
			System.out.println(e + "AAAAAAAAAAA");
			fail();
		} finally {
			// boolean b = testBL.removeUser(u);
			System.out.println("Finally ");

		}
	}

	

	@Test
	// izena = null
	public void registerTest3() {
		try {
			boolean aa = sut.Register(true, "aa", "aa", "aa", "wer", "wrr", "aa", "aa", "aa", "aa", "aa", "aa", "aa",
					"aa", "aa");
			System.out.println(aa);
			fail();

		} catch (Exception e) {
			System.out.println(e + "aaaaaaa");
			assertTrue(true);
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
		} finally {
			try {

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
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

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
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

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
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
		} finally {
			try {

				System.out.println("Finally ");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

	@Test
	// erabiltzailea datubasean dago
	public void isRegisterText0() {
		try {

			boolean expectedvalue = true;

			Mockito.doReturn(true).when(dataAccess).isRegister(Mockito.any(String.class), Mockito.any(String.class));

			boolean result = sut.isRegister("ibon", "777");
			assertTrue(expectedvalue == result);

		} catch (Exception e) {
			fail();
		}
	}
	@Test
	// erabiltzailea datubasean ez dago
	public void isRegisterText1() {
		try {

			boolean expectedvalue = false;

			Mockito.doReturn(false).when(dataAccess).isRegister(Mockito.any(String.class), Mockito.any(String.class));

			boolean result = sut.isRegister("ibon", "777");
			assertTrue(expectedvalue == result);

		} catch (Exception e) {
			fail();
		}
	}
	@Test
	// erabiltzailea null
	public void isRegisterText2() {
		try {
			Mockito.doReturn(false).when(dataAccess).isRegister(Mockito.any(String.class), Mockito.any(String.class));
			
			sut.isRegister(null, "777");
			fail();

		} catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	// pasahitza null
	public void isRegisterText3() {
		try {
			Mockito.doReturn(false).when(dataAccess).isRegister(Mockito.any(String.class), Mockito.any(String.class));
			
			sut.isRegister("Gorka", null);
			fail();

		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
}
