package com.jss.abhi.zealicon.utils;

import java.util.HashMap;
import java.util.Map;

public class Society {

    public static  Map<Integer,String> society = new HashMap<Integer,String>(){
        {
            put(1, "NCS");
            put(2, "ACE");
            put(3, "Electrique");
            put(4, "Quizzoc");
            put(5, "SEED");
            put(6, "Photography Club");
            put(7, "Verve");
            put(8, "Qunata");
            put(9, "YFAC");
            put(10, "Origo");
            put(11, "Impetus");
            put(12,"Connoisseur");
            put(13, "Oorja");
            put(14, "Yantrashilpa");
            put(15, "Optimist");
            put(16, "MMIL");
            put(17, "Spice");
            put(18, "EDC");
            put(19, "DSC");
            put(20, "Lingua Franca");
            put(21, "Illuminati");

        }
    };

    public static String getSociety(int societyId){
        return society.get(societyId);
    }


}
