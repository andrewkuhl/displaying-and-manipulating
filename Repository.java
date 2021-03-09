import java.io.File;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  

/**
 * Repository.java This class is used to store all the data for the application
 *
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl
 * and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */

@SuppressWarnings("deprecation")
public class Repository extends Observable {

    // setup private variable
    private LinkedList<Student> roster;
    private LinkedList<Attendance> attendanceList;
    private LinkedList<Date> dateList;
    private boolean rosterLoaded = false;
    private boolean attendanceLoaded = false;
    private boolean rosterChanged = false;
    private boolean attendanceChanged = false;

    /**
     * This method is the constructor for Repository
     *
     * @param args Unused.
     * @return Nothing.
     */
    private Repository() {
        // init all the linkedlists to store the data
        this.roster = new LinkedList<Student>();
        this.attendanceList = new LinkedList<Attendance>();
        this.dateList = new LinkedList<Date>();
    }

    /**
     * This method is used to return a new instance of repository
     *
     * @param args Unused.
     * @return Repository This returns a new instance of repository.
     */
    public static Repository getInstance() {
        // return a new repo
        return new Repository();
    }

    /**
     * This method is used to return if a roster has been loaded.
     *
     * @param args Unused.
     * @return boolean This returns a if the roster has been loaded
     */
    public boolean isRosterLoaded() {
        // return if roster is loaded
        return this.rosterLoaded;
    }

    /**
     * This method is used to return if an attendance has been loaded.
     *
     * @param args Unused.
     * @return boolean This returns a if attendance has been loaded.
     */
    public boolean isAttendanceLoaded() {
        // return if attendance is loaded
        return this.attendanceLoaded;
    }

    /**
     * This method is used to return if the attendance has changed
     *
     * @param args Unused.
     * @return boolean This returns a if attendance has been changed.
     */
    public boolean isAttendanceChanged() {
        // return if attendance changed
        return this.attendanceChanged;
    }

    /**
     * This method is used to return if the roster has changed
     *
     * @param args Unused.
     * @return boolean This returns a if roster has been changed.
     */
    public boolean isRosterChanged() {
        // return if roster changed
        return this.rosterChanged;
    }

    /**
     * This method is used to return the attendance LinkedList
     *
     * @param args Unused.
     * @return LinkedList<Attendance> This returns the attendance linkedlist
     */
    public LinkedList<Attendance> getAttendance() {
        // return attendance list
        return this.attendanceList;
    }

    /**
     * This method is used to return the roster LinkedList
     *
     * @param args Unused.
     * @return LinkedList<Student> This returns the roster linkedlist
     */
    public LinkedList<Student> getRoster() {
        // return roster linkedlist
        return this.roster;
    }

    /**
     * This method is used to return the date linked list
     *
     * @param args Unused.
     * @return LinkedList<Date> This returns the date linkedlist
     */
    public LinkedList<Date> getDates() {
        // return date linked list
        return this.dateList;
    }

    /**
     * This method is used to set the data in roster from a csv file
     *
     * @param file This is the file being used for the roster.
     * @return Nothing.
     */
    public void setRoster(File file) {
        try {
            // reset roster
            roster = new LinkedList<Student>();
            // read all lines
            List<String> allLines = Files.readAllLines(file.toPath());
            // loop through lines
            for (String line : allLines) {
                // split string by ,
                String[] parts = line.split(",");
                // make sure that it starts with only numbers
                parts[0] = parts[0].replaceAll("[^0-9]", "");
                // create a new student
                Student newStudent = new Student(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5]);
                // add new student to roster
                roster.add(newStudent);
            }
            // set roster loaded to true
            this.rosterLoaded = true;
        } catch (Exception e) {

        }
        // set roster changed to true and attendance changed to false
        this.rosterChanged = true;
        this.attendanceChanged = false;
        // notify observers
        setChanged();
        notifyObservers();
    }

    /**
     * This method is used to set the data in attendanceList from a csv file
     *
     * @param file This is the file being used for the roster.
     * @return Nothing.
     */
    public void setAttendance(File file, String month, String day) {
        try {
            // read all the lines
            List<String> allLines = Files.readAllLines(file.toPath());
            // loop through every line
            
            for (String line : allLines) {
                boolean found = false;
                boolean added = false;
                // split by ,
                String[] parts = line.split(",");
                // make sure starts with letters or numbers
                parts[0] = parts[0].replaceAll("[^a-zA-z0-9]", "");
                // create new attendance
                Attendance newAttendance = new Attendance(parts[0], Integer.parseInt(parts[1]), month, day);
                // add new attendance to attedance list
                
                
                for(int i = 0; i < attendanceList.size() ; i++){
                    if(newAttendance.getAsurite().equals(attendanceList.get(i).getAsurite()) 
                            && newAttendance.getDay().equals(attendanceList.get(i).getDay())
                            && newAttendance.getMonth().equals(attendanceList.get(i).getMonth())){
                        int hold = attendanceList.get(i).getTime() + newAttendance.getTime();
                        attendanceList.get(i).setTime(hold);
                        
                        added = true;
                        
                    } else {
                        
                    }
                       
                }
                           
                            
                if(added == false){
                    attendanceList.add(newAttendance);
                }
                for (int i = 0; i < roster.size(); i++) {
                    if (newAttendance.getAsurite().equals(roster.get(i).getAsuRite())) {
                        found = true;
                    }
                }
                
                if(found == false){
                    JFrame f = new JFrame("frame");
                    JDialog d = new JDialog(f, "Error Adding Student");
                    // create a label 
                    JLabel l = new JLabel("Error adding " + newAttendance.getAsurite() + " to the roster");

                    d.add(l);

                    // setsize of dialog 
                    d.setSize(300, 100);

                    // set visibility of dialog 
                    d.setVisible(true);
                }
            }
            // set attendance loaded to true
            this.attendanceLoaded = true;
            
        } catch (Exception e) {

        }
        // add date to date list
        this.dateList.add(new Date(month, day));
        // set attenance change and roster changed
        this.attendanceChanged = true;
        this.rosterChanged = false;
        // notify observers
        setChanged();
        notifyObservers();
    }
}
