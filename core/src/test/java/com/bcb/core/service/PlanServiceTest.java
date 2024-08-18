package com.bcb.core.service;

import com.bcb.core.entity.Plan;
import com.bcb.core.repository.PlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PlanServiceTest {
    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private PlanService planService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePlanSuccessfully() {
        Plan plan = new Plan("POST_PAID", 2000.0, 0.0);

        when(planRepository.save(any(Plan.class))).thenReturn(plan);

        Plan savedPlan = planService.savePlan(plan);

        assertNotNull(savedPlan);
        assertEquals("POST_PAID", savedPlan.getType());
        assertEquals(2000.0, savedPlan.getLimit());
    }

    @Test
    void testGetPlanByIdSuccessfully() {
        UUID planId = UUID.randomUUID();
        Plan plan = new Plan("POST_PAID", 2000.0, 0.0);

        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));

        Optional<Plan> retrievedPlan = planService.getPlanById(planId);

        assertTrue(retrievedPlan.isPresent());
        assertEquals("POST_PAID", retrievedPlan.get().getType());
    }

    @Test
    void testGetPlanByIdNotFound() {
        UUID planId = UUID.randomUUID();

        when(planRepository.findById(planId)).thenReturn(Optional.empty());

        Optional<Plan> retrievedPlan = planService.getPlanById(planId);

        assertFalse(retrievedPlan.isPresent());
    }

    @Test
    void testUpdatePlanSuccessfully() {
        UUID planId = UUID.randomUUID();
        Plan existingPlan = new Plan("POST_PAID", 2000.0, 0.0);
        Plan updatedPlan = new Plan("PRE_PAID", 0.0, 500.0);

        when(planRepository.findById(planId)).thenReturn(Optional.of(existingPlan));
        when(planRepository.save(any(Plan.class))).thenReturn(updatedPlan);

        Plan result = planService.updatePlan(planId, updatedPlan);

        assertNotNull(result);
        assertEquals("PRE_PAID", result.getType());
        assertEquals(500.0, result.getCredits());
    }

    @Test
    void testUpdatePlanNotFound() {
        UUID planId = UUID.randomUUID();
        Plan updatedPlan = new Plan("PRE_PAID", 0.0, 500.0);

        when(planRepository.findById(planId)).thenReturn(Optional.empty());

        Plan result = planService.updatePlan(planId, updatedPlan);

        assertNull(result);
    }
}
