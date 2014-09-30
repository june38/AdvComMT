package topic5;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

class MyCellRenderer implements ListCellRenderer<Object> {
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list,
			Object value, int index, boolean isSelected, boolean cellHasFocus) {
		CustomEntryPanel panel = (CustomEntryPanel)value;
		if(isSelected){
			panel.setBackground(Color.RED);
			panel.setForeground(Color.WHITE);
		}else{
			panel.setBackground(Color.WHITE);
			panel.setForeground(Color.BLACK);
		}
		return panel;
	}
}
