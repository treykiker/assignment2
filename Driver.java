/*
	Program: CustomerQueue ~ Driver Class
	Author: David Kiker
	Date: 2/29/2016
	Summary: Simulates a 60 minute time frame in a grocery store line using a Customer class and a Linked List Queue class. There is a 25% chance a customer will be added
	and a customer is removed after their service time reaches zero. The queue updates every minute.
*/
	
import java.util.Random; // Used to give a 25% chance of adding a new customer at each iteration.

public class Driver{

	public static void main(String [] args){

		int addCust = 0; // Holds a value of 1-4, if the value is 1 a new customer is added to the queue.
		int custServed = 0; // Holds the number of customers served in 60 min.
		int maxLineSize = 0; // Holds the maximum length of the queue.
		PriorityQueue cqueue = new PriorityQueue();
		
		for(int i = 0; i <= 59; i++){ // The for loop loops 60 times to simulate the 60 minutes in the store.
			Random randNum = new Random();
			addCust = randNum.nextInt(4) + 1; // The value of 1-4 is given to addCust.

			if(addCust == 1){ // If the value is 1, a new customer is created and added to the queue. A message and the queue size is displayed to the user.
				PriorityCustomer c = new PriorityCustomer();
				cqueue.addCustomer(c);
				System.out.println("New customer added! Queue length is now " + cqueue.getSize() + ".");
			}

			if(maxLineSize == 0){ // If the maxLineSize is zero, the maximum size becomes whatever the queue size is at that iteration.
				maxLineSize = cqueue.getSize();
			}
			else if(maxLineSize < cqueue.getSize()){ // If it is not zero, check to see if it is smaller than the current queue size.
													// If it is smaller, change it to the current queue size.
				maxLineSize = cqueue.getSize(); 
			}

			PriorityCustomer first = cqueue.getFirst(); // The first customer in the queue is the customer currently being serviced.

			if(cqueue.getSize() == 0){ // If the queue is empty nothing happens and a message is displayed to the user.
				System.out.println("The queue is empty, no customers serviced!");
			}
			else if(first.getServiceTime() > 0){ // If the serviceTime of the customer being serviced is greater than 0, serviceTime
												// is decremented at each iteration until it reaches 0.
				first.decServiceTime();
			}
			else{ // If the serviceTime is 0, the customer has been fully serviced and is removed from queue. The next customer's service time starts getting decremented in the next iteration
					// after the last customer is completely serviced.
				cqueue.removeCustomer();
				System.out.println("Customer serviced and removed from the queue. Queue length is now " + cqueue.getSize() + "."); // Message is displayed to user
																															// to show that the customer has been removed.
				custServed++; // Number of customers serviced is incremented.
			}
			System.out.println("---------------------------------------------------"); // Prints at the end of each iteration to show a minute has passed.
		}
		System.out.println("Total number of customers serviced: " + custServed); // At the end of the simulation, total customers serviced and maximum line length is displayed to the user.
		System.out.println("Maximum line length: " + maxLineSize);
	}
}