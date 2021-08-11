package org.id2k1149.project_v9;

import org.springframework.util.Assert;

public interface HasId {
    Long getId();

    void setId(Long id);

    default boolean isNew() {
        return getId() == null;
    }

    default Long id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
