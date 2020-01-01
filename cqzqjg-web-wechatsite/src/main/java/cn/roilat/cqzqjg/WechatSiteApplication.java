package cn.roilat.cqzqjg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 启动器
 * 
 * @author Louis
 * @date Oct 29, 2018
 */
@SpringBootApplication(scanBasePackages = { "cn.roilat.cqzqjg" })
public class WechatSiteApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(WechatSiteApplication.class, args);
	}

	@Override

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 静态资源放行
		registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/static/fonts/**").addResourceLocations("classpath:/static/fonts/");
		registry.addResourceHandler("/static/img/**").addResourceLocations("classpath:/static/img/");
		registry.addResourceHandler("/index.html").addResourceLocations("classpath:/static/index.html");
	}
}
