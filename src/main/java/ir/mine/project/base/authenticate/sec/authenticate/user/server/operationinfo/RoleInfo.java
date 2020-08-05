package ir.mine.project.base.authenticate.sec.authenticate.user.server.operationinfo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleInfo {
    public String name;
    public String description;
}
