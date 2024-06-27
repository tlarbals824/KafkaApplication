package com.sim.mysql

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan("com.sim.mysql")
@EnableJpaRepositories("com.sim.mysql")
class JpaConfig {
}