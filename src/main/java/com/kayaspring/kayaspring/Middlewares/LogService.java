package com.kayaspring.kayaspring.Middlewares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LogService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void log(String logLevel, String logMessage) {

        logLevel = logLevel.length() > 10 ? logLevel.substring(0, 10) : logLevel;

        String sql = "INSERT INTO logs (log_level, log_message, created_at) VALUES (?, ?, NOW())";
        jdbcTemplate.update(sql, logLevel, logMessage);
    }
}
