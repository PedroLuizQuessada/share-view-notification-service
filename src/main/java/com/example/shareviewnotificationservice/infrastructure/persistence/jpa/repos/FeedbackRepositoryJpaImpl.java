package com.example.shareviewnotificationservice.infrastructure.persistence.jpa.repos;

import com.example.shareviewnotificationservice.datasources.FeedbackDataSource;
import dtos.FeedbackDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import mappers.FeedbackJpaDtoMapper;
import models.FeedbackJpa;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("jpa")
public class FeedbackRepositoryJpaImpl implements FeedbackDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FeedbackDto> findFeedbacksFromWeek() {
        Date startDate = Date.valueOf(LocalDate.now().minusDays(6));
        Date endDate = Date.valueOf(LocalDate.now());

        Query query = entityManager.createQuery("SELECT DISTINCT feedback FROM FeedbackJpa feedback WHERE feedback.evaluationDate >= :startDate AND feedback.evaluationDate <= :endDate", FeedbackJpa.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<FeedbackJpa> feedbackJpaList = query.getResultList();
        return feedbackJpaList.stream().map(FeedbackJpaDtoMapper::toFeedbackDto).collect(Collectors.toList());
    }
}
