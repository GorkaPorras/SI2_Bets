package Iterator;

import java.util.Calendar;
import java.util.Date;

import Factory.NLFactory;
import businessLogic.BLFacade;
import domain.Event;

public class Main {

	public static void main(String[] args) {
		boolean isLocal = true;
		// Facade objektua lortu lehendabiziko ariketa erabiliz
		// BLFacade facadeInterface=……….
		NLFactory f = new NLFactory();
		BLFacade facadeInterface = f.getNL(isLocal);
		ExtendedIterator<Event> i = facadeInterface.getEvents(newDate(2019, 1, 17));
		Event ev;
		i.goLast();
		while (i.hasPrevious()) {
			ev = (Event) i.previous();
			System.out.println(ev.toString());
		}
		System.out.println("----------------------------------------");
		// Nahiz eta suposatu hasierara ailegatu garela, eragiketa egiten dugu.
		i.goFirst();
		while (i.hasNext()) {
			ev = (Event) i.next();
			System.out.println(ev.toString());
		}
	}

	private static Date newDate(int year, int month, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
}
