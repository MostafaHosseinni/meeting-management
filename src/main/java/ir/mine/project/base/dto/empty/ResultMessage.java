package ir.mine.project.base.dto.empty;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultMessage<T> {

    private T data;
//    private Long count;
//    private String message;
}
