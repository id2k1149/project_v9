package org.id2k1149.project_v9.util;

import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.to.AnswerTo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnswerUtil {

    public static List<AnswerTo> getTos(List<Answer> answers, List<Info> infoList) {
        Map<Long, List<Info>> infoGroupedByAnswerId = infoList.stream()
                .collect(Collectors.groupingBy(info -> info.getAnswer().getId()));

        return answers.stream()
                .map(answer -> createTo(answer, infoGroupedByAnswerId.get(answer.getId())))
                .collect(Collectors.toList());
    }

    private static AnswerTo createTo(Answer answer, List<Info> infoList) {
        return new AnswerTo(answer.getId(), answer.getTitle(), infoList);
    }
}
