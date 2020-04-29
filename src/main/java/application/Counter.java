package application;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class is used to generate primary key for tables
 */

public class Counter {
	
	private static int counter = 0;
	
	public static int getCounterAndIncrement(){
		counter = counter + 1;
		return counter;
	}

}
