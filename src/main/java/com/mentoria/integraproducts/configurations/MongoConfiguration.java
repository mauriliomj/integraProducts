package com.mentoria.integraproducts.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {
    "com.mentoria.integraproducts.gateways.outputs.mongodb.repositories"})
public class MongoConfiguration {

}
