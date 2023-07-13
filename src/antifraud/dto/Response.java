package antifraud.dto;

import antifraud.models.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private Status result;

}
