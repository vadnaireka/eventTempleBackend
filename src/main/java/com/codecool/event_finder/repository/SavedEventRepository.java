package com.codecool.event_finder.repository;

import com.codecool.event_finder.entity.EventEntity;
import com.codecool.event_finder.entity.SavedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedEventRepository extends JpaRepository <SavedEventEntity, String>{

    SavedEventEntity findDistinctByIdLike(String id);

    SavedEventEntity findDistinctById(String id);
}
