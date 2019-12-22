package cn.roilat.cqzqjg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动器
 * @author Louis
 * @date Oct 29, 2018
 */
@SpringBootApplication(scanBasePackages={"cn.roilat.cqzqjg"})
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
