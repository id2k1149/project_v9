package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.DescriptionInInfo;
import org.id2k1149.project_v9.repository.DescriptionRepository;
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
public class DescriptionService {

    private final DescriptionRepository descriptionRepository;

    @Autowired
    public DescriptionService(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    public List<DescriptionInInfo> getDescriptions() {
        return descriptionRepository.findAll();
    }

    public Map<DescriptionInInfo, BigDecimal> makeMap() {
        Map<DescriptionInInfo, BigDecimal> descriptionMap = new HashMap<>();
        List<DescriptionInInfo> allDescription = getDescriptions();

        Random random = new Random();
        int max = 5;
        int min = 2;
        int numberOfElements = random.nextInt((max - min) + 1) + min;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = random.nextInt(allDescription.size());
            DescriptionInInfo randomDescription = allDescription.get(randomIndex);
            allDescription.remove(randomIndex);

            BigDecimal digitalInfo = BigDecimal.valueOf((random.nextInt((100 - 1) + 1) + 1) / 100);

            descriptionMap.put(randomDescription, digitalInfo);
        }

        return descriptionMap;
    }
}
