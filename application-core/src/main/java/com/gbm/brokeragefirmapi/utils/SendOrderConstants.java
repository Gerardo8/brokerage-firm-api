package com.gbm.brokeragefirmapi.utils;

import lombok.NoArgsConstructor;

import java.time.LocalTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SendOrderConstants {

    public static final LocalTime SIX_AM = LocalTime.of(6, 0, 0, 0);
    public static final LocalTime THREE_PM = LocalTime.of(15, 0, 0, 0);
}
