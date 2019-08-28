package com.codecool.event_finder.service;

import com.codecool.event_finder.entity.SavedEventEntity;
import com.codecool.event_finder.repository.RatingRepository;
import com.codecool.event_finder.repository.SavedEventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DBManipulatorTest {

    @Autowired
    DBManipulator dbManipulator;

    @Autowired
    SavedEventRepository savedEventRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Test
    @Transactional
    public void saveRating() {
        ratingRepository.deleteAll();
        savedEventRepository.save(SavedEventEntity.builder()
        .id("ABCD").build());
        dbManipulator.saveRating("ABCD", 5);
        assertThat(savedEventRepository.findDistinctByIdLike("ABCD").getRatings()).hasSize(1);
        assertThat(ratingRepository.count()).isEqualTo(1);
        assertThat(ratingRepository.findAll().get(0).getRating()).isEqualTo(5);

    }
}