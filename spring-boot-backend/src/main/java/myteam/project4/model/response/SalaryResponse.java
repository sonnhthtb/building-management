package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class SalaryResponse implements Serializable {
    private Long id;
    private String level;
    private String position;
    private Float salary;


}
