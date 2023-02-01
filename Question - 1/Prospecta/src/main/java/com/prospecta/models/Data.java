package com.prospecta.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {

	private Integer count;

	private List<Entries> entries;

}
