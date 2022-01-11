package myteam.project4.model.response;

import lombok.Data;

@Data
public class MonthStatCompanyResponse {
    private CompanyResponse companyResponse;
    private Float rentalPrice;
    private Float servicePrice;
    private Float totalPrice;
}
