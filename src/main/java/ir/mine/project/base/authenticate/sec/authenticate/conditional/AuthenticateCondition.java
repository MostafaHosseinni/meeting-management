package ir.mine.project.base.authenticate.sec.authenticate.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class AuthenticateCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context,
			AnnotatedTypeMetadata metadata) {
		if (context.getEnvironment() != null) {
			MultiValueMap<String, Object> attrs = metadata
					.getAllAnnotationAttributes(AuthenticationMechanism.class
							.getName());
			if (attrs != null) {
				for (Object value : attrs.get("value")) {
					AuthenticationType type = (AuthenticationType) value;
					String property = context.getEnvironment().getProperty(
							"authentication.mechanism");
					if (property == null) {
						property = "BASIC";
					}
					if (type.toString().equalsIgnoreCase(property)) {
						return true;
					}
				}
				return false;
			}
		}
		return true;
	}

}
