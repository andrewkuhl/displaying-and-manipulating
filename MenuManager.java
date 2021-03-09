import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.LinkedList;
import java.util.ListIterator;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
/**
 * MenuManager.java 
 * The MenuManager class is used to manage all of the features of the menu of the program.
 * The class initializes a data repo, loads all the data required by the program, saves new changes made to it, and
 * adds new data that is not already accounted for.
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */
public class MenuManager {
    
    // instance variables
    private JFrame frame;
    private int tracker;
    private Repository repo;
    
    /**
     * This constructor initializes a data repo
     * @param repo The data repo that is passed into the constructor
     * @return Nothing
    */
    public MenuManager(Repository repo) {
        // init repo
        this.repo = repo;
    }
    
    /**
     * This method is used to throw an exception when trying to access an unreachable file.
     * @return Nothing
    */
    public void load() throws FileNotFoundException { // loads data and stores into student not implenmented
        
    }
    
    /**
     * This method saves the data from the repo into the students that have not been added.
     * It organizes the data and ensures that everything is formatted for cleanliness,
     * and then writes all the data to a .csv file and saves it.
     * @return Nothing
    */
    public void save() { 
        //Do if there is no attendance yet
        if (repo.isAttendanceLoaded() == false) {
            // init string builder
            StringBuilder sb = new StringBuilder();
            // init file chooser
            JFileChooser chooser = new JFileChooser();
            
            // get roster from repo
            LinkedList<Student> students = this.repo.getRoster();

            //header
            String[] SaveFileHeader = {"ID", "First Name", "Last Name", "Program", "Academic Level", "Asurite", "\r\n"};
            for (int i = 0; i < SaveFileHeader.length; i++) {
                sb.append(SaveFileHeader[i]);
                if (i < 5) {
                    sb.append(",");
                }
            }
            
            // save row and input student data
            for (int i = 0; i < students.size(); i++) {
                Student CurrentStudent;
                CurrentStudent = students.get(i);
                sb.append(CurrentStudent.getID());
                sb.append(",");
                sb.append(CurrentStudent.getFirstName());
                sb.append(",");
                sb.append(CurrentStudent.getLastName());
                sb.append(",");
                sb.append(CurrentStudent.getProgram());
                sb.append(",");
                sb.append(CurrentStudent.getLevel());
                sb.append(",");
                sb.append(CurrentStudent.getAsuRite());
                sb.append(",");
                sb.append("\r\n");

            }
            
            // open up user dir and get file
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try {
                    PrintWriter pw = new PrintWriter(chooser.getSelectedFile() + ".csv");
                    pw.write(sb.toString());
                    pw.close();
                } catch (Exception e) {

                }
            }
            //do if there is attendance
        } else {
            // init string builder
            StringBuilder sb = new StringBuilder();
             // init file choose
            JFileChooser chooser = new JFileChooser();
            
            // get attendance and students from the repo
            LinkedList<Student> students = this.repo.getRoster();
            LinkedList<Attendance> attendance = this.repo.getAttendance();
            ArrayList<String> Dates = new ArrayList<>();
            
            for (int i = 0; i < attendance.size(); i++) {
                Attendance attendanceDate = attendance.get(i);
                Dates.add(attendanceDate.getMonth() + " " + attendanceDate.getDay());
            }
            
            Set<String> set = new LinkedHashSet<>(Dates);
            Dates.clear();
            Dates.addAll(set);
            
            String[] SaveFileHeader = {"ID", "First Name", "Last Name", "Program", "Academic Level", "Asurite", "\r\n"};
            for (int i = 0; i < SaveFileHeader.length; i++) {
                sb.append(SaveFileHeader[i]);
                //adding dates to the header
                if (i == 5) {
                    for (int j = 0; j < Dates.size(); j++) {
                        String hold = Dates.get(j);
                        sb.append(",");
                        sb.append(hold);
                        
                    }
                }
                
                if (i < 5) {
                    sb.append(",");
                }
                
            }
            // add students information into row
            for (int i = 0; i < students.size(); i++) {
                Student CurrentStudent;
                CurrentStudent = students.get(i);
                sb.append(CurrentStudent.getID());
                sb.append(",");
                sb.append(CurrentStudent.getFirstName());
                sb.append(",");
                sb.append(CurrentStudent.getLastName());
                sb.append(",");
                sb.append(CurrentStudent.getProgram());
                sb.append(",");
                sb.append(CurrentStudent.getLevel());
                sb.append(",");
                sb.append(CurrentStudent.getAsuRite());
                sb.append(",");
                //adding the dates
                for(int j = 0; j < attendance.size(); j++){
                    Attendance CurrentAttendance = attendance.get(j);
                    if(CurrentAttendance.getAsurite().equals(CurrentStudent.getAsuRite())){
                        sb.append(CurrentAttendance.getTime());
                        sb.append(",");
                    }
                }
                sb.append("\r\n");

            }
            
            //init file that we are going to output
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try {
                    PrintWriter pw = new PrintWriter(chooser.getSelectedFile() + ".csv");
                    pw.write(sb.toString());
                    pw.close();
                } catch (Exception e) {

                }
            }
        }
    }
    /**
     * This method adds new info that has not already been accounted for, and
     * ensures that it is formatted correctly.
     * @return Nothing
    */
    public void add() { 
        JOptionPane.showMessageDialog(frame, "data loaded for " + tracker + " in the roster" + "\n"
                + " additional attendee was found" + " connected for" + "mintute(s)");
    }
}