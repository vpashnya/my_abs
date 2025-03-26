package ru.pvn.levelup.abscore;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class Constants {
    public final DateTimeFormatter INTEGRAION_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
