package com.risaleinurdanvecizeler.quotesservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Quotes {
    private String quote;
    private String source;
    private List<String> category;

}
