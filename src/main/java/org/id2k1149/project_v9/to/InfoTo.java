package org.id2k1149.project_v9.to;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.Map;

public class InfoTo extends BaseTo {

    private final Map<String, BigDecimal> details;

    @ConstructorProperties({"id", "details"})
    public InfoTo(Long id,
                  Map<String, BigDecimal> details) {
        super(id);
        this.details = details;
    }

    public Map<String, BigDecimal> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return details.toString();
    }
}
