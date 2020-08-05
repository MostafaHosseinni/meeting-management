package ir.mine.project.base.authenticate.sec.authenticate.user.shared.dto;


import java.io.Serializable;
import java.util.List;

import ir.mine.project.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the Role entity.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoleDTO extends BaseDTO<Long> implements Serializable {

    private Long id;

    private String name;

    private String description;


    private List<OperationDTO> operations;

}
