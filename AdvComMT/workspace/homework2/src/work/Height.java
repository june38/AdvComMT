package work;
//5631363921 Sutatta Adisornvorakij

//this class is about calculating a height of a children by using father and mother height
//from the formula on the Internet

public class Height implements Relatable{
	private String gender="";
	private int fatherHeight=0;
	private int motherHeight=0;
	private int ans=0;
	
	public Height(){
		this.gender="";
		this.fatherHeight=0;
		this.motherHeight=0;
	}
	
	public Height(String a,int b,int c){
		this.gender=a;
		this.fatherHeight=b;
		this.motherHeight=c;
	}
	
	public Height(int a,int b){
		this.fatherHeight=a;
		this.motherHeight=b;
	}
	
	public void setGender(String a){
		this.gender=a;
	}
	
	public void setFatherHeight(int b){
		this.fatherHeight=b;
	}
	
	public void setMotherHeight(int c){
		this.motherHeight=c;
	}
	
	public String getGender(){
		return gender;
	}
	
	public int getFatherHeight(){
		return fatherHeight;
	}

	public int getMotherHeight(){
		return motherHeight;
	}
	
	public int getAns(){
		if(this.gender.equals("boy")){
			ans=((this.fatherHeight+this.motherHeight+13)/2);
		}else if(this.gender.equals("girl")){
			ans=((this.fatherHeight+this.motherHeight-13)/2);
		}
		return ans;
	}

	 public int isLargerThan(Relatable other) {
	        Height otherH = (Height)other;
	        if (this.getAns() < otherH.getAns())
	            return -1;
	        else if (this.getAns() > otherH.getAns())
	            return 1;
	        else
	            return 0;               
	 }
	 
	 public boolean isDivisibleByFive(Relatable other){
		 Height otherH = (Height)other;
		 if(((this.getAns()%5)==0)&&((otherH.getAns()%5)==0)){
			 return true;
		 }else return false;
	 }
	 
	 public boolean isEqual(Relatable other){
		 Height otherH = (Height)other;
		 if(this.getAns()==otherH.getAns()){
			 return true;
		 }else return false;
	 }
	 
	 public static void main(String [] args){
		 Height a = new Height("boy",180,160);
		 Height b = new Height("girl",175,155);
		 System.out.println(a.isLargerThan(b));
		 System.out.println(a.isDivisibleByFive(b));
		 System.out.println(a.isEqual(b));
	 }
}
