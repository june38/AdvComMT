package work;

//5631363921 Sutatta Adisornvorakij

//this class is about calculating an acceleration by using velocity and time

//using physic formula

public class Acceleration implements Relatable{
	private double u = 0.0;
	private double v = 0.0;
	private double time = 0.0;
	private double acc = 0.0;
	
	public Acceleration(){
		this.u=0.0;
		this.v=0.0;
		this.time=0.0;
	}
	
	public Acceleration(double x,double y,double z){
		this.u = x;
		this.v = y;
		this.time = z;
	}
	
	public void setU(double x){
		this.u=x;
	}
	 public void setV(double y){
		 this.v=u;
	 }
	 
	 public void setTime(double z){
		 this.time=z;
	 }
	 
	 public double getU(){
		 return this.u;
	 }
	 
	 public double getV(){
		 return this.v;
	 }
	 
	 public double getTime(){
		 return this.time;
	 }
	 
	 public double getAcc(){
		 acc=((Math.abs(this.v-this.u))/this.time);
		 return acc;
	 }
	
	 public int isLargerThan(Relatable other) {
	        Acceleration otherA = (Acceleration)other;
	        if (this.getAcc() < otherA.getAcc())
	            return -1;
	        else if (this.getAcc() > otherA.getAcc())
	            return 1;
	        else
	            return 0;               
	 }
	 
	 public boolean isDivisibleByFive(Relatable other){
		 Acceleration otherA = (Acceleration)other;
		 if(((this.getAcc()%5)==0)&&((otherA.getAcc()%5)==0)){
			 return true;
		 }else return false;
	 }
	 
	 public boolean isEqual(Relatable other){
		 Acceleration otherA = (Acceleration)other;
		 if(this.getAcc()==otherA.getAcc()){
			 return true;
		 }else return false;
	 }
	 
	 public static void main(String [] args){
		 Acceleration a = new Acceleration(10.0,100.0,30.0);
		 Acceleration b = new Acceleration(80.0,140.0,50.5);
		 System.out.println(a.isLargerThan(b));
		 System.out.println(a.isDivisibleByFive(b));
		 System.out.println(a.isEqual(b));
	 }
}
