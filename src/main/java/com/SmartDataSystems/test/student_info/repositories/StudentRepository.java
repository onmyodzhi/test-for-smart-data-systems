package com.SmartDataSystems.test.student_info.repositories;

import com.SmartDataSystems.test.student_info.models.Student;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying
    @Lock(LockModeType.OPTIMISTIC)
    @Query("update Student s set s.archived = true where s.id in :id")
    Integer archivingByIds(@Param("id") Long... id);
}