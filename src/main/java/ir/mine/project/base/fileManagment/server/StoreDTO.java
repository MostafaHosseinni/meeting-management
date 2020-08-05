package ir.mine.project.base.fileManagment.server;

import ir.mine.project.base.domin.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreDTO extends BaseEntity<Long> {
    private String fileName;
    private String originalFileName;
    private String categoryTreeName;
    private String categoryDisplayName;
    private String contentType;
    private Long fileSize;
    private String description;
    private String fileOnDiskPath;
}
