package topic02task01;

public class Test {
	public static void main(String[] args){
		IdealToaster t1 = new IdealToaster(22,22,4);
		IdealToaster t2 = new IdealToaster(40,50,2);
		IdealToaster fasterToaster = IdealToaster.findFasterToaster(t1,t2);
		
		RoadJourney j1 = new RoadJourney(100,80);
		RoadJourney j2 = new RoadJourney(50,80);
		RoadJourney fasterJourney = RoadJourney.findFasterJourney(j1, j2);
		
		System.out.println(fasterToaster.toString());
		System.out.println(fasterJourney.toString());
		
		System.out.println(fasterToaster.takeMoreThanHour());
		System.out.println(fasterJourney.takeMoreThanHour());
				
	}

	
}