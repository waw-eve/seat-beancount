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

package com.waw_eve.seat.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Provide pagination urls for navigation
 */
@Schema(description = "Provide pagination urls for navigation")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-23T10:10:39.586058800+08:00[Asia/Shanghai]")
public class ResourcePaginatedLinks {
	@SerializedName("first")
	private String first = null;

	@SerializedName("last")
	private String last = null;

	@SerializedName("prev")
	private String prev = null;

	@SerializedName("next")
	private String next = null;

	public ResourcePaginatedLinks first(String first) {
		this.first = first;
		return this;
	}

	/**
	 * First Page
	 * 
	 * @return first
	 **/
	@Schema(description = "First Page")
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public ResourcePaginatedLinks last(String last) {
		this.last = last;
		return this;
	}

	/**
	 * Last Page
	 * 
	 * @return last
	 **/
	@Schema(description = "Last Page")
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public ResourcePaginatedLinks prev(String prev) {
		this.prev = prev;
		return this;
	}

	/**
	 * Previous Page
	 * 
	 * @return prev
	 **/
	@Schema(description = "Previous Page")
	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public ResourcePaginatedLinks next(String next) {
		this.next = next;
		return this;
	}

	/**
	 * Next Page
	 * 
	 * @return next
	 **/
	@Schema(description = "Next Page")
	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResourcePaginatedLinks resourcePaginatedLinks = (ResourcePaginatedLinks) o;
		return Objects.equals(this.first, resourcePaginatedLinks.first)
				&& Objects.equals(this.last, resourcePaginatedLinks.last)
				&& Objects.equals(this.prev, resourcePaginatedLinks.prev)
				&& Objects.equals(this.next, resourcePaginatedLinks.next);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, last, prev, next);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResourcePaginatedLinks {\n");

		sb.append("    first: ").append(toIndentedString(first)).append("\n");
		sb.append("    last: ").append(toIndentedString(last)).append("\n");
		sb.append("    prev: ").append(toIndentedString(prev)).append("\n");
		sb.append("    next: ").append(toIndentedString(next)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
