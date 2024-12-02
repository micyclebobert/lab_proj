package com.lab.project.TemplateComponent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {
    String[] columnNames={""};

    public Table () {
        //DefaultTableModel model = new DefaultTableModel(info, columnNames);
        //JTable table = new JTable(model);
        
        //table.setBounds(30,40,200,300);
        //table.setTableHeader(null);
        //info(table);
    }

    public JTable info(String[][] info){
        DefaultTableModel model = new DefaultTableModel(info, columnNames);
        JTable table = new JTable(model);
        
        table.setBounds(30,40,200,300);
        table.setTableHeader(null);
        return table;
    }
}
