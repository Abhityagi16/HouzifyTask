import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseLogFile {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter folder path");
		
		try {
			String path = br.readLine();
			System.out.println("Enter time range separated by space(Ex: 14141414 15151515515)");
			String[] time = br.readLine().split("\\s+");
			System.out.println("Enter the number of items to include in output ");
			int n = Integer.parseInt(br.readLine());
			String option = args[0];
			
			
			
			
			HashMap<String, ImageRequest> map = new HashMap<>();
			HashMap<String, Requestor> userMap = new HashMap<>();
			PriorityQueue<ImageRequest> heap = new PriorityQueue<>(n, new Comparator<ImageRequest>() {

				@Override
				public int compare(ImageRequest o1, ImageRequest o2) {
					// TODO Auto-generated method stub
					return o1.getNumOfRequests()-o2.getNumOfRequests();
				}
			});
			
			PriorityQueue<Requestor> userHeap = new PriorityQueue<>(n, new Comparator<Requestor>() {

				@Override
				public int compare(Requestor o1, Requestor o2) {
					// TODO Auto-generated method stub
					return o1.getNumberOfImages()-o2.getNumberOfImages();
				}
			});
			if(option.equals("1")) {
				//Setting null so they can be garbage collected
				userMap = null;
				userHeap = null;
			}
			else {
				map = null;
				heap = null;
			}
			File folder = new File(path);
			for (File fileEntry : folder.listFiles()) {
				if (fileEntry.isFile()) {
					BufferedReader fileReader = new BufferedReader(new FileReader(fileEntry));
					String line = fileReader.readLine();
					while(line != null) {
//							System.out.println(line);
						String regex = "timestamp";
						int index = line.indexOf(regex);
						if(index != -1) {
							String jsonString = line.substring(index-2);
							JSONObject json = new JSONObject(jsonString);
							int timestamp = json.getInt("timestamp");
							if(timestamp <= Integer.parseInt(time[1]) && timestamp >= Integer.parseInt(time[0])) {
								String requestor = json.getString("remote_addr");
								int responseTime = json.getInt("response_time");
								int totalTime = json.getInt("total_time");
								String request = json.getString("http_request");
								String[] requestParams = request.split("\\s+");
								String imagePath = requestParams[1];
								
								if(imagePath.length() > 5 && imagePath.substring(imagePath.length()-5).equals(".jpeg")) {
									if(option.equals("1")) {
										ImageRequest imageRequest = new ImageRequest(imagePath, responseTime, totalTime);
										if(map.containsKey(imagePath)) {
											ImageRequest temp = map.get(imagePath);
											temp.incrementRequests();
											temp.addResponseTime(responseTime);
											temp.addTotalTime(totalTime);
											
											ImageRequest head = heap.peek();
											if(head.getNumOfRequests() < temp.getNumOfRequests()) {
												//This will remove the object when it finds same imagePath as I've overriden equal() method of ImageRequest class
												boolean removed = heap.remove(imageRequest);			//If heap doesn't contain the element then remove() method will return false but its doesn't matter
												if(!removed) {
													heap.poll();
												}
												imageRequest.setNumOfRequests(temp.getNumOfRequests());
												heap.add(imageRequest);
												
											}
										}
										else {
											
											map.put(imagePath, imageRequest);
											
											if(heap.isEmpty() || heap.size() < n) heap.add(imageRequest);
										}
									}
									else {
										Requestor req = new Requestor(requestor, responseTime, totalTime);
										if(userMap.containsKey(requestor)) {
											Requestor temp = userMap.get(requestor);
											temp.incrementImages();
											temp.addResponseTime(responseTime);
											temp.addTotalTime(totalTime);
											
											Requestor head = userHeap.peek();
											if(head.getNumberOfImages() < temp.getNumberOfImages()) {
												//This will remove the object when it finds same imagePath as I've overriden equal() method of ImageRequest class
												boolean removed = userHeap.remove(req);			//If heap doesn't contain the element then remove() method will return false but its doesn't matter
												if(!removed) {
													userHeap.poll();
												}
												req.setNumberOfImages(temp.getNumberOfImages());
												userHeap.add(req);
												
											}
										}
										else {
											
											userMap.put(requestor, req);
											
											if(userHeap.isEmpty() || userHeap.size() < n) userHeap.add(req);
										}
									}
									
								}
							}
							
						}
						line = fileReader.readLine();
						
						
					}
			    }
			    
				
				System.out.println("Top " + n + " image request details are: ");
				int x = 1;
				if(option.equals("1")) {
					while(!heap.isEmpty()) {
						ImageRequest head = heap.poll();
						System.out.println(x + ". " +head.toString());	//Top N will be printed from least to highest
						x++;
					}
				}
				else {
					while(!userHeap.isEmpty()) {
						Requestor head = userHeap.poll();
						System.out.println(x + ". " +head.toString());	//Top N will be printed from least to highest
						x++;
					}
				}
				
				
				
				
			}
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
