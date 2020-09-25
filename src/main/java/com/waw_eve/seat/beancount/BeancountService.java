package com.waw_eve.seat.beancount;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.threeten.bp.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
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

	private static Gson gson = new GsonBuilder().serializeNulls()
			.setLongSerializationPolicy(LongSerializationPolicy.STRING).setPrettyPrinting().create();

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
		log.info("Updating " + account + " start with page " + page);
		InlineResponse20026 resp;
		File indexCache = dir.resolve(".index_cache").toFile();
		try {
			Files.createDirectories(dir);
			if (indexCache.exists()) {
				page = gson.fromJson(new FileReader(indexCache), Integer.class);
			}
			do {
				resp = api.seatApiHttpControllersApiv2CorporationControllerGetWalletJournal(id, page);
				log.info("process data of " + account + " page " + page);
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
		log.info("Update " + account + " done.");
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
			ledge.append(formatter.format(journal.getDate()));
			ledge.append(" * ");
			ledge.append("\"" + journal.getDescription() + "\"");
			if (journal.getReason() != null) {
				ledge.append(" \"reason:" + journal.getReason() + "\"");
			}
			ledge.append("\n  ");
			ledge.append(account + ":" + journal.getDivision());
			ledge.append("\t\t\t\t\t\t" + BigDecimal.valueOf(journal.getAmount()).toPlainString() + " ISK");
			ledge.append("\n  ");
			ledge.append("Assest:RefType:" + lineToHump(journal.getRefType()));
			ledge.append("\n");
			ledge.append(formatter.format(journal.getDate()));
			ledge.append(" balance " + account + ":" + journal.getDivision());
			ledge.append("\t" + BigDecimal.valueOf(journal.getBalance()).toPlainString() + " ISK\n\n");
		}
		Files.writeString(beanFile, ledge.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
	private static Pattern linePattern = Pattern.compile("_(\\w)");
	private static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return (sb.substring(0,1).toUpperCase()+sb.substring(1)).toString();
	}

}
