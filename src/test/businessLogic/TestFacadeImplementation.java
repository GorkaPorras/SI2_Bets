package test.businessLogic;


import java.util.Date;

import configuration.ConfigXML;
import domain.Event;
import domain.User;
import test.dataAccess.TestDataAccess;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
		 
		public boolean removeEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(ev);
			dbManagerTest.close();
			return b;

		}
		
		public Event addEvent(String desc, Date d) {
			dbManagerTest.open();
			Event o=dbManagerTest.addEvent(desc,d);
			dbManagerTest.close();
			return o;

		}
		public User register(User u) {			
			dbManagerTest.open();
			 User us =dbManagerTest.register(u);
			dbManagerTest.close();
			return us;
	    }
		public boolean removeUser(User u) {
			dbManagerTest.open();
			boolean us=dbManagerTest.removeUser(u);
			dbManagerTest.close();
			return us;

		}
		public User getUser(String izena) {
			dbManagerTest.open();
			User us=dbManagerTest.getUser(izena);
			dbManagerTest.close();
			return us;

		}
		

}
