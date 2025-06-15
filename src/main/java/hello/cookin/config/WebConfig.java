package hello.cookin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/login");  // 루트는 로그인으로 리디렉션

        registry.addViewController("/login").setViewName("forward:/login.html");
        registry.addViewController("/register").setViewName("forward:/register.html");
        registry.addViewController("/fridge").setViewName("forward:/fridge.html");
        registry.addViewController("/mypage").setViewName("forward:/mypage.html");
        registry.addViewController("/edit").setViewName("forward:/edit.html");
        registry.addViewController("/add-ingredient").setViewName("forward:/add_ingredient.html");  // ❗ 이름 변환
    }
}
