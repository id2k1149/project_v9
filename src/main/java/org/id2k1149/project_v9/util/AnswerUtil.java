package org.id2k1149.project_v9.util;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.to.AnswerTo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerUtil {
    public static List<AnswerTo> getAnswersTo(List<Answer> answers, List<Info> infoList) {

        List<AnswerTo> answerToList = new ArrayList<>();
        for (Answer answer : answers) {
            List<Info> infoWithAnswerList = infoList.stream().filter(info -> info.getAnswer() == answer).collect(Collectors.toList());
            answerToList.add(new AnswerTo(answer.getId(), answer.getTitle(), infoWithAnswerList));
        }
        return answerToList;
    }
}
