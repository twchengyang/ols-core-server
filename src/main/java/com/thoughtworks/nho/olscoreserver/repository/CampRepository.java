package com.thoughtworks.nho.olscoreserver.repository;

import com.thoughtworks.nho.olscoreserver.camp.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampRepository extends JpaRepository<Camp, String> {
}
