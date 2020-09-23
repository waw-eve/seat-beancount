# WalletApi

All URIs are relative to *https://seat.waw-eve.com/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**seatApiHttpControllersApiv2CharacterControllerGetWalletJournal**](WalletApi.md#seatApiHttpControllersApiv2CharacterControllerGetWalletJournal) | **GET** /v2/character/wallet-journal/{character_id} | Get a paginated wallet journal for a character
[**seatApiHttpControllersApiv2CharacterControllerGetWalletTransactions**](WalletApi.md#seatApiHttpControllersApiv2CharacterControllerGetWalletTransactions) | **GET** /v2/character/wallet-transactions/{character_id} | Get paginated wallet transactions for a character
[**seatApiHttpControllersApiv2CorporationControllerGetWalletJournal**](WalletApi.md#seatApiHttpControllersApiv2CorporationControllerGetWalletJournal) | **GET** /v2/corporation/wallet-journal/{corporation_id} | Get a paginated wallet journal for a corporation
[**seatApiHttpControllersApiv2CorporationControllerGetWalletTransactions**](WalletApi.md#seatApiHttpControllersApiv2CorporationControllerGetWalletTransactions) | **GET** /v2/corporation/wallet-transactions/{corporation_id} | Get paginated wallet transactions for a corporation

<a name="seatApiHttpControllersApiv2CharacterControllerGetWalletJournal"></a>
# **seatApiHttpControllersApiv2CharacterControllerGetWalletJournal**
> InlineResponse20017 seatApiHttpControllersApiv2CharacterControllerGetWalletJournal(characterId)

Get a paginated wallet journal for a character

Returns a wallet journal

### Example
```java
// Import classes:
//import com.waw_eve.seat.client.invoker.ApiClient;
//import com.waw_eve.seat.client.invoker.ApiException;
//import com.waw_eve.seat.client.invoker.Configuration;
//import com.waw_eve.seat.client.invoker.auth.*;
//import com.waw_eve.seat.client.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: ApiKeyAuth
ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
ApiKeyAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//ApiKeyAuth.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
Integer characterId = 56; // Integer | Character id
try {
    InlineResponse20017 result = apiInstance.seatApiHttpControllersApiv2CharacterControllerGetWalletJournal(characterId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#seatApiHttpControllersApiv2CharacterControllerGetWalletJournal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **characterId** | **Integer**| Character id |

### Return type

[**InlineResponse20017**](InlineResponse20017.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="seatApiHttpControllersApiv2CharacterControllerGetWalletTransactions"></a>
# **seatApiHttpControllersApiv2CharacterControllerGetWalletTransactions**
> InlineResponse20018 seatApiHttpControllersApiv2CharacterControllerGetWalletTransactions(characterId)

Get paginated wallet transactions for a character

Returns wallet transactions

### Example
```java
// Import classes:
//import com.waw_eve.seat.client.invoker.ApiClient;
//import com.waw_eve.seat.client.invoker.ApiException;
//import com.waw_eve.seat.client.invoker.Configuration;
//import com.waw_eve.seat.client.invoker.auth.*;
//import com.waw_eve.seat.client.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: ApiKeyAuth
ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
ApiKeyAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//ApiKeyAuth.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
Integer characterId = 56; // Integer | Character id
try {
    InlineResponse20018 result = apiInstance.seatApiHttpControllersApiv2CharacterControllerGetWalletTransactions(characterId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#seatApiHttpControllersApiv2CharacterControllerGetWalletTransactions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **characterId** | **Integer**| Character id |

### Return type

[**InlineResponse20018**](InlineResponse20018.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="seatApiHttpControllersApiv2CorporationControllerGetWalletJournal"></a>
# **seatApiHttpControllersApiv2CorporationControllerGetWalletJournal**
> InlineResponse20026 seatApiHttpControllersApiv2CorporationControllerGetWalletJournal(corporationId)

Get a paginated wallet journal for a corporation

Returns a wallet journal

### Example
```java
// Import classes:
//import com.waw_eve.seat.client.invoker.ApiClient;
//import com.waw_eve.seat.client.invoker.ApiException;
//import com.waw_eve.seat.client.invoker.Configuration;
//import com.waw_eve.seat.client.invoker.auth.*;
//import com.waw_eve.seat.client.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: ApiKeyAuth
ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
ApiKeyAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//ApiKeyAuth.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
Integer corporationId = 56; // Integer | Corporation id
try {
    InlineResponse20026 result = apiInstance.seatApiHttpControllersApiv2CorporationControllerGetWalletJournal(corporationId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#seatApiHttpControllersApiv2CorporationControllerGetWalletJournal");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **corporationId** | **Integer**| Corporation id |

### Return type

[**InlineResponse20026**](InlineResponse20026.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="seatApiHttpControllersApiv2CorporationControllerGetWalletTransactions"></a>
# **seatApiHttpControllersApiv2CorporationControllerGetWalletTransactions**
> InlineResponse20027 seatApiHttpControllersApiv2CorporationControllerGetWalletTransactions(corporationId)

Get paginated wallet transactions for a corporation

Returns wallet transactions

### Example
```java
// Import classes:
//import com.waw_eve.seat.client.invoker.ApiClient;
//import com.waw_eve.seat.client.invoker.ApiException;
//import com.waw_eve.seat.client.invoker.Configuration;
//import com.waw_eve.seat.client.invoker.auth.*;
//import com.waw_eve.seat.client.WalletApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: ApiKeyAuth
ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
ApiKeyAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//ApiKeyAuth.setApiKeyPrefix("Token");

WalletApi apiInstance = new WalletApi();
Integer corporationId = 56; // Integer | Corporation id
try {
    InlineResponse20027 result = apiInstance.seatApiHttpControllersApiv2CorporationControllerGetWalletTransactions(corporationId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#seatApiHttpControllersApiv2CorporationControllerGetWalletTransactions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **corporationId** | **Integer**| Corporation id |

### Return type

[**InlineResponse20027**](InlineResponse20027.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

