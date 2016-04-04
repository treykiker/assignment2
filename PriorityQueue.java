// Check to see if max heap removes properly
// Make it to where the item currently being serviced is not changed if a new value is added that is higher
public class PriorityQueue{

	private PriorityCustomer[] queue;
	private int size;

	public PriorityQueue(){
		queue = new PriorityCustomer[60];
		size = 0;
	}

	public void addCustomer(PriorityCustomer c){
		int index = size + 1;
		queue[index] = c;

		while(index > 1){
			int parent = index / 2;
			PriorityCustomer rootValue = queue[1]; 

			if(queue[parent].getPriority() < c.getPriority()){
				queue[index] = queue[parent];
				queue[parent] = c;

				index = parent;
			}
			else if(rootValue.getPriority() < c.getPriority()){
				PriorityCustomer temp = queue[index];
					for(int i = (index - 1); i >= 2; i--){
						queue[i + 1] = queue[i];
					}
				queue[2] = temp;
			}
			else{
				break;
			}
		}
		size++;
	}

	public PriorityCustomer removeCustomer(){
		PriorityCustomer rootValue = queue[1];      //store root value to return at the end
		PriorityCustomer x = queue[size];           //store last value in heap in x
		queue[1] = x;                  //take v and move to root
		queue[size] = null;               //delete node at last position (b/c we moved it to the root)
		
		int index = 1;
		  
		while(index * 2 < size){      //is there at least one child at index
			int leftIndex = (index * 2) ;
			int rightIndex = leftIndex + 1;
			 
			PriorityCustomer leftValue = queue[leftIndex];
			PriorityCustomer rightValue = null;
			 
			if(rightIndex < size){     //there is a right child
				rightValue = queue[rightIndex];
			}
			 
			PriorityCustomer maxChild;
			int maxIndex;         //find index of and value of larger child
			if(leftValue.getPriority() >= rightValue.getPriority()){     //put in '=' so you get FIFO (swap with left child if values are the same
				maxChild = leftValue;
				maxIndex = leftIndex;
			}
			else{
				maxChild = rightValue;
				maxIndex = rightIndex;         
			}
			 
			if(x.getPriority() < maxChild.getPriority()){             //value is less than the larger child -> swap
				queue[index] = maxChild;    //perform swap with larger of two children
				queue[maxIndex] = x;
				index = maxIndex;
			}
			else{
				break;                     //value is in a valid position -> stop
			}
		}
		  
	size --;                      //update size
		  
	return rootValue;             //return old root
	}

	public int getSize(){
		return size;
	}

	public PriorityCustomer getFirst(){
		return queue[1];
	}

	public String toString(){
		return queue[1].getPriority() + " " + queue[2].getPriority() + " " + queue[3].getPriority() + " " + queue[4].getPriority() + " " + queue[5].getPriority() + " " + queue[6].getPriority();
	}

	public String toString2(){
		return queue[1].getPriority() + " " + queue[2].getPriority() + " " + queue[3].getPriority() + " " + queue[4].getPriority() + " " + queue[5].getPriority();
	}
}	