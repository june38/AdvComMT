package topic1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloMoon {
	public static void main(String[] args) {
		Param param = getParamFromUser();
		if(param==null){
			System.exit(1);
		}
		int [] data = param.genRandomData();
		for(int i=0;i<data.length;i++){
			sayHelloMoon(data[i]);
		}
	}
	private static Param getParamFromUser(){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter number of random data points:");
			int numData = Integer.parseInt(in.readLine());
			System.out.print("Enter min allowable integer:");
			int min = Integer.parseInt(in.readLine());
			System.out.print("Enter max allowable integer:");
			int max = Integer.parseInt(in.readLine());
			return new HelloMoon().new Param(numData,min,max);
		}catch(IOException e){
			return null;
		}
	}
	
	public static void sayHelloMoon(int i){
		String s = "Hello ";
		int k = 0;
		do{
			s += "Moon ";
			k++;
		}while(k<i);
		System.out.println(s+" "+i);
	}
	
	class Param{
		private int numData = 5;
		private int min = 0;
		private int max = 3;
		
		public Param(){
			this(5,0,3);
		}
		
		public Param(int numData,int min, int max){
			this.numData = numData;
			this.min = min;
			this.max = max;
		}
		
		public int [] genRandomData(){
			int [] data = new int[numData];
			for(int i=0;i<numData;i++){
				data[i] = (int)Math.round(Math.random()*(max-min)+min);
			}
			return data;
		}
	}
}
