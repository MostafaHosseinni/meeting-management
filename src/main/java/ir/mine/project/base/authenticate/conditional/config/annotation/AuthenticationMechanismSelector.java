package ir.mine.project.base.authenticate.conditional.config.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import ir.mine.project.base.authenticate.conditional.config.annotation.basic.BasicSecAuthenticateConfig;
import ir.mine.project.base.authenticate.sec.authenticate.conditional.AuthenticationType;

public class AuthenticationMechanismSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attrs = importingClassMetadata
                .getAllAnnotationAttributes(EnableAuthenticationMechanism.class
                        .getName());
        if (attrs != null) {
            for (Object value : attrs.get("value")) {
                AuthenticationType type = (AuthenticationType) value;
                if (AuthenticationType.BASIC.equals(type)) {
                    return new String[]{BasicSecAuthenticateConfig.class
                            .getName()};
                }
            }
        }
        return null;
    }

}
