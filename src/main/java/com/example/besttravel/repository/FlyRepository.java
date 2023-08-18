package com.example.besttravel.repository;

import com.example.besttravel.entity.FlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FlyRepository extends JpaRepository<FlyEntity,Long> {
    @Query(value = "SELECT f FROM fly f where f.price < :price")
    List<FlyEntity>selectLessPrice( @Param("price")BigDecimal price);
    @Query("SELECT f FROM fly f where f.price between :min and :max")
    List<FlyEntity>selectBetweenPrice(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query("SELECT f FROM fly f where f.originName = :origin and f.destinyName= :destiny")
    List<FlyEntity>selectOriginDestiny(@Param("origin") String origin ,@Param("destiny") String destiny);

    @Query("SELECT f FROM fly f join fetch f.tickets t where t.id= :id")
    Optional<FlyEntity>findByTicketId(UUID id);

}
