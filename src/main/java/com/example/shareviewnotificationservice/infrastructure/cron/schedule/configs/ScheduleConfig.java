package com.example.shareviewnotificationservice.infrastructure.cron.schedule.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Profile("schedule")
public class ScheduleConfig {
}
