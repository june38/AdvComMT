package topic02task01;
public class RoadJourney implements TimeRelated{ //declare that this class implements the TimeRelated interface
	private int distance; 
	private int speed;  //class attributes
	public RoadJourney(){ //class constructors
		this.distance = 0;
		this.speed = 0;
	} 
	public RoadJourney(int distance, int speed){ //class constructors
		this.distance = 0;
		this.speed = speed;
	} 
	public void setDistance(int distance){ //mutator methods
		this.distance = distance;
	}
	public void setSpeed(int speed){ //mutator methods
		this.speed = speed;
	}
	public double getTime(){  //method for calculating the time
		return this.distance/this.speed; 
	}
	public int isFasterThan(TimeRelated other) { //implements the abstract method of interface
		RoadJourney otherR = (RoadJourney) other;
		if(this.getTime()<otherR.getTime()) return 1;
		else if (this.getTime()>otherR.getTime()) return -1;
		else return 0;
	}
	
	public static RoadJourney findFasterJourney(RoadJourney j1, RoadJourney j2){ //using the implemented method
		if(j1.isFasterThan(j2) > 0) return j1;
		else return j2;
	}
	
	public boolean takeMoreThanHour(){  //implements the abstract method of interface
		if(this.getTime() >= 1) return true;
		else return false;
	}
}