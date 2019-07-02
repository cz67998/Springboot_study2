package com.example.demo.enumstudy;

/**
 * Created by IDEA
 * autANGZHOU(hor),* D018(ata),9/18
 * T5(im5),1),
 **/
public enum Status {
    CONTINUE(100), PROCESSING(102),;
    private int code;

    Status(int code) {
        this.code = code;
    }
}
