import java.util.LinkedList;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.Observer;
import java.util.Observable;
import java.util.Set;
/**
 * Plot.java 
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */

/**
* The Plot class takes the data that is entered into the program and creates a graph out of it.
* Also continuously updates the data and the corresponding scatter plot. 
*/
public class Plot extends JFrame implements Observer {

    //init repo
    private Repository repo;
    
    /**
     * This method is used to create a new data set
     * and then draw a scatter plot from that data set.
     * Afterwards it sets the colors and visual properties
     * of the chart before finally adding it to the chart panel
     * @param title This is the first parameter for the Plot method
     * @param repo This is the second parameter for the plot method
     * @return Nothing
     */
    public Plot(String title, Repository repo) {
        // call parent constructor to add title
        super(title);
        //fill repo
        this.repo = repo;
        // create new dataset
        XYDataset dataset = createDataset();
        
        // create new scatter plot
        JFreeChart chart = ChartFactory.createScatterPlot(
                " ",
                "X-Axis", "Count", dataset);
        
        // create new xyplot
        XYPlot plot = (XYPlot) chart.getPlot();
        // set background color
        plot.setBackgroundPaint(new Color(255, 228, 196));
        
        // add chart to chart panel
        ChartPanel panel = new ChartPanel(chart);
        // set the content pane to chartpanel
        setContentPane(panel);
    }
    
    /**
     * This method gets the attendance list and roster from the data set
     * and proceeds to create a new data set for the chart that
     * is organized and fits all the categories needed for the chart,
     * and plots it within the data set in the correct format.
     * Also adds series to the data set when needed.
     * @return dataset This returns the resulting data set that was created
    */
    private XYDataset createDataset() {
        // get the attendance list and roster
        LinkedList<Attendance> attendance = this.repo.getAttendance();
        LinkedList<Student> roster = this.repo.getRoster();
        // create a new dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        //list of the dates that will be series
        ArrayList<String> Dates = new ArrayList<>();
        //getting the dates into the List
        for (int i = 0; i < attendance.size(); i++) {
            Attendance attendanceDate = attendance.get(i);
            Dates.add(attendanceDate.getMonth() + " " + attendanceDate.getDay());
        }
        //getting rid of the duplicate dates
        Set<String> set = new LinkedHashSet<>(Dates);
        Dates.clear();
        Dates.addAll(set);
        ArrayList<XYSeries> SeriesList = new ArrayList<>();
       //going through the attendance list and adding all of the matching attendance records to the plot
       for(int h = 0; h < Dates.size(); h++){
           
           int HelpMe = 1;
           XYSeries series1 = new XYSeries(Dates.get(h));
           
           for(int i = 1; i < attendance.size() + 1; i++) {
            boolean present = false;
            Attendance student = attendance.get(i-1);
            
            for(int j = 0; j < roster.size(); j++){
                Student comparing = roster.get(j);
                if(student.getAsurite().equals(comparing.getAsuRite()) && Dates.get(h).equals(student.getMonth() + " " + student.getDay())){
                    present = true;
                    
		}
            }
            //if the attendance matches a student add to series
            if(present == true){
                int time = student.getTime();
                series1.add(HelpMe, time);
                HelpMe++;
                
            }
            
        }
           //adding the series to a list
           SeriesList.add(series1);
       }
       //adding all of the series from the list to the plot
       for(int i = 0; i < SeriesList.size(); i++)
            dataset.addSeries(SeriesList.get(i));

        return dataset;
    }
    
    /**
     * This method is used to update the data on the graph with data from the repository
     * @param o Observable o is the first parameter of the method update
     * @param arg Object arg is the second parameter of the method update
     * @return Nothing
    */
    public void update(Observable o, Object arg) {
        Repository repo = ((Repository) o);
        // update data on graph with data from repo
    }

}