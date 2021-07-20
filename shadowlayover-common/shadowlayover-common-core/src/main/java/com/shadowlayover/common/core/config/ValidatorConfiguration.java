package com.shadowlayover.common.core.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/20-20:07
 * @desc: hibernatevalidator配置
 * </pre>
 */
@Configuration(proxyBeanMethods = false)
public class ValidatorConfiguration {
    
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}
