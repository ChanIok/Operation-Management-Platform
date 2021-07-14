package com.hrm.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advantage {
    private int product_id;
    private String title;
    private String content;
    private String img_url;
}
