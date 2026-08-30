package com.exaample.backend.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class AppInfoController {

    private final JdbcTemplate jdbcTemplate;

    public AppInfoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/api/info")
    public AppInfoResponse getInfo() {

        AppData appData = jdbcTemplate.queryForObject(
                """
                SELECT app_name, admin_name
                FROM app_info
                WHERE id = 1
                """,
                (rs, rowNum) -> new AppData(
                        rs.getString("app_name"),
                        rs.getString("admin_name")
                )
        );

        return new AppInfoResponse(
                appData.appName(),
                appData.adminName(),
                Instant.now().toString()
        );
    }

    private record AppData(
            String appName,
            String adminName
    ) {}

    public record AppInfoResponse(
            String appName,
            String adminName,
            String serverTime
    ) {}
}