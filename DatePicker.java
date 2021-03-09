
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * DatePicker.java 
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */

/**
   * This is the DatePicker method which helps pick date for attendance.
   */
public class DatePicker extends JFrame implements ActionListener {
        // setting up private variables
	private Repository repo;
	private JComboBox month;
	private JComboBox day;
        // setting up the drop boxes for the date picker
	private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
	private String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        // method taking in repository repo
	public DatePicker(Repository repo) {
		this.repo = repo;
		this.setLayout(new FlowLayout());
                // creating a drop down box for months
		month = new JComboBox(months);
                // creating a drop down box for days
		day = new JComboBox(days);
                // setting the size of the month drop down box
		month.setPreferredSize(new Dimension(200, 20));
                // setting the size of the day drop down box
		day.setPreferredSize(new Dimension(200, 20));
                // creating the "Next" button
		JButton nextButton = new JButton("Next");
                // next button gets added to the action listener
		nextButton.addActionListener(this);
                // adding the month
		this.add(month);
                // adding the day
		this.add(day);
                // adding the next button
		this.add(nextButton);
                // setting the visibility 
		this.setVisible(true);
	}
/**
   * This is the actionPerformed method.
   * @param e ActionEvent when buttons are being pressed
   * @return nothing.
   */
	public void actionPerformed(ActionEvent e) {
                // creating monthIndex for repo.setAttendance below
		int monthIndex = month.getSelectedIndex();
                // creating dayIndex for the repo.setAttendance below
		int dayIndex = day.getSelectedIndex();
                // creating new file chooser
		JFileChooser fileChooser = new JFileChooser();
		try {
			int returnVal = fileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File chosenFile = fileChooser.getSelectedFile();
                                // sets attendance to the selected file with the months[index] and days[index]
				repo.setAttendance(chosenFile, months[monthIndex], days[dayIndex]);
			}
		} catch (Exception er) {

		}
		setVisible(false);
		dispose();
	}
}
