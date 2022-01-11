package myteam.project4.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ServiceRequest {

    @NotNull
    private String name;
    private String type;
    @NotNull
    private Float price;
}
