package atd.spring.server.helpers;

import atd.spring.server.gateway.StatusMonitor;

public class LoggingMonitor extends StatusMonitor {

	
	@Override
	public void start() {
		super.start();
		System.out.println("Monitor has started");
	}
	
	@Override
	public void shutdown() {
		super.shutdown();
		System.out.println("Monitor has started");
	}
	
}
