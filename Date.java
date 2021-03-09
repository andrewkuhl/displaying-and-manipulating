/**
 * Date.java 
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */

/**
* Date class which stores the private variables 
* @return nothing
*/
public class Date {
        // setting up private variables
	private String month;
	private String day;
/**
* Date method which stores parameters in the method for later use.
* @Param String month, String day
*/  
	public Date (String month, String day) {
                //assigning parameter month to month instance for later use
		this.month = month;
                //assigning parameter day to day instance for later use
		this.day = day; 
	}
/**
* getter method for month
* @return this.month
*/  
	public String getMonth() {
		return this.month;
	}
/**
* getter method for day
* @return this.day
*/   
	public String getDay() {
		return this.day;
	}
}
