package com.anitadukai.repository;

import com.anitadukai.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {

    List<StatusHistory> findByDeviceIdOrderByChangedAtDesc(Long deviceId);

}