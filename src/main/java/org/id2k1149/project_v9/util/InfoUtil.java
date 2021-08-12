package org.id2k1149.project_v9.util;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.to.InfoTo;

import java.util.List;
import java.util.stream.Collectors;

public class InfoUtil {
    public static List<InfoTo> getInfoTo(Answer answer, List<Info> infoList) {
        return infoList
                .stream()
                .filter(info -> info.getAnswer() == answer)
                .map(info -> new InfoTo(info.getId(), info.getDetails()))
                .collect(Collectors.toList());
    }
}
