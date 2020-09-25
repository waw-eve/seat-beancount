package com.waw_eve.seat.beancount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.waw_eve.seat.client.WalletApi;
import com.waw_eve.seat.client.invoker.ApiClient;
import com.waw_eve.seat.client.invoker.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KagurazakaNyaa
 *
 */
@Slf4j
public class BeancountApplication {

	private static ApiClient apiClient = Configuration.getDefaultApiClient();

	private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

	private static Config config = null;

	/**
	 * @param args arg[0] should be config file
	 */
	public static void main(String[] args) {
		String configFile = "config.json";
		try {
			if (args.length > 0) {
				configFile = args[0];
			}
			config = gson.fromJson(new FileReader(configFile), Config.class);
		} catch (JsonSyntaxException | JsonIOException e) {
			log.error("Get exception on parse config.\nPlease check config file.", e);
		} catch (FileNotFoundException e) {
			log.error("Cannot read config file " + configFile + "\nPlease check config file.", e);
			try {
				Config example = new Config();
				example.setBaseUrl(new URL("https://seat.example.com/api"));
				example.setToken("aaaaaaaaaAAAAA1111100000asdfg5tg");
				example.setTargetPath(new File("data").getAbsolutePath());
				example.setCorporationIdMap(new HashMap<>());
				example.getCorporationIdMap().put("TEST", 100001);
				example.getCorporationIdMap().put("MEOW", 100002);
				Files.writeString(Path.of(configFile), gson.toJson(example));
			} catch (IOException e1) {
				log.error("Cannot create config file " + configFile + "\nPlease check config file.", e1);
			}
		}
		new BeancountApplication().run();

	}

	public void run() {
		apiClient.setApiKey(config.getToken());
		apiClient.setBasePath(config.getBaseUrl().toString());

		Path targetPath = Path.of(config.getTargetPath());
		try {
			if (Files.notExists(targetPath)) {
				Files.createDirectory(targetPath);
			}
			Path refType = targetPath.resolve("RefType.bean");
			if (Files.notExists(refType)) {
				Files.copy(BeancountApplication.class.getResourceAsStream("/RefType.bean"), refType);
			}
		} catch (Exception e) {
			log.error("Cannot create target path and refType file.", e);
		}

		WalletApi api = new WalletApi(apiClient);

		Map<String, Integer> corporationIdMap = config.getCorporationIdMap();

		for (Map.Entry<String, Integer> entry : corporationIdMap.entrySet()) {
			Path dir = targetPath.resolve(entry.getKey());
			BeancountService bs = new BeancountService(api, dir, entry.getKey(), entry.getValue());
			BeancountService.RunningServiceList.add(bs);
		}

		if (config.isDaemon()) {
			SchedulerFactory sf = new StdSchedulerFactory();
			try {
				Scheduler scheduler = sf.getScheduler();
				JobDetail updateJob = JobBuilder.newJob(UpdateJob.class).withIdentity("updateJob", "default").build();
				Trigger trigger = newTrigger()
						.withIdentity("updateTrigger", "default").withSchedule(simpleSchedule()
								.withIntervalInMinutes(config.getQuartzRepeatInterval()).repeatForever())
						.forJob(updateJob).build();
				scheduler.scheduleJob(updateJob, trigger);
				scheduler.start();
			} catch (SchedulerException e) {
				log.error("Get exception on schedule jobs.", e);
			}
		} else {
			for (BeancountService bs : BeancountService.RunningServiceList) {
				bs.update();
			}
		}
	}

}
