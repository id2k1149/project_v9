package org.id2k1149.project_v9.util;

import org.id2k1149.project_v9.util.exception.LateToVoteException;

import java.time.LocalTime;

public class TimeUtil {

    public static int hourLimitForVote = 10; // condition check + 1 hour

    public static void checkTime() {
        if (LocalTime.now().getHour() > hourLimitForVote) throw new LateToVoteException("You can't vote after 11am");
    }
}
