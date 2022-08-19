package myteam.project4.model.response;


import lombok.Getter;
import lombok.Setter;
import myteam.project4.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponse<T> implements Serializable{
    private int code;
    private T data;

    private BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> BaseResponse<T> ofSuccess(T data) {
        return new BaseResponse<>(200, data);
    }

    public static <T> BaseResponse<T> ofSuccess(HttpStatus status, T data) {
        return new BaseResponse<>(status.value(), data);
    }

    public static BaseResponse<ErrorResponse> ofFailed(ErrorResponse errorResponse) {
        return new BaseResponse<>(errorResponse.getStatus().value(), errorResponse);
    }
}
