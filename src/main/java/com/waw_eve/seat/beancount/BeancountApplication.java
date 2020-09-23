package com.waw_eve.seat.beancount;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.waw_eve.seat.client.WalletApi;
import com.waw_eve.seat.client.invoker.ApiClient;
import com.waw_eve.seat.client.invoker.ApiException;
import com.waw_eve.seat.client.invoker.Configuration;
import com.waw_eve.seat.client.model.CorporationWalletJournal;
import com.waw_eve.seat.client.model.InlineResponse20026;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KagurazakaNyaa <i@kagurazakanyaa.com>
 *
 */
@Slf4j
public class BeancountApplication {

	private static ApiClient apiClient = Configuration.getDefaultApiClient();

	private static Gson gson = new Gson();

	private static Config config = null;

	/**
	 * @param args
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
		}
		apiClient.setAccessToken(config.getToken());
		apiClient.setBasePath(config.getBaseUrl());

		WalletApi api = new WalletApi(apiClient);

		Map<String, Integer> corporationIdMap = config.getCorporationIdMap();

		for (Map.Entry<String, Integer> entry : corporationIdMap.entrySet()) {
			int page = 1;
			InlineResponse20026 resp;
			Path dir = Path.of(config.getTargetPath(), entry.getKey());
			try {
				Files.createDirectories(dir);
				do {
					resp = api.seatApiHttpControllersApiv2CorporationControllerGetWalletJournal(entry.getValue(), page);
					processData(entry.getKey(), resp.getData());
					page++;
				} while (resp.getMeta().getLastPage() >= page);
			} catch (ApiException e) {
				log.error("Get exception on calling api.", e);
			} catch (IOException e) {
				log.error("Get exception on create directory.", e);
			}
		}

	}

	private static void processData(String account, List<CorporationWalletJournal> data) {
		// TODO Auto-generated method stub

	}

}
