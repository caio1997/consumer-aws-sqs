package com.example.listener.repository;

import com.example.listener.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

    List<PhoneEntity> findByPeopleId(Long id);

}
