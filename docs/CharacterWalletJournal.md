# CharacterWalletJournal

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** | Unique journal reference ID |  [optional]
**date** | [**OffsetDateTime**](OffsetDateTime.md) | Date and time of transaction |  [optional]
**refType** | **String** | The transaction type for the given transaction. Different transaction types will populate different attributes. Note: If you have an existing XML API application that is using ref_types, you will need to know which string ESI ref_type maps to which integer. You can look at the following file to see string-&gt;int mappings: https://github.com/ccpgames/eve-glue/blob/master/eve_glue/wallet_journal_ref.py |  [optional]
**amount** | **Double** | The amount of ISK given or taken from the wallet as a result of the given transaction. Positive when ISK is deposited into the wallet and negative when ISK is withdrawn |  [optional]
**balance** | **Double** | Wallet balance after transaction occurred |  [optional]
**reason** | **String** | The user stated reason for the transaction. Only applies to some ref_types |  [optional]
**taxReceiverId** | **Long** | The corporation ID receiving any tax paid. Only applies to tax related transactions |  [optional]
**tax** | **Double** | Tax amount received. Only applies to tax related transactions |  [optional]
**contextId** | **Long** | An ID that gives extra context to the particular transaction. Because of legacy reasons the context is completely different per ref_type and means different things. It is also possible to not have a context_id |  [optional]
**contextIdType** | [**ContextIdTypeEnum**](#ContextIdTypeEnum) | The type of the given context_id if present |  [optional]
**description** | **String** | The reason for the transaction, mirrors what is seen in the client |  [optional]
**firstParty** | [**UniverseName**](UniverseName.md) |  |  [optional]
**secondParty** | [**UniverseName**](UniverseName.md) |  |  [optional]

<a name="ContextIdTypeEnum"></a>
## Enum: ContextIdTypeEnum
Name | Value
---- | -----
STRUCTURE_ID | &quot;structure_id&quot;
STATION_ID | &quot;station_id&quot;
MARKET_TRANSACTION_ID | &quot;market_transaction_id&quot;
CHARACTER_ID | &quot;character_id&quot;
CORPORATION_ID | &quot;corporation_id&quot;
ALLIANCE_ID | &quot;alliance_id&quot;
EVE_SYSTEM | &quot;eve_system&quot;
INDUSTRY_JOB_ID | &quot;industry_job_id&quot;
CONTRACT_ID | &quot;contract_id&quot;
PLANET_ID | &quot;planet_id&quot;
SYSTEM_ID | &quot;system_id&quot;
TYPE_ID | &quot;type_id&quot;
