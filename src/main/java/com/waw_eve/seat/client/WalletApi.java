/*
 * SeAT API
 * SeAT API Documentation. All endpoints require an API key.
 *
 * OpenAPI spec version: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.waw_eve.seat.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.waw_eve.seat.client.invoker.ApiClient;
import com.waw_eve.seat.client.invoker.ApiException;
import com.waw_eve.seat.client.invoker.ApiResponse;
import com.waw_eve.seat.client.invoker.Configuration;
import com.waw_eve.seat.client.invoker.Pair;
import com.waw_eve.seat.client.invoker.ProgressRequestBody;
import com.waw_eve.seat.client.invoker.ProgressResponseBody;
import com.waw_eve.seat.client.model.InlineResponse20026;

public class WalletApi {
	private ApiClient apiClient;

	public WalletApi() {
		this(Configuration.getDefaultApiClient());
	}

	public WalletApi(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public ApiClient getApiClient() {
		return apiClient;
	}

	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	/**
	 * Build call for
	 * seatApiHttpControllersApiv2CorporationControllerGetWalletJournal
	 * 
	 * @param corporationId           Corporation id (required)
	 * @param page                    Page number
	 * @param progressListener        Progress listener
	 * @param progressRequestListener Progress request listener
	 * @return Call to execute
	 * @throws ApiException If fail to serialize the request body object
	 */
	public com.squareup.okhttp.Call seatApiHttpControllersApiv2CorporationControllerGetWalletJournalCall(
			Integer corporationId, Integer page, final ProgressResponseBody.ProgressListener progressListener,
			final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		Object localVarPostBody = null;

		// create path and map variables
		String localVarPath = "/v2/corporation/wallet-journal/" + apiClient.escapeString(corporationId.toString());

		List<Pair> localVarQueryParams = new ArrayList<>();
		List<Pair> localVarCollectionQueryParams = new ArrayList<>();

		Map<String, String> localVarHeaderParams = new HashMap<>();

		Map<String, Object> localVarFormParams = new HashMap<>();

		localVarQueryParams.add(new Pair("page", page.toString()));

		final String[] localVarAccepts = { "application/json" };
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
		if (localVarAccept != null)
			localVarHeaderParams.put("Accept", localVarAccept);

		final String[] localVarContentTypes = {

		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
		localVarHeaderParams.put("Content-Type", localVarContentType);

		if (progressListener != null) {
			apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
				@Override
				public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain)
						throws IOException {
					com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
					return originalResponse.newBuilder()
							.body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
				}
			});
		}

		String[] localVarAuthNames = new String[] { "ApiKeyAuth" };
		return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams,
				localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
	}

	@SuppressWarnings("rawtypes")
	private com.squareup.okhttp.Call seatApiHttpControllersApiv2CorporationControllerGetWalletJournalValidateBeforeCall(
			Integer corporationId, Integer page, final ProgressResponseBody.ProgressListener progressListener,
			final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		// verify the required parameter 'corporationId' is set
		if (corporationId == null) {
			throw new ApiException(
					"Missing the required parameter 'corporationId' when calling seatApiHttpControllersApiv2CorporationControllerGetWalletJournal(Async)");
		}

		return seatApiHttpControllersApiv2CorporationControllerGetWalletJournalCall(corporationId, page,
				progressListener, progressRequestListener);

	}

	/**
	 * Get a paginated wallet journal for a corporation Returns a wallet journal
	 * 
	 * @param corporationId Corporation id (required)
	 * @param page          Page number
	 * @return InlineResponse20026
	 * @throws ApiException If fail to call the API, e.g. server error or cannot
	 *                      deserialize the response body
	 */
	public InlineResponse20026 seatApiHttpControllersApiv2CorporationControllerGetWalletJournal(Integer corporationId,
			Integer page) throws ApiException {
		ApiResponse<InlineResponse20026> resp = seatApiHttpControllersApiv2CorporationControllerGetWalletJournalWithHttpInfo(
				corporationId, page);
		return resp.getData();
	}

	/**
	 * Get a paginated wallet journal for a corporation Returns a wallet journal
	 * 
	 * @param corporationId Corporation id (required)
	 * @param page          Page number
	 * @return ApiResponse&lt;InlineResponse20026&gt;
	 * @throws ApiException If fail to call the API, e.g. server error or cannot
	 *                      deserialize the response body
	 */
	public ApiResponse<InlineResponse20026> seatApiHttpControllersApiv2CorporationControllerGetWalletJournalWithHttpInfo(
			Integer corporationId, Integer page) throws ApiException {
		com.squareup.okhttp.Call call = seatApiHttpControllersApiv2CorporationControllerGetWalletJournalValidateBeforeCall(
				corporationId, page, null, null);
		Type localVarReturnType = new TypeToken<InlineResponse20026>() {
		}.getType();
		return apiClient.execute(call, localVarReturnType);
	}

}
