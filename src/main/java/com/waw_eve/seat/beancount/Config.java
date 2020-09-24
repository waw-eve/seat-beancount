package com.waw_eve.seat.beancount;

import java.net.URL;
import java.util.Map;

import lombok.Data;

/**
 * @author KagurazakaNyaa <i@kagurazakanyaa.com>
 *
 */
@Data
public class Config {

	/**
	 * Base path of the URL (e.g https://seat.waw-eve.com/api)
	 */
	private URL baseUrl;

	/**
	 * Access token Generated on SeAT API Token Admin (e.g
	 * SiaVjEbphVYejKxfLbCCMU7H8Gs9AAE1)
	 */
	private String token;

	/**
	 * Map of corporation IDs that need to export WalletJournal<br>
	 * key=AccountName,value=corporationId<br>
	 * The account name looks like
	 * <code>Assets:[corporation name]</code><br>
	 * It will generate 7 accounts for each corporation<br>
	 * The generated account name will be like
	 * <code>Assets:[corporation name]:[1-7]</code>
	 */
	private Map<String, Integer> corporationIdMap;

	/**
	 * Target location of the generated result
	 */
	private String targetPath;
}
