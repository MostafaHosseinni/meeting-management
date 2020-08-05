package ir.mine.project.base.fileManagment;//package vezarat.behdasht.yas.base.fileManagment;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.*;
//import org.springframework.core.env.Environment;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//
//
//@Configuration
//@ComponentScan("vezarat.behdasht.yas.base.fileManagment.server")
//@PropertySources(value = {@PropertySource("classpath:filemanagement.properties")})
//public class FileConfig {
//
//    @Autowired
//    Environment environment;
//
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver result = new CommonsMultipartResolver();
//        String fileUploadMaxSize = environment.getProperty("file.upload.maxSize");
//        if (fileUploadMaxSize.toLowerCase().endsWith("b")) {
//            String substring = fileUploadMaxSize.substring(0, fileUploadMaxSize.length() - 1);
//            result.setMaxUploadSize(Long.valueOf(substring));
//        } else if (fileUploadMaxSize.toLowerCase().endsWith("k")) {
//            String substring = fileUploadMaxSize.substring(0, fileUploadMaxSize.length() - 1);
//            result.setMaxUploadSize(Long.valueOf(substring) * 1024);
//        } else if (fileUploadMaxSize.toLowerCase().endsWith("m")) {
//            String substring = fileUploadMaxSize.substring(0, fileUploadMaxSize.length() - 1);
//            result.setMaxUploadSize(Long.valueOf(substring) * 1024 * 1024);
//        } else if (fileUploadMaxSize.toLowerCase().endsWith("g")) {
//            String substring = fileUploadMaxSize.substring(0, fileUploadMaxSize.length() - 1);
//            result.setMaxUploadSize(Long.valueOf(substring) * 1024 * 1024 * 1024);
//        } else {
//            result.setMaxUploadSize(Long.valueOf(fileUploadMaxSize));
//        }
//        return result;
//    }
//}
