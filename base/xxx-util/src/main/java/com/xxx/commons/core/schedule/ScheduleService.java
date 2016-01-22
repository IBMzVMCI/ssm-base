package com.xxx.commons.core.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduleService {

	private static ScheduledExecutorService timer = Executors
			.newSingleThreadScheduledExecutor();

	public static ScheduledExecutorService getService() {
		return timer;
	}
	
}
