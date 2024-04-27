package com.Swagger_Document;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public  class search_output_pojo {
	
	private int status;
    private String message;
    private ArrayList<SearchList> data;
    private String currency;

}