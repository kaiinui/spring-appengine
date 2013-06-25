package com.github.marceloverdijk.springappengine.objectify.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

@Configuration
public class ObjectifyRegistrar implements ImportBeanDefinitionRegistrar {

//    @Bean
//    public OfyService ofyService() {
//        OfyService ofyService = new OfyService();
//        ObjectifyFactory factory = ofyService.factory();
//        // TODO register classes
//        return ofyService;
//    }

    /*
     * (non-Javadoc)
     * @see org.springframework.context.annotation.ImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata, org.springframework.beans.factory.support.BeanDefinitionRegistry)
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        Assert.notNull(annotationMetadata);
        Assert.notNull(registry);
        
        // TODO see RepositoryBeanDefinitionRegistrarSupport
    }
}
