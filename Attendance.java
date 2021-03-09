
/**
 * Attendance.java 
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */

/**
* Attendance class which stores the private variables 
* @return nothing
*/
class Attendance {
        // setup private variable 
	private String asuRite;
	private int time;
	private String day;
	private String month;
/**
* Attendance method which stores parameters in the method for later use.
* @Param String asuRite, int time, String month, String day
*/        
	public Attendance(String asuRite, int time, String month, String day) {
		
		this.time = time;
		this.asuRite = asuRite;
		this.month = month;
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
* setter method for time
* @return this.month
*/        
        public void setTime(int time){
                this.time = time;
        }
/**
* getter method for day
* @return this.day
*/
	public String getDay() {
		return this.day;
	}
/**
* getter method for asurite
* @return this.asuRite
*/
	public String getAsurite() {
		return this.asuRite;
	}
/**
* getter method for time
* @return this.time
*/
	public int getTime() {
		return this.time;
	}
}
