package com.waw_eve.seat.beancount;

import java.util.List;

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
	private String baseUrl;

	/**
	 * Access token Generated on SeAT API Token Admin (e.g
	 * SiaVjEbphVYejKxfLbCCMU7H8Gs9AAE1)
	 */
	private String token;

	/**
	 * List of corporation IDs that need to export WalletJournal
	 */
	private List<Integer> corporationIdList;

	/**
	 * List of character IDs that need to export WalletJournal
	 */
	private List<Integer> characterIdList;

	/**
	 * Target location of the generated result
	 */
	private String targetPath;
}
