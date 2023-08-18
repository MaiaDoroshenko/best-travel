package com.example.besttravel.util;

import java.time.LocalDateTime;
import java.util.Random;

public class BestTravelUtil {//clases de utileria deben llevar solo metodos estaticos
    private static final Random random=new Random();
    public static LocalDateTime getRandomSoon(){
        var randomHours=random.nextInt(5-2)+2;
        var now=LocalDateTime.now();
        return now.plusHours(randomHours);// a la hora actual le suma la hora generada con Random
    }
    public static LocalDateTime getRandomLater(){
        var randomHours=random.nextInt(12-6)+6;
        var now=LocalDateTime.now();
        return now.plusHours(randomHours);// a la hora actual le suma la hora generada con Random
    }


}
