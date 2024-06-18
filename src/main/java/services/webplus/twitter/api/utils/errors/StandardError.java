package services.webplus.twitter.api.utils.errors;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardError {
    
    private String message;
    private Integer status;
    private Long timestamp;
}
