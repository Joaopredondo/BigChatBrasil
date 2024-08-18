package com.bcb.core.service;

import com.bcb.core.entity.Plan;
import com.bcb.core.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Plan savePlan(Plan plan) {
        return planRepository.save(plan);
    }

    public Optional<Plan> getPlanById(UUID id) {
        return planRepository.findById(id);
    }

    public List<Plan> listAllPlans() {
        return planRepository.findAll();
    }

    public Plan updatePlan(UUID id, Plan updatedPlan) {
        Optional<Plan> existingPlanOptional = planRepository.findById(id);

        if(existingPlanOptional.isPresent()) {
            Plan existingPlan = existingPlanOptional.get();
            existingPlan.setType(updatedPlan.getType());
            existingPlan.setLimit(updatedPlan.getLimit());
            existingPlan.setCredits(updatedPlan.getCredits());
            return planRepository.save(existingPlan);
        }
        return null;
    }

    public void deletePlan(UUID id){
        planRepository.deleteById(id);
    }
}
