
public class Loops {

	public static void main(String[] args) {
		
		//create a simple for-loop that prints numbers 1-10//
		for(int i = 1; i <= 10; i++) {
			System.out.println(i);
			
		}
		//create evens output
		for(int i = 1; i <= 10; i++) {
			System.out.println(i);
			i = i+1;
		}
		
		//create 10 byte values and print to console//
		byte[] b = {1, 2, 4, 8, 16, 32, 64, 127};
		
		for(int a = 0; a < b.length; a++) {
			System.out.println(b[a]);
			
		}
		
		//reverse the order of the array below and print result//
		String[] cities = {"Atlanta", "San Antonio", "San Diego", "Carlotte", "Dallas", "Los Angeles", "New York", "Orlando", "Philadelphia", "Seattle"};
		
		for(int i = 0; i < cities.length/2; i++) {
		String city = cities[i];
		
		cities[i] = cities[cities.length- 1 - i];
		cities[cities.length- 1 - i] = city;
		}
		
		for(int i = 0; i < cities.length; i++) {
			System.out.println(cities[i]);
			
		
		}
	}
}
