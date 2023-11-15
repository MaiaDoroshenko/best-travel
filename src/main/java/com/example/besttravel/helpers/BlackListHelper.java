package com.example.besttravel.helpers;

import com.example.besttravel.exception.ForbiddenCustomerException;
import org.springframework.stereotype.Component;

@Component
public class BlackListHelper {
    public void isInBlackListCustomer(String customerId){
        if(customerId.equals("GOTW771012HMRGR087")){
            throw new ForbiddenCustomerException();
        }
    }

}
