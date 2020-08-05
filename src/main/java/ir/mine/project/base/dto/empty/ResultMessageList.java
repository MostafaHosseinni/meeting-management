package ir.mine.project.base.dto.empty;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultMessageList<T> {

    private List<T> data;
    private Long count;
//    private String message;
}
