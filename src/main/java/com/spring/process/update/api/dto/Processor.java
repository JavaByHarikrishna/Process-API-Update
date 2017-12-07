package com.spring.process.update.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Processor {
	private long pid;
	private long duration_in_sec;
	private String exePath;
	private String dateTime;
	private String duration_in_ms;
	private String owner;
}
