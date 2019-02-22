package com.l.marc.tinderpi.Cartas;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class Spot {
    private static int counter = 0;

    public long id;
    public String name;
    public String city;
    public String url;

    public Spot(String name, String city, String url) {
        this.id = counter++;
        this.name = name;
        this.city = city;
        this.url = url;
    }
}

