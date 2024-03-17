package implementation;

import interfaces.Room;

public class room0 implements Room {
	private String building;
	private String roomNum;

	public room0(String building, String roomNum) {
		//requires:a building and roomnumber 
		//effects: create an instance of Room
		super();
		this.building = building;
		this.roomNum = roomNum;
	}

	@Override
	//requires:none
	//effects:return the name of the building
	public String getBuilding() {
		return building;
	}

	@Override
	//requires:none
	//effects:return the room number in string format
	public String getRoomNumber() {
		return roomNum;
	}
	
	
}
