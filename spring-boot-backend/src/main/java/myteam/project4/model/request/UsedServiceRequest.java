package myteam.project4.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class UsedServiceRequest {
    @NotNull
    private Long companyId;
    @NotNull
    private Long ServiceId;

}
