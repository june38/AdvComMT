package work;
//5631363921 Sutatta Adisornvorakij

//isLargerThan method use to compare which one have bigger value
//isDivisibleByFive method use to check that the value is divisible by five
//isEqual method use to check that those two value is equal or not

public interface Relatable {
	    public int isLargerThan(Relatable other);
	    public boolean isDivisibleByFive(Relatable other);
	    public boolean isEqual(Relatable other);
}

