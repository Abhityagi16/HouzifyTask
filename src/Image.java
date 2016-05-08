
public class Image {
	private int numOfRequest;
	private String request;
	public Image(String request) {
		this.request = request;
		numOfRequest = 1;
	}
	
	public String getRequest() {
		return request;
	}
	
	public int getNumOfRequests() {
		return numOfRequest;
	}
	
	public void incrementRequests() {
		numOfRequest++;
	}
}
