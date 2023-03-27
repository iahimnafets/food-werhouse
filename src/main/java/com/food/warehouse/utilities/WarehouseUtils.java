package com.food.warehouse.utilities;

import com.food.warehouse.entities.Discount;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

@Service
public class WarehouseUtils {



    public static BigDecimal getDiscountedPrice(BigDecimal productPrice,
                                                BigDecimal discountPercentage ,
                                                Date discountStartDate,
                                                Date discountEndDate
    ) {

        if( !(productPrice != null  && discountPercentage != null &&
                discountStartDate != null && discountEndDate != null) ){
            return  BigDecimal.ZERO;
        }
        BigDecimal discountedPrice = BigDecimal.ZERO;

        if (theDateIsWithinRange( new Date(), discountStartDate, discountEndDate ) ) {
            BigDecimal discountAmount = productPrice.multiply(discountPercentage).divide(new BigDecimal("100"));
            discountedPrice = productPrice.subtract(discountAmount);
            discountedPrice = discountedPrice.setScale( 2 , RoundingMode.HALF_UP);
        }
        return discountedPrice;
    }


    public static boolean theDateIsWithinRange(Date dateOrder, Date startDate, Date endDate ) {
        return dateOrder.getTime() >= startDate.getTime() &&
                dateOrder.getTime() <= endDate.getTime();
    }

    public static Date getNewDate(Integer addDaysToDate){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, addDaysToDate);
        return c.getTime();
    }
}
