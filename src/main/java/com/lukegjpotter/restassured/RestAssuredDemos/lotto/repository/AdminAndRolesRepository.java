package com.lukegjpotter.restassured.RestAssuredDemos.lotto.repository;

import com.lukegjpotter.restassured.RestAssuredDemos.lotto.model.AdminAndRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
public interface AdminAndRolesRepository extends JpaRepository<AdminAndRole, UUID> {
    AdminAndRole findByBearerToken(String bearerToken);
}
