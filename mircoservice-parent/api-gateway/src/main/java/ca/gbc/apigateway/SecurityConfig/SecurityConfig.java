//package ca.gbc.apigateway.SecurityConfig;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Value("${spring.security.oauth2.resourceserver.issuer-uri}")
//    private String issuerUri;
//
//    @Bean
//    public ReactiveJwtDecoder jwtDecoder() {
//        return ReactiveJwtDecoders.fromIssuerLocation(issuerUri);
//    }
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity){
//        serverHttpSecurity
//                .csrf(csrf -> csrf.disable())
//                .authorizeExchange(exchange -> exchange.pathMatchers("/eureka/**")
//                        .permitAll()
//                        .anyExchange()
//                        .authenticated())
//                .oauth2ResourceServer(configurer -> configurer.jwt(jwt -> jwt.jwtDecoder(jwtDecoder())));
//
//        return serverHttpSecurity.build();
//    }
//}
