package myteam.project4.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FoodServiceRequest extends ServiceRequest{
    @NotNull
    private String time;
}
