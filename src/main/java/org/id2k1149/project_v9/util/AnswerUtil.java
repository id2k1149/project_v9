package org.id2k1149.project_v9.util;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.to.AnswerTo;
import org.id2k1149.project_v9.to.InfoTo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerUtil {
    public static List<AnswerTo> getAnswersTo(List<Answer> answers, List<Info> infoList) {

        List<AnswerTo> answerToList = new ArrayList<>();
        for (Answer answer : answers) {
            List<InfoTo> infoToList = new ArrayList<>();
            for (Info info : infoList) {
                if (info.getAnswer() == answer) {
                    InfoTo infoTo = new InfoTo(info.getId(), info.getDetails());
                    infoToList.add(infoTo);
                }
            }
            answerToList.add(new AnswerTo(answer.getId(), answer.getTitle(), infoToList));
        }
        return answerToList;
    }
}
