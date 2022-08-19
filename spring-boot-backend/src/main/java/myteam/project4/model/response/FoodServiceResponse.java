package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class FoodServiceResponse extends ServiceResponse implements Serializable {
    private String time;
}
