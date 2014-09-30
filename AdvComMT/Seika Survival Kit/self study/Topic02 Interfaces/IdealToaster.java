package topic02task01;

public class IdealToaster implements TimeRelated{ //declare that this class implements the TimeRelated interface
	int breadAmount,heatRequire, heatInputPerMin; //class attributes
	public IdealToaster(){ //class constructors
		this.breadAmount = 0;
		this.heatRequire = 0;
		this.heatInputPerMin = 0;
	}
	public IdealToaster(int breadAmount, int heatRequire, int heatInputPerMin){ //class constructors
		this.breadAmount = breadAmount;
		this.heatRequire = heatRequire;
		this.heatInputPerMin = heatInputPerMin;
	}
	public void setBreadAmount(int breadAmount){ //mutator methods
		this.breadAmount = breadAmount;
	}
	public void setHeatRequire(int heatRequire){ //mutator methods
		this.heatRequire = heatRequire;
	}
	public void setHeatInputPerMin(int heatInputPerMin){ //mutator methods
		this.heatInputPerMin = heatInputPerMin;
	}
	public double getTotalTime(){  //method for calculating the time
		return (this.breadAmount * this.heatRequire) / this.heatInputPerMin;
	}
	public int isFasterThan(TimeRelated other){ //implements the abstract method of interface
		IdealToaster otherToaster = (IdealToaster) other;
		if(this.getTotalTime() < otherToaster.getTotalTime()) return 1;
		else if (this.getTotalTime() > otherToaster.getTotalTime()) return -1;
		else return 0;
	}
	public static IdealToaster findFasterToaster(IdealToaster t1, IdealToaster t2){ //using the implemented method
		if(t1.isFasterThan(t2) > 0) return t1;
		else return t2;
	}
	
	public boolean takeMoreThanHour(){ //implements the abstract method of interface
		if(this.getTotalTime() >= 60) return true;
		else return false;
	}
}

