package com.gbm.brokeragefirmapi.utils;

import java.time.LocalTime;

public class SendOrderConstants {

    private SendOrderConstants() {
    }

    public static final LocalTime SIX_AM = LocalTime.of(6, 0, 0, 0);
    public static final LocalTime THREE_PM = LocalTime.of(15, 0, 0, 0);
}
