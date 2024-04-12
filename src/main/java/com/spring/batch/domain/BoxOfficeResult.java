package com.spring.batch.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoxOfficeResult {

    String boxofficeType;
    String showRange;
    List<KobisMovie> dailyBoxOfficeList;
    
}
