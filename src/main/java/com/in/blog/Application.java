package com.in.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.in.blog.config.AppConstants;
import com.in.blog.entity.Role;
import com.in.blog.repository.RoleRepo;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean  // it is used to create automatic object
	public ModelMapper modelMapper() 
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		try
		{
			Role adminRole = new Role();
			adminRole.setId(AppConstants.ADMIN_USER);
			adminRole.setName("ROLE_ADMIN");
			
			Role normalRole = new Role();
			normalRole.setId(AppConstants.NORMAL_USER);
			normalRole.setName("ROLE_NORMAL");
			
			List<Role> roles = List.of(adminRole, normalRole);
			
			List<Role> result = this.roleRepo.saveAll(roles);
			
			result.forEach(r->{
				System.out.println(r.getName());
				
			});
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
