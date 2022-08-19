package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CleanedResponse extends ServiceResponse implements Serializable {
    private Integer timesPerWeek;
}
