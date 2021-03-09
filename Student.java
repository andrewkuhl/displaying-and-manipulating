/**
 * Student.java 
 * @author martin ituarte hernandez, Justin Rittmeyer, Ben Bousquet, Andrew Kuhl and Vikram Dattatri
 * @since 12-1-20
 * @version 17.2 build version 2
 */

/**
* Student class which stores the private variables 
* @return nothing
*/
class Student {
        // setting up private variables
	private int ID;
	private String firstName;
	private String lastName;
	private String program;
	private String level;
	private String asuRite;
/**
* Student method which stores parameters in the method for later use.
* @param int ID, String firstName, String lastName, String program, String level, String asuRite
*/      
	public Student(int ID, String firstName, String lastName, String program, String level, String asuRite) {
		//assigning parameter ID to ID instance in the class
                this.ID = ID;
                //assigning parameter firstName to firstName instance in the class
		this.firstName = firstName; 
                //assigning parameter lastName to lastName instance in the class
		this.lastName = lastName; //assigning parameter lastName to LastName instance in the class
                //assigning parameter program to program instance in the class
		this.program = program; 
                //assigning parameter level to level instance in the class
		this.level = level;
                //assigning parameter asurite to asurite instance in the class
		this.asuRite = asuRite; 
	}
/**
* getter method for id
* @return this.ID
*/
	public int getID() {
		return this.ID;
	}
/**
* getter method for firstName
* @return this.firstName
*/
	public String getFirstName() {
		return this.firstName;
	}
/**
* getter method for lastName
* @return this.lastName
*/
	public String getLastName() {
		return this.lastName;
	}
/**
* getter method for program value
* @return this.program
*/
	public String getProgram() {
		return this.program;
	}
/**
* getter method for level value
* @return this.level
*/
	public String getLevel() {
		return this.level;
	}
/**
* getter method for asuRite
* @return this.asuRite
*/
	public String getAsuRite() {
		return this.asuRite;
	}

}
