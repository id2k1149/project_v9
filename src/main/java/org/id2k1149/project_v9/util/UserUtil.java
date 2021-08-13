package org.id2k1149.project_v9.util;

import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.model.Voter;
import org.id2k1149.project_v9.to.UserTo;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    public static List<UserTo> getUsersTo(User user, List<Voter> voterList) {

        List<UserTo> list = new ArrayList<>();
//        for (Voter voter : voterList) {
//            if (voter.getUser() == user) {
//
//                UserTo userTo = new UserTo(user.getId(), user.getUsername(), );
//                list.add(userTo);
//            }
//        }

        return list;
    }
}
