package ir.mine.project.base.authenticate.sec.authenticate.user.shared.dto;


import java.io.Serializable;

import ir.mine.project.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the Operation entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OperationDTO extends BaseDTO<Long> implements Serializable {


    private String name;

    private String entityName;

    private String description;

    private String groupName;

}
