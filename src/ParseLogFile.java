import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ParseLogFile {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter folder path");
		
		try {
			String path = br.readLine();
			System.out.println("Enter time range separated by space(Ex: 14141414 15151515515)");
			String[] time = br.readLine().split("//s+");
			System.out.println("Enter the number of items to include in output");
			int n = Integer.parseInt(br.readLine());
			String option = args[0];
			
			if(option.equals("1")) {
				PriorityQueue<Image> maxHeap = new PriorityQueue<>(new Comparator<Image>() {

					@Override
					public int compare(Image o1, Image o2) {
						// TODO Auto-generated method stub
						return o2.getNumOfRequests()-o1.getNumOfRequests();
					}
					
				});
				
			}
			else {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
