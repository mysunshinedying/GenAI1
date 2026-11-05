package com.mycompany.demo.di.conf;
import org.springframework.context.annotation.Configuration;
import com.mycompany.demo.di.service.IProductService;
import com.mycompany.demo.di.service.ProductServiceA;

@Configuration 
public class DiConfiguration {
    //Configuration Bean은 주입하지 않음
    // @Bean
    //  public IProductService getProductService() {
    //     return new ProductServiceA();
    // }
}
