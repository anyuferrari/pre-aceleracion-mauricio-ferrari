package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDTO {
    private String title;
    private Long genreId;
    private String date;
    private String order;

    public MovieFiltersDTO(String title, Long genreId, String date, String order){
        this.title = title;
        this.genreId = genreId;
        this.date = date;
        this.order = order;
    }

    public boolean isASC() {return this.order.compareToIgnoreCase("ASC")==0;}
    public boolean isDESC() {return this.order.compareToIgnoreCase("DESC")==0;}
}
