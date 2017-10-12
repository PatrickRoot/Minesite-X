package cn.sixlab.mine.site.plugin.pages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsPluginPagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPluginPagesApplication.class, args);
	}
}