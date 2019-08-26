package com.codecool.event_finder.repository;

import com.codecool.event_finder.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository <EventEntity, String> {

    EventEntity findDistinctByIdLike(String id);

}
