package com.jss.abhi.zealicon.utils;

public class EventCategory {

    public static String getCategory(int categoryId){

        String category = "";

        switch (categoryId) {
            case 1:
                category = "Coloralo";
                break;
            case 2:
                category = "Mechavoltz";
                break;
            case 3:
                category ="Play-it-on";
                break;
            case 4:
                category = "Robotiles";
                break;
            case 5:
               category = "Z-wars";
                break;
            case 6:
                category = "Coderz";
                break;
        }

        return category;
    }
}
