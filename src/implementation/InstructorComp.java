package implementation;
import java.util.*;

import interfaces.Instructor;

public class InstructorComp implements Comparator<Instructor>{

	@Override
	//requires: 2 parameters of type Instructor
	//effects:return 0 if the parameters are the same instructor and any other integer if it is not the case
	public int compare(Instructor o1, Instructor o2) {
		String i1 = o1.getFirstName() + " " + o1.getLastName();
		String i2 = o2.getFirstName() + " " + o2.getLastName();
 		return i1.compareTo(i2);
	}

}
