package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Diner;
import org.id2k1149.project_v9.model.Menu;
import org.id2k1149.project_v9.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InfoService {

    private final MenuRepository menuRepository;

    @Autowired
    public InfoService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Diner> getAnswers() {
        List<Menu> menuList = menuRepository.findByDate(LocalDate.now());
        List<Diner> dinersList = new ArrayList<>();
        for (Menu menu : menuList) {
            dinersList.add(menu.getDiner());
        }
        return dinersList;
    }
}
