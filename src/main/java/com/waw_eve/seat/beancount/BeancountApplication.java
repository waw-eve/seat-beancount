package com.waw_eve.seat.beancount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
 * @author KagurazakaNyaa
 *
 */
@Slf4j
public class BeancountApplication {

	private static ApiClient apiClient = Configuration.getDefaultApiClient();

	private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

	private static Config config = null;

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

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
				example.setTargetPath(new File("generated").getAbsolutePath());
				example.setCorporationIdMap(new HashMap<>());
				example.getCorporationIdMap().put("TEST", 100001);
				example.getCorporationIdMap().put("MEOW", 100002);
				Files.writeString(Path.of(configFile), gson.toJson(example));
			} catch (IOException e1) {
				log.error("Cannot create config file " + configFile + "\nPlease check config file.", e1);
			}
		}
		apiClient.setAccessToken(config.getToken());
		apiClient.setBasePath(config.getBaseUrl().toString());

		Path targetPath = Path.of(config.getTargetPath());
		try {
			Files.createDirectory(targetPath);
			File refType = targetPath.resolve("RefType.bean").toFile();
			if (!refType.exists()) {
				Files.copy(BeancountApplication.class.getResourceAsStream("RefType.bean"), refType.toPath());
			}
		} catch (IOException e) {
			log.error("Cannot create target path and refType file.", e);
		}

		WalletApi api = new WalletApi(apiClient);

		Map<String, Integer> corporationIdMap = config.getCorporationIdMap();

		for (Map.Entry<String, Integer> entry : corporationIdMap.entrySet()) {
			int page = 1;
			InlineResponse20026 resp;
			Path dir = targetPath.resolve(entry.getKey());
			File indexCache = dir.resolve(".index_cache").toFile();
			try {
				Files.createDirectories(dir);
				if (indexCache.exists()) {
					page = gson.fromJson(new FileReader(indexCache), Integer.class);
				}
				do {
					resp = api.seatApiHttpControllersApiv2CorporationControllerGetWalletJournal(entry.getValue(), page);
					processData(dir.resolve("page_" + page + ".bean"), entry.getKey(), resp.getData());
					page++;
				} while (resp.getMeta().getLastPage() >= page);
				Files.writeString(Path.of(indexCache.getAbsolutePath()), gson.toJson(page));
				generateIndex(dir, page);
			} catch (ApiException e) {
				log.error("Get exception on calling api.", e);
			} catch (IOException e) {
				log.error("Get exception on create directory or write data.", e);
			}
		}

	}

	private static void generateIndex(Path dir, int page) throws IOException {
		StringBuilder indexStr = new StringBuilder();
		for (int i = 1; i <= page; i++) {
			indexStr.append("include \"page_" + i + ".bean\"\n");
		}
		Files.writeString(dir.resolve("index.bean"), indexStr.toString(), StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}

	private static void processData(Path beanFile, String account, List<CorporationWalletJournal> data)
			throws IOException {
		StringBuilder ledge = new StringBuilder();
		for (CorporationWalletJournal journal : data) {
			ledge.append(dateFormat.format(journal.getDate()));
			ledge.append(" * ");
			ledge.append("\"" + journal.getDescription() + "\"");
			if (journal.getReason() != null) {
				ledge.append(" \"reason:" + journal.getReason() + "\"");
			}
			ledge.append("\n  ");
			ledge.append(account + ":" + journal.getDivision());
			ledge.append("\t\t\t\t\t\t" + journal.getAmount() + " ISK");
			ledge.append("\n  ");
			ledge.append("Assest:RefType:" + journal.getRefType());
			ledge.append("\n");
			ledge.append(dateFormat.format(journal.getDate()));
			ledge.append(" balance " + account + ":" + journal.getDivision());
			ledge.append("\t" + journal.getBalance() + " ISK\n\n");
		}
		Files.writeString(beanFile, ledge.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}

}
