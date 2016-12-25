package Util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

public class HttpClientManager {
	private static boolean inited = false;
	private static MultiThreadedHttpConnectionManager connectionManager = null;
	private static HttpClient httpClient;
	public static HttpClient getHttpClient() {
		if (!inited) {
			connectionManager = new MultiThreadedHttpConnectionManager();
			int maxConnectionsPerHost = 40;
			int TotalConnections = 255;
			connectionManager.setMaxConnectionsPerHost(maxConnectionsPerHost);
			connectionManager.setMaxTotalConnections(TotalConnections);
			inited = true;
			httpClient = new HttpClient(connectionManager);
		}
		return httpClient;
	}
	
	public static void destory(){
		connectionManager.shutdownAll();
	}
}
