package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.Dish;
import org.id2k1149.project_v9.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getDish() {
        return dishRepository.findAll();
    }

    public Map<Dish, BigDecimal> makeMap() {
        Map<Dish, BigDecimal> dishMap = new HashMap<>();
        List<Dish> allDish = getDish();

        Random random = new Random();
        int max = 5;
        int min = 2;
        int numberOfElements = random.nextInt((max - min) + 1) + min;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = random.nextInt(allDish.size());
            Dish randomDish = allDish.get(randomIndex);
            allDish.remove(randomIndex);

            BigDecimal digitalInfo = BigDecimal.valueOf((random.nextInt((100 - 1) + 1) + 1) / 100);

            dishMap.put(randomDish, digitalInfo);
        }

        return dishMap;
    }
}
