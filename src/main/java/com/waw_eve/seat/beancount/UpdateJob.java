package com.waw_eve.seat.beancount;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * the job for update beancount file
 * 
 * @author KagurazakaNyaa
 *
 */
public class UpdateJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		for (BeancountService bs : BeancountService.RunningServiceList) {
			bs.update();
		}
	}

}
