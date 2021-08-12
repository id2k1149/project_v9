package org.id2k1149.project_v9.util;

import org.id2k1149.project_v9.util.exception.LateToVoteException;

import java.time.LocalTime;

public class TimeUtil {

    public static int hour = 10;

    public static void checkTime() {
        if (LocalTime.now().getHour() > hour) throw new LateToVoteException("You can't vote after 11am");
    }
}
