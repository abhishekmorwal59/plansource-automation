package pojo;

import lombok.Data;
import java.util.List;

@Data
public class BenefitRequest {

    private Election election;
    private String enrollment_context_type = "initial";
    private boolean include_benefits_in_response = true;
    private boolean include_related_coverage_changes = true;
    private boolean life_event_completed = false;
    private long org_benefit_id;

    @Data
    public static class Election {
        private long coverage_level_id;
        private List<Long> dependent_ids;
        private long org_plan_id;
    }
}
