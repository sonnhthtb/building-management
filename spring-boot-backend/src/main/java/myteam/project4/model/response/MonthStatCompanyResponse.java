package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class MonthStatCompanyResponse implements Serializable {
    private CompanyResponse companyResponse;
    private Float rentalPrice;
    private Float servicePrice;
    private Float totalPrice;
}
