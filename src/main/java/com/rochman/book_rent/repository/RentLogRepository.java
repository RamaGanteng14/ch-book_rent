package com.rochman.book_rent.repository;

import com.rochman.book_rent.entity.RentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentLogRepository extends JpaRepository<RentLog, String> {
}
