package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.NotFoundException;
import org.id2k1149.project_v9.model.Description;
import org.id2k1149.project_v9.repository.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.javafaker.Faker;

@Service
@Transactional
public class DescriptionService {

    private final Faker faker = new Faker();

    private final DescriptionRepository descriptionRepository;

    @Autowired
    public DescriptionService(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    public List<Description> getDescriptions() {
        return descriptionRepository.findAll();
    }

    public Map<Description, BigDecimal> makeMap() {
        Map<Description, BigDecimal> descriptionMap = new HashMap<>();
        List<Description> allDescription = getDescriptions();

        Random random = new Random();
        int max = 5;
        int min = 2;
        int numberOfElements = random.nextInt((max - min) + 1) + min;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = random.nextInt(allDescription.size());
            Description randomDescription = allDescription.get(randomIndex);
            allDescription.remove(randomIndex);

            BigDecimal digitalInfo = BigDecimal.valueOf(Double.valueOf(faker.commerce().price(10, 100)));
            descriptionMap.put(randomDescription, digitalInfo);
        }

        return descriptionMap;
    }

    public List<Description> getAllDescription() {
        return descriptionRepository.findAll();
    }

    public Description getDescription(Long id) {
        if (descriptionRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exist");
        }
        return descriptionRepository.getById(id);
    }

    public void addDescription(Description newDescription) {
        descriptionRepository.save(newDescription);
    }

    public void updateDescription(Description description, Long id) {
    }

    public void deleteDescription(Long id) {
        if (descriptionRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exists");
        }
        descriptionRepository.deleteById(id);

    }
}
