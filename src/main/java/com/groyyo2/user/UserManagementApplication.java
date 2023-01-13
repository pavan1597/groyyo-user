package com.groyyo2.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class UserManagementApplication {

   public static void main( String[] args ) {
      SpringApplication.run(UserManagementApplication.class, args);
   }

}
