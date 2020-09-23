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
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * Universe Name
 */
@Schema(description = "Universe Name")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-23T10:10:39.586058800+08:00[Asia/Shanghai]")
public class UniverseName {
  @SerializedName("entity_id")
  private Long entityId = null;

  @SerializedName("name")
  private String name = null;

  /**
   * The entity type
   */
  @JsonAdapter(CategoryEnum.Adapter.class)
  public enum CategoryEnum {
    ALLIANCE("alliance"),
    CHARACTER("character"),
    CONSTELLATION("constellation"),
    CORPORATION("corporation"),
    INVENTORY_TYPE("inventory_type"),
    REGION("region"),
    SOLAR_SYSTEM("solar_system"),
    STATION("station"),
    FACTION("faction");

    private String value;

    CategoryEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static CategoryEnum fromValue(String text) {
      for (CategoryEnum b : CategoryEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<CategoryEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CategoryEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public CategoryEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return CategoryEnum.fromValue(String.valueOf(value));
      }
    }
  }  @SerializedName("category")
  private CategoryEnum category = null;

  public UniverseName entityId(Long entityId) {
    this.entityId = entityId;
    return this;
  }

   /**
   * The entity identifier
   * @return entityId
  **/
  @Schema(description = "The entity identifier")
  public Long getEntityId() {
    return entityId;
  }

  public void setEntityId(Long entityId) {
    this.entityId = entityId;
  }

  public UniverseName name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The entity name
   * @return name
  **/
  @Schema(description = "The entity name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UniverseName category(CategoryEnum category) {
    this.category = category;
    return this;
  }

   /**
   * The entity type
   * @return category
  **/
  @Schema(description = "The entity type")
  public CategoryEnum getCategory() {
    return category;
  }

  public void setCategory(CategoryEnum category) {
    this.category = category;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UniverseName universeName = (UniverseName) o;
    return Objects.equals(this.entityId, universeName.entityId) &&
        Objects.equals(this.name, universeName.name) &&
        Objects.equals(this.category, universeName.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entityId, name, category);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UniverseName {\n");
    
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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
