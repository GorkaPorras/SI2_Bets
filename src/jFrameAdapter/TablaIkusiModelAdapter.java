package jFrameAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Bet;
import domain.Erabiltzailea;



public class TablaIkusiModelAdapter extends AbstractTableModel{
	 private final List<Bet> bets;
	    private Erabiltzailea erabiltzailea;
	    private String[] colNames = new String[] {"Event", "Question","Event Date","Price"};
	    
	    
	    public TablaIkusiModelAdapter(Erabiltzailea p) {
	        //copy the HashMap data to a sequential data structure
	    	bets=new ArrayList<Bet>(p.getEbets());
	    	this.erabiltzailea=p;
	        
	    } 

	    @Override
	    public String getColumnName(int col) {
	        return colNames[col];
	    }
	    @Override
	    public int getColumnCount() {
	        return 4;
	    }

	    @Override
	    public int getRowCount() {
	        return bets.size();
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	       switch(columnIndex) {
	            case 0: return ((Object) bets.get(rowIndex).getResult().getQuestion().getEvent());
	            case 1: return ((Object) bets.get(rowIndex).getResult().getQuestion().getQuestion());
	            case 2: return ((Object) bets.get(rowIndex).getResult().getQuestion().getEvent().getEventDate());
	            case 3: return ((Object) bets.get(rowIndex).getPrice());        
	    }
	       return null;
	   }
}
