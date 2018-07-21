package com.jeonguk.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

public class PersonDTO {

	@Data
	public static class ReqPerson {
		private String personName;
	}

	@Data
	public static class ResPerson {
		private Long id;
		private String personName;
		@JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
		private LocalDateTime createdAt;
	}
}
