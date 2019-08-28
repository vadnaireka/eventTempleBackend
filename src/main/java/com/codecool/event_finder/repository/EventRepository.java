package com.codecool.event_finder.repository;

import com.codecool.event_finder.entity.EventEntity;
import com.codecool.event_finder.entity.UnifiedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository <EventEntity, String> {

    EventEntity findDistinctByIdLike(String id);

    @Query("select new com.codecool.event_finder.entity.UnifiedEventEntity(e, s.averagerating) from EventEntity e left join SavedEventEntity s on e.id = s.id")
    List<UnifiedEventEntity> searchedEvents();

}
