package org.id2k1149.project_v9.to;

import org.id2k1149.project_v9.model.Voter;

import java.beans.ConstructorProperties;
import java.util.List;

public class UserTo extends BaseTo {
    private final String username;

    private final List<Voter> votes;

    @ConstructorProperties({"id", "info"})
    public UserTo(Long id,
                  String username,
                  List<Voter> votes) {
        super(id);
        this.username = username;
        this.votes = votes;
    }

    public String getUsername() {
        return username;
    }

    public List<Voter> getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "UserVotes{" +
                "votes=" + votes +
                '}';
    }
}
