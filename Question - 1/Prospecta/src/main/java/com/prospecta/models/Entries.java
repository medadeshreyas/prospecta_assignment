package com.prospecta.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entries {

	@Id
	private Integer id;

	@JsonProperty("API")
	private String api;

	@JsonProperty("Link")
	private String link;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("Auth")
	private String auth;

	@JsonProperty("Https")
	private boolean HTTPS;

	@JsonProperty("Cors")
	private String cors;

	@JsonProperty("Category")
	private String category;

}
