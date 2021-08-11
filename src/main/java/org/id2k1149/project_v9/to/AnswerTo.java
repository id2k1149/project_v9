package org.id2k1149.project_v9.to;

import org.id2k1149.project_v9.model.Info;

import java.beans.ConstructorProperties;
import java.util.List;

public class AnswerTo extends BaseTo {
    private final String title;

    private final List<Info> info;

    @ConstructorProperties({"id", "title", "info"})
    public AnswerTo(Long id,
                    String title,
                    List<Info> info) {
        super(id);
        this.title = title;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public List<Info> getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "AnswerTo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", info=" + info +
                '}';
    }
}
