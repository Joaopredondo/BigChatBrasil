package com.bcb.core.controller;

import com.bcb.core.dto.MessageDTO;
import com.bcb.core.dto.PlanDTO;
import com.bcb.core.entity.Plan;
import com.bcb.core.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<PlanDTO> createPlan(@RequestBody PlanDTO planDTO) {
        Plan plan = new Plan(planDTO.getType(), planDTO.getCredits(), planDTO.getLimit());
        Plan newPlan = planService.savePlan(plan);
        PlanDTO newPlanDTO = new PlanDTO(newPlan.getId(), newPlan.getType(), newPlan.getLimit(), newPlan.getCredits());
        return ResponseEntity.ok(newPlanDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> getPlan(@PathVariable UUID id) {
        Optional<Plan> plan = planService.getPlanById(id);
        return plan.map(value -> {
            PlanDTO planDTO = new PlanDTO(value.getId(), value.getType(), value.getLimit(), value.getCredits());
            return ResponseEntity.ok(planDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PlanDTO>> getAllPlans() {
        List<Plan> plans = planService.listAllPlans();

        List<PlanDTO> planDTOS = plans.stream()
                .map(plan -> new PlanDTO(plan.getId(), plan.getType(), plan.getLimit(), plan.getCredits()))
                .toList();
        return ResponseEntity.ok(planDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> updatePlan(@PathVariable UUID id, @RequestBody PlanDTO planDTO) {
       Plan plan = new Plan(planDTO.getType(), planDTO.getLimit(), planDTO.getCredits());
       Plan updatePlan = planService.updatePlan(id, plan);

       if(updatePlan != null) {
           PlanDTO updatePlanDTO = new PlanDTO(updatePlan.getId(), updatePlan.getType(), updatePlan.getLimit(), updatePlan.getCredits());
           return ResponseEntity.ok(updatePlanDTO);
       } else {
           return ResponseEntity.notFound().build();
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable UUID id) {
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}
