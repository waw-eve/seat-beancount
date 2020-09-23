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
 * Squad Resource
 */
@Schema(description = "Squad Resource")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-23T11:22:13.900688700+08:00[Asia/Shanghai]")
public class SquadResource {
  @SerializedName("id")
  private Integer id = null;

  /**
   * The Squad management type
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    MANUAL("manual"),
    AUTO("auto"),
    HIDDEN("hidden");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<TypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return TypeEnum.fromValue(String.valueOf(value));
      }
    }
  }  @SerializedName("type")
  private TypeEnum type = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("logo")
  private byte[] logo = null;

  @SerializedName("description")
  private String description = null;

  public SquadResource id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * The unique identifier
   * @return id
  **/
  @Schema(description = "The unique identifier")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public SquadResource type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * The Squad management type
   * @return type
  **/
  @Schema(description = "The Squad management type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public SquadResource name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The Squad name
   * @return name
  **/
  @Schema(description = "The Squad name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SquadResource logo(byte[] logo) {
    this.logo = logo;
    return this;
  }

   /**
   * The Squad Logo
   * @return logo
  **/
  @Schema(description = "The Squad Logo")
  public byte[] getLogo() {
    return logo;
  }

  public void setLogo(byte[] logo) {
    this.logo = logo;
  }

  public SquadResource description(String description) {
    this.description = description;
    return this;
  }

   /**
   * The squad description
   * @return description
  **/
  @Schema(description = "The squad description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SquadResource squadResource = (SquadResource) o;
    return Objects.equals(this.id, squadResource.id) &&
        Objects.equals(this.type, squadResource.type) &&
        Objects.equals(this.name, squadResource.name) &&
        Arrays.equals(this.logo, squadResource.logo) &&
        Objects.equals(this.description, squadResource.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, Arrays.hashCode(logo), description);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SquadResource {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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