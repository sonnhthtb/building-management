package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class MaintenanceResponse extends ServiceResponse implements Serializable {
    private Long period;
}
