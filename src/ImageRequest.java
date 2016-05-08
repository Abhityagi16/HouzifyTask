
public class ImageRequest {
	private int numOfRequest, responseTime, totalTime;
	private String request;
	public ImageRequest(String request, int responseTime, int totalTime) {
		this.request = request;
		this.responseTime = responseTime;
		this.totalTime = totalTime;
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
	
	public int getResponseTime() {return responseTime;}
	
	public void setNumOfRequests(int num) {
		this.numOfRequest = num;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ImageRequest) {
			return ((ImageRequest)obj).getRequest().equals(getRequest());
		}
		else return false;
	}
	
	public void addResponseTime(int time) {
		responseTime += time;
	}
	
	public void addTotalTime(int time) {
		totalTime += time;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "Path : " + request + "\n Number of requests : " + numOfRequest + "\n Average response time : " + (float)responseTime/numOfRequest
				+ "\nAverage total time : " + (float)totalTime/numOfRequest;
		return str;
	}

	public int hashCode(){
		return request.hashCode();
    }
}
