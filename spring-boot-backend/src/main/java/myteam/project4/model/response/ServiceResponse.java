package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceResponse implements Serializable {

    private Long id;

    private String name;

    private String type;

    private Float price;
}
