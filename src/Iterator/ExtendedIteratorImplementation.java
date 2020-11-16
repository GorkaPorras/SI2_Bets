package Iterator;

import java.util.List;

import domain.Event;

public class ExtendedIteratorImplementation implements ExtendedIterator<Event> {
	 

	     List<Event> event;
	        int position;

	        public ExtendedIteratorImplementation(List<Event> s) {
	            this.event = s;
	            this.position=0;
	        }

	        @Override
	        public boolean hasNext() {
	            return position<event.size();
	        }

	        @Override
	        public Event next() {
	            Event ev=event.get(position);
	            position++;
	            return ev;
	        }

	        @Override
	        public Event previous() {
	            Event e=event.get(position);
	            position--;
	            return e;
	        }

	        @Override
	        public boolean hasPrevious() {
	            return position>=0;
	        }

	        @Override
	        public void goFirst() {
	            position=0;

	        }

	        @Override
	        public void goLast() {
	            position=event.size()-1;

	        }

	        @Override
	        public int size() {
	            // TODO Auto-generated method stub
	            return event.size();
	        }

	        @Override
	        public Boolean isEmpty() {
	            // TODO Auto-generated method stub
	            return event.size()==0;
	        }

	  

}
