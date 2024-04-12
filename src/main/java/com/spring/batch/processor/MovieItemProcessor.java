package com.spring.batch.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.spring.batch.domain.KobisMovie;
import com.spring.batch.domain.ResponseDto;

public class MovieItemProcessor implements ItemProcessor<ResponseDto, List<KobisMovie>> {

    @Override
    @Nullable
    public List<KobisMovie> process(@NonNull ResponseDto item) throws Exception {
        // TODO Auto-generated method stub

        return item.getBoxOfficeResult().getDailyBoxOfficeList();

    }
    
}
