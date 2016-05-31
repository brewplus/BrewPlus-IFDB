/*
 * DateEditor.java
 *
 * Created on 26 giugno 2005, 10.09
 */

package jmash;

//import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Alessandro
 */
public class DateEditor extends AbstractCellEditor
        implements TableCellEditor {
  //  JDateChooser chooser ;

    /**
	 * 
	 */
	private static final long serialVersionUID = -6996806715776998285L;
	public DateEditor() {
    //    chooser= new JDateChooser();
        /*spinner.setModel(new SpinnerListModel(java.util.Arrays.asList(items)));*/
        
    }
    Date date=null;
    // Prepares the spinner component and returns it.
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        return null;
    }

    // Enables the editor only for double-clicks.
    @Override
	public boolean isCellEditable(EventObject evt) {
        if (evt instanceof MouseEvent) {
            return ((MouseEvent)evt).getClickCount() > 1;
        }
        return false;
    }

    // Returns the spinners current value.
    @Override
    public Object getCellEditorValue() {
       // date=chooser.getDate();
      //  System.out.println("date:"+date);
        return null;
    }
}
