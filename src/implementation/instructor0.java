package implementation;

import interfaces.Instructor;

public class instructor0 implements Instructor{
	private String instructorFirst,instructorLast;
	@Override
	//requires:none
	//effects:return the instructor's first name
	public String getFirstName() {
		return instructorFirst;
	}

	@Override
	//requires:none
	//effects:return the instructor's last name
	public String getLastName() {
		return instructorLast;
	}

	public instructor0(String instructorFirst, String instructorLast) {
		//requires: the instructor's first and last name
		//effects:create an instance of instructor
		super();
		this.instructorFirst = instructorFirst;
		this.instructorLast = instructorLast;
	}

}
