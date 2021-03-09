
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * Header.java 
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */

/**
*
* The Header class sets up the Header and its various functions.
* 
*/

public class Header extends JMenuBar implements ActionListener {
        // setting up private variables
	private JMenu menu, menu2;
	private JMenuItem loadRoster, addAttendence, save, plot, info;
	private MenuManager manager;
	private Repository repo;
/**
* This method is used as the constructor for Header.
* @param repo Repository type that has necessary data
* @return nothing.
*/
	public Header(Repository repo) {
		this.repo = repo;

		manager = new MenuManager(repo);
                // creates file menu on main screen
		menu = new JMenu("File");
                // creates about menu on main screen
		menu2 = new JMenu("About");
                // sets menu foreground to red
		menu.setForeground(Color.RED);
                // sets menu2 foreground to red
		menu2.setForeground(Color.RED);
                // adding load a roster option
		loadRoster = new JMenuItem("Load a Roster");
                // setting foreground of loadRoster to red
		loadRoster.setForeground(Color.RED);
                // adding add attendance option
		addAttendence = new JMenuItem("Add Attendance");
                // setting foreground of addAttendence to red
		addAttendence.setForeground(Color.RED);
                // adding Save option
		save = new JMenuItem("Save");
                // setting foreground of save to red
		save.setForeground(Color.RED);
                // adding plot data option
		plot = new JMenuItem("Plot Data");
                // setting foreground of plot data to red
		plot.setForeground(Color.RED);
                // creating team info option
		info = new JMenuItem("Team Info");
                // setting foreground of info to red
		info.setForeground(Color.RED);

		loadRoster.addActionListener(this);
		addAttendence.addActionListener(this);
		save.addActionListener(this);
		plot.addActionListener(this);
		info.addActionListener(this);
                // adds all options to menu 
		menu.add(loadRoster);
		menu.add(addAttendence);
		menu.add(save);
		menu.add(plot);
                // adds info to menu2
		menu2.add(info);

		this.add(menu);
		this.add(menu2);
	}
/**
* This method is an override of the actionPerformed that implements actions 
* when a button in Header is pressed.
* @param e ActionEvent for a button being pressed.
* @return nothing.
*/
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser;
                // will open and load file
		if (e.getSource() == loadRoster) 
		{
			fileChooser = new JFileChooser();
			try {
				int returnVal = fileChooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File chosenFile = fileChooser.getSelectedFile();
					repo.setRoster(chosenFile);
				}
			} catch (Exception er) {

			}

			try {
				manager.load();
			} catch (FileNotFoundException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
                // adds to file 
		if (e.getSource() == addAttendence) 
		{
			DatePicker dp = new DatePicker(repo);
			dp.setSize(800, 400);
		}
                // saves data 
		if (e.getSource() == save)  
		{
			manager.save();
		}
		;
                // creates plot
		if (e.getSource() == plot)
		{
			Plot plot = new Plot("Chart", repo);
			// sets size of plot
                        plot.setSize(800, 400);
			plot.setLocationRelativeTo(null);
                        // sets visibility of plot
			plot.setVisible(true);
                        // adds plot to observer role
			repo.addObserver(plot);
		}
                // makes window with info including our team names
		if (e.getSource() == info) { 
			JFrame frame;
			frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Team: Martin Ituarte Hernandez, Andrew Kuhl, Justin Rittmeyer, Ben Bousquet, and Vikram Dattatri");

		}
	}

}
