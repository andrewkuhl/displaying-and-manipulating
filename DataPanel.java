
import java.awt.*;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ui.Drawable;

import javax.swing.*;

/**
 * DataPanel.java
 *
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl
 * and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */
public class DataPanel extends JPanel implements Observer {

    // private instance variables
    private Drawable d;
    private JTable jt;

    public DataPanel() {
        // create a new jtable
        jt = new JTable();
    }

    public void update(Observable o, Object arg) {
        Repository repo = ((Repository) o);
        // check if roster was loaded
        if (repo.isRosterLoaded() == false) {
            return;
        }

        // check if roster was changed
        if (repo.isRosterChanged() == true) {
            // reset frame
            this.removeAll();
            this.revalidate();
            this.repaint();
            // reset jtable
            jt = new JTable();
            // get tables model
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            // add columns and set their widths
            String column[] = {"ID     ", "First Name     ", "Last Name     ", "Program     ", "Academic Level     ", "Asurite     "};
            model.setColumnIdentifiers(column);
            jt.getColumnModel().getColumn(0).setPreferredWidth(80);
            jt.getColumnModel().getColumn(1).setPreferredWidth(40);
            jt.getColumnModel().getColumn(2).setPreferredWidth(55);
            jt.getColumnModel().getColumn(3).setPreferredWidth(100);
            jt.getColumnModel().getColumn(4).setPreferredWidth(110);
            jt.getColumnModel().getColumn(5).setPreferredWidth(65);
            jt.setBounds(100, 100, 900, 900);
            jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            // create scroll pane
            JScrollPane scrollPane = new JScrollPane(jt);
            // Force the scrollbars to always be displayed
            scrollPane.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // make pane and table visible
            this.add(scrollPane);
            this.setVisible(true);

            // get repo
            LinkedList<Student> roster = repo.getRoster();

            // add headers to table
            String data[] = new String[6];
            String idLabel = "ID";
            String firstName = "First Name";
            String lastName = "Last Name";
            String level = "Level";
            String program = "Program";
            String asuRite = "AsuRite";

            data[0] = idLabel;
            data[1] = firstName;
            data[2] = lastName;
            data[3] = level;
            data[4] = program;
            data[5] = asuRite;
            // add to table
            model.addRow(data);
            // loop through roster
            for (int i = 0; i < roster.size(); i++) {
                // add roster data for row
                data = new String[6];
                Student current = roster.get(i);
                int id = current.getID();
                firstName = current.getFirstName();
                lastName = current.getLastName();
                level = current.getLevel();
                program = current.getProgram();
                asuRite = current.getAsuRite();

                data[0] = Integer.toString(id);
                data[1] = firstName;
                data[2] = lastName;
                data[3] = level;
                data[4] = program;
                data[5] = asuRite;
                // insert into table
                model.addRow(data);
            }
            // add data to table
            jt.setModel(model);

            // update table
            model.fireTableDataChanged();
        } else {
            // reset panel
            this.removeAll();
            this.revalidate();
            this.repaint();

            // reset table
            jt = new JTable();

            // get table model
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            // get dates
            LinkedList<Date> dates = repo.getDates();
            // add headers
            String column[] = new String[6 + dates.size()];
            column[0] = "ID";
            column[1] = "First Name";
            column[2] = "Last Name";
            column[3] = "Program";
            column[4] = "Academic Level";
            column[5] = "Asurite";
            // add dates to header
            for (int i = 0; i < dates.size(); i++) {
                String month = dates.get(i).getMonth();
                String day = dates.get(i).getDay();
                column[i + 6] = month + " " + day;
            }
            // set widths for columns
            model.setColumnIdentifiers(column);
            jt.getColumnModel().getColumn(0).setPreferredWidth(80);
            jt.getColumnModel().getColumn(1).setPreferredWidth(40);
            jt.getColumnModel().getColumn(2).setPreferredWidth(55);
            jt.getColumnModel().getColumn(3).setPreferredWidth(100);
            jt.getColumnModel().getColumn(4).setPreferredWidth(110);
            jt.getColumnModel().getColumn(5).setPreferredWidth(65);
            for (int i = 0; i < dates.size(); i++) {
                jt.getColumnModel().getColumn(i).setPreferredWidth(100);
            }
            jt.setBounds(100, 100, 1500, 1500);
            jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            JScrollPane scrollPane = new JScrollPane(jt);
            // Force the scrollbars to always be displayed
            scrollPane.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            // make table visible
            this.add(scrollPane);
            this.setVisible(true);
            // get roster from repo
            LinkedList<Student> roster = repo.getRoster();

            // add headers to table
            String data[] = new String[6 + dates.size()];
            String idLabel = "ID";
            String firstName = "First Name";
            String lastName = "Last Name";
            String level = "Level";
            String program = "Program";
            String asuRite = "AsuRite";

            data[0] = idLabel;
            data[1] = firstName;
            data[2] = lastName;
            data[3] = level;
            data[4] = program;
            data[5] = asuRite;
            // add dates to header
            for (int i = 0; i < dates.size(); i++) {
                String month = dates.get(i).getMonth();
                String day = dates.get(i).getDay();
                data[i + 6] = month + " " + day;
            }
            // add header to table
            model.addRow(data);
            // add roster to table
            for (int i = 0; i < roster.size(); i++) {
                // add roster data to table
                data = new String[6 + dates.size()];
                Student current = roster.get(i);
                int id = current.getID();
                firstName = current.getFirstName();
                lastName = current.getLastName();
                level = current.getLevel();
                program = current.getProgram();
                asuRite = current.getAsuRite();

                data[0] = Integer.toString(id);
                data[1] = firstName;
                data[2] = lastName;
                data[3] = level;
                data[4] = program;
                data[5] = asuRite;
                // add attendance data to table
                for (int j = 0; j < dates.size(); j++) {
                    // loop through the attendance and try to find when the asurite and month and day match up
                    LinkedList<Attendance> attendanceList = repo.getAttendance();
                    String time = " ";
                    // check if there are any attendance objects that have asurite, month and day that match
                    for (int k = 0; k < attendanceList.size(); k++) {
                        Date currentDate = dates.get(j);
                        String month = currentDate.getMonth();
                        String day = currentDate.getDay();
                        Attendance currentAttendance = attendanceList.get(k);
                        // if so then set the time
                        if (currentAttendance.getAsurite().equals(asuRite) && currentAttendance.getMonth().equals(month) && currentAttendance.getDay().equals(day)) {
                            time = Integer.toString(currentAttendance.getTime());
                        }
                    }
                    // set in table
                    data[j + 6] = time;
                }
                // add to table	
                model.addRow(data);
            }
            // add data to table
            jt.setModel(model);
            // update table
            model.fireTableDataChanged();
        }
    }
}