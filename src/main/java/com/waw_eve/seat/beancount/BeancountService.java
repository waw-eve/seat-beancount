package com.waw_eve.seat.beancount;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.waw_eve.seat.client.WalletApi;
import com.waw_eve.seat.client.invoker.ApiException;
import com.waw_eve.seat.client.model.CorporationWalletJournal;
import com.waw_eve.seat.client.model.InlineResponse20026;

import lombok.extern.slf4j.Slf4j;

/**
 * @author KagurazakaNyaa
 *
 */
@Slf4j
public class BeancountService {

	private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

	private WalletApi api;

	private Path dir;

	private int page = 1;

	private String account;

	private int id;

	public static List<BeancountService> RunningServiceList = new LinkedList<>();

	BeancountService(WalletApi api, Path dir, String account, int id) {
		this.api = api;
		this.dir = dir;
		this.account = account;
		this.id = id;
	}

	public void update() {
		InlineResponse20026 resp;
		File indexCache = dir.resolve(".index_cache").toFile();
		try {
			Files.createDirectories(dir);
			if (indexCache.exists()) {
				page = gson.fromJson(new FileReader(indexCache), Integer.class);
			}
			do {
				resp = api.seatApiHttpControllersApiv2CorporationControllerGetWalletJournal(id, page);
				processData(dir.resolve("page_" + page + ".bean"), resp.getData());
				page++;
			} while (resp.getMeta().getLastPage() >= page);
			Files.writeString(Path.of(indexCache.getAbsolutePath()), gson.toJson(page));
			generateIndex();
		} catch (ApiException e) {
			log.error("Get exception on calling api.", e);
		} catch (IOException e) {
			log.error("Get exception on create directory or write data.", e);
		}
	}

	private void generateIndex() throws IOException {
		StringBuilder indexStr = new StringBuilder();
		for (int i = 1; i <= page; i++) {
			indexStr.append("include \"page_" + i + ".bean\"\n");
		}
		Files.writeString(dir.resolve("index.bean"), indexStr.toString(), StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}

	private void processData(Path beanFile, List<CorporationWalletJournal> data) throws IOException {
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