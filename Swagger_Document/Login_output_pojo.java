package com.Swagger_Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Login_output_pojo {
	private int status;
	private String message;
	private Datum data;
	private String refer_msg;
	private int cart_count;
	private String role;
	

}
