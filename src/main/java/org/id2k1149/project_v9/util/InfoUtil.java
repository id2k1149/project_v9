package org.id2k1149.project_v9.util;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.to.InfoTo;

import java.util.ArrayList;
import java.util.List;

public class InfoUtil {
    public static List<InfoTo> getInfoTo(Answer answer, List<Info> infoList) {
        List<InfoTo> list = new ArrayList<>();
        for (Info info : infoList) {
            if (info.getAnswer() == answer) {
                InfoTo infoTo = new InfoTo(info.getId(), info.getDate(), info.getDetails());
                list.add(infoTo);
            }
        }
        return list;
    }
}

//return infoList
//        .stream()
//        .filter(info -> info.getAnswer() == answer)
//        .map(info -> new InfoTo(info.getId(), info.getDetails()))
//        .collect(Collectors.toList());
