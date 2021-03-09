
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Main.java 
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */

/**
   * This is the Main method which creates the main frame where everything is displayed.
   */
public class Main extends JFrame {
    // private ScatterPlot plot;

    JTextArea ta;
    // setting up private variable
    private Repository repo;
/**
   * This is the Main method constructor.
   * @param none Unused.
   * @return Nothing.
   */
    public Main() {
        repo = Repository.getInstance();
        // creating a new frame that says "CSE 360 Final Project"
        JFrame frame = new JFrame("CSE 360 Final Project");
        // creating new header object
        Header header = new Header(repo);
        // creating new datapanel object
        DataPanel table = new DataPanel();
        // setting the size of the table
        table.setPreferredSize(new Dimension(700, 500));
        // adding observer role to table
        repo.addObserver(table);
        // setting backgorund to black
        header.setBackground(Color.black);
        frame.setJMenuBar(header);

       // setting layout of the frame
        frame.setLayout(new FlowLayout(0,0,0));
        // adding table
        frame.add(table);
        // setting visibility
        frame.setVisible(true);
        // setting up the size
        frame.setSize(700, 500);
        // setting up default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
/**
   * This is the main method which makes use of Main method.
   * @param args Unused.
   * @return Nothing.
   * @exception IOException On input error.
   * @see IOException
   */
    public static void main(String[] args) throws Exception {

        new Main();

    }
}
