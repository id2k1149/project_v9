package org.id2k1149.project_v9.to;

import java.beans.ConstructorProperties;
import java.util.List;

public class AnswerTo extends BaseTo {
    private final String title;

    private final List<InfoTo> infoTo;

    @ConstructorProperties({"id", "info"})
    public AnswerTo(Long id,
                    String title,
                    List<InfoTo> infoTo) {
        super(id);
        this.title = title;
        this.infoTo = infoTo;
    }

    public String getTitle() {
        return title;
    }

    public List<InfoTo> getInfoTo() {
        return infoTo;
    }

    @Override
    public String toString() {
        return "AnswerTo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", infoTo=" + infoTo +
                '}';
    }
}
