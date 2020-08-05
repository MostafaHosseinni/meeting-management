package ir.mine.project.base.fileManagment.server;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

@Component
public class FileApiImpl implements FileApi {


    private final Environment environment;

    public FileApiImpl(Environment environment) {
        this.environment = environment;
    }

    private StoreDTO storeFile(StoreDTO entity, InputStream inputStream)
            throws BaseServerBusinessException {
        String categoryTreeName = entity.getCategoryTreeName();
        String storeOnDiskKey = "file." + categoryTreeName + ".store.on.disk";
        String diskPathKey = "file." + categoryTreeName + ".path";
        if (environment.containsProperty(storeOnDiskKey)) {
            String fileStoreOnDisk = environment.getProperty(storeOnDiskKey);
            Boolean storeOnDisk = Boolean.valueOf(fileStoreOnDisk);
            if (storeOnDisk) {
                String diskPath = environment.getProperty(diskPathKey);
                diskPath += System.getProperty("file.separator")
                        + entity.getFileName();
                try {
                    FileOutputStream fos = new FileOutputStream(diskPath);
                    IOUtils.copy(inputStream, fos);
                    fos.flush();
                    fos.close();
                    entity.setFileOnDiskPath(diskPath);
                } catch (Exception e) {
                    throw new BaseServerBusinessException(e.getMessage());
                }
                return new StoreDTO();
            }
        }
        StoreDTO f = new StoreDTO();

        return f;
    }
}
