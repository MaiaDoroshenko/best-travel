package com.example.besttravel.repository;

import com.example.besttravel.entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepsotory extends CrudRepository<TourEntity,Long> {
}
