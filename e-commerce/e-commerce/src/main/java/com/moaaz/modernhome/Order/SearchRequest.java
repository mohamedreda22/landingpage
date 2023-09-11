package com.moaaz.modernhome.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    @JsonFormat(pattern = "yyyy-MM-dd") // Specify the date format
    private LocalDate fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd") // Specify the date format
    private LocalDate toDate;
    private OrderStatus orderStatus;

}
