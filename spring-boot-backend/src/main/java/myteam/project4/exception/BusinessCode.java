package myteam.project4.exception;

import org.springframework.http.HttpStatus;

public class BusinessCode {

    private BusinessCode() {
    }
    public static final ErrorResponse SUCCESS =
            new ErrorResponse("SUCCESS-01", "SUCCESS", HttpStatus.OK);

    public static final ErrorResponse INTERNAL_SERVER =
            new ErrorResponse("INTERNAL-SERVER", "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ErrorResponse NOT_FOUND_COMPANY_EMPLOYEE =
            new ErrorResponse("NOT_FOUND_COMPANY_EMPLOYEE", "Not found company employee with id", HttpStatus.NOT_FOUND);

    public static final ErrorResponse NOT_FOUND_BUILDING_EMPLOYEE =
            new ErrorResponse("NOT_FOUND_BUILDING_EMPLOYEE", "Not found building employee with id", HttpStatus.NOT_FOUND);

    public static final ErrorResponse NOT_FOUND_COMPANY =
            new ErrorResponse("NOT_FOUND_COMPANY", "Not found company with id", HttpStatus.NOT_FOUND);
    // Service Error Response
    public static final ErrorResponse NOT_FOUND_CURRENT_SERVICE =
            new ErrorResponse("NOT_FOUND_CURRENT_SERVICE", "Not found current service", HttpStatus.NOT_FOUND);

    public static final ErrorResponse NOT_FOUND_SALARY =
            new ErrorResponse("NOT FOUND SALARY", "Not found salary", HttpStatus.NOT_FOUND);

    public static final ErrorResponse COMPANY_EMPLOYEE_EXISTED =
            new ErrorResponse("COMPANY_EMPLOYEE_EXISTED", "Company employee existed", HttpStatus.BAD_REQUEST);

    public static final ErrorResponse BUILDING_EMPLOYEE_EXISTED =
            new ErrorResponse("BUILDING_EMPLOYEE_EXISTED", "Building employee existed", HttpStatus.BAD_REQUEST);
}
