
public class SwapElements {

	public static void main(String[] args) {
		int[] elements = {1,2,3,4,5};
		
		/*Code to swap first element with the last*/
		
		//store first element//
		int first = elements[0];
		
		//swap elements//
		elements[0] = elements[elements.length-1];
		elements[elements.length-1] = first;
		
		//print elements//
		for (int i = 0; i < elements.length; i++) {
			System.out.println(elements[i]);
		}
		
		
		
		
	}
}
