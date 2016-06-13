package com.wbd.seniutsagan.main;


import com.wbd.seniutsagan.dto.KlientDTO;
import com.wbd.seniutsagan.service.KlientService;

public class Singleton {
 private static final Singleton INSTANCE = new Singleton();

    private KlientDTO loggedInCustomer;
    private KlientService klientService;

    private Singleton() {
        klientService = new KlientService();
        loggedInCustomer = klientService.getKlient("email@testowy.pl","test"); //FOR TESTS ONLY!!!
    }

    private static Singleton getInstance() {
        return INSTANCE;
    }

    public static KlientDTO getLoggedInCustomer() {
        return Singleton.getInstance().loggedInCustomer;
    }

    public static void setLoggedInCustomer(KlientDTO loggedInCustomer) {
        Singleton.getInstance().loggedInCustomer = loggedInCustomer;
    }
}
