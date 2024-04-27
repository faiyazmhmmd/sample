package com.Swagger_Document;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class getAddress_outpot_pojo {
    private int status;
    private String message;
    private ArrayList<getAdressList> data;

}
