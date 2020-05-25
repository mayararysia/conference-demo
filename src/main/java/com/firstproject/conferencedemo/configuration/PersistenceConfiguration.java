package com.firstproject.conferencedemo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
//    @Bean
//    public DataSource dataSource(){
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.url("jdbc:postgresql://localhost:5432/conference");
//        builder.username("");
//        builder.password("");
//        return builder.build();
//    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
