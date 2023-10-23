package com.app.restApiAndroid.infra.security;

import com.app.restApiAndroid.models.Routes;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    private Routes routes;
    @Autowired
    SecurityFilter securityFilter;

    private List<String[]> routesPermits(Routes route){

        String[] AUTH_WHITELIST = {
                routes.getPrincipal() +"/v3/api-docs/**",
                routes.getPrincipal() +"/swagger-ui/**"
        };

        String[] ALLPERMITPOSTSROUTES = {
                routes.getPrincipal() + routes.getAutenticacaoLogin()
        };
        String[] ALLPERMITGETSROUTES = {};
        String[] ALLPERMITPUTSROUTES = {};
        String[] ALLPERMITDELETESROUTES = {};


        String[] ROLESPOSTSROUTES = {
                routes.getPrincipal() + routes.getCadastroUsuario(),
                routes.getPrincipal() + routes.getCadastroProduto(),
                route.getPrincipal() + routes.getCadastroEmpresa(),
                routes.getPrincipal() + routes.getCadastroPreco()
        };
        String[] ROLESGETSROUTES = {
                routes.getPrincipal() + routes.getBuscaListaUsuarios(),
                route.getPrincipal() + routes.getBuscaListaUsuarioToNome(),
                routes.getPrincipal() + routes.getBuscaUsuarioToNome(),
                routes.getPrincipal() + routes.getBuscaUsuarioToId(),
                routes.getPrincipal() + routes.getBuscaUsuarioToLogin(),
                routes.getPrincipal() + routes.getBuscaUsuarioToEmail(),
                routes.getPrincipal() + routes.getBuscaUsuarioToLoginEmail(),
                routes.getPrincipal() + routes.getBuscaListaProdutos(),
                routes.getPrincipal() + routes.getBuscaListaProdutosToTipo(),
                routes.getPrincipal() + routes.getBuscaProdutoToNome(),
                routes.getPrincipal() + routes.getBuscaListaProdutoToNome(),
                routes.getPrincipal() + routes.getBuscaProdutoToId(),
                routes.getPrincipal() + routes.getBuscaProdutoToGtin(),
                routes.getPrincipal() + routes.getBuscaProdutoApiExterna(),
                routes.getPrincipal() + routes.getBuscaEmpresaToId(),
                routes.getPrincipal() + routes.getBuscaEmpresaToCnpj(),
                routes.getPrincipal() + routes.getBuscaEmpresaToNome(),
                routes.getPrincipal() + routes.getBuscaListaEmpresaToNome(),
                routes.getPrincipal() + routes.getBuscaEmpresaToFantasia(),
                routes.getPrincipal() + routes.getBuscaListaEmpresaToFantasia(),
                routes.getPrincipal() + routes.getBuscaListaEmpresas(),
                routes.getPrincipal() + routes.getBuscaListaEmpresaToUf(),
                routes.getPrincipal() + routes.getBuscaListaEmpresaToMunicipio(),
                routes.getPrincipal() + routes.getBuscaListaEmpresaToUfMunicipio(),
                routes.getPrincipal() + routes.getBuscaEmpresaApiExterna(),
                routes.getPrincipal() + routes.getBuscaListaPrecos(),
                routes.getPrincipal() + routes.getBuscaPrecoToId(),
                routes.getPrincipal() + routes.getBuscaListaPrecoToIdPro(),
                routes.getPrincipal() + routes.getBuscaListaPrecoToIdEmp(),
                routes.getPrincipal() + routes.getBuscaPrecoToIdPrePro(),
                routes.getPrincipal() + routes.getBuscaPrecoToIdPreEmp(),
                routes.getPrincipal() + routes.getBuscaListaPrecoToIdProEmp(),
                routes.getPrincipal() + routes.getBuscaPrecoToIdPreProEmp(),
        };
        String[] ROLESPUTSROUTES = {
                routes.getPrincipal() + routes.getAtualizaUsuarioToId(),
                routes.getPrincipal() + routes.getAtualizaUsuarioToLogin(),
                routes.getPrincipal() + routes.getAtualizaProdutoToId(),
                routes.getPrincipal() + routes.getAtualizaProdutoToGtin(),
                routes.getPrincipal() + routes.getAtualizaEmpresaToId(),
                routes.getPrincipal() + routes.getAtualizaEmpresaToCnpj(),
                routes.getPrincipal() + routes.getAtualizaEmpresaToNome(),
                routes.getPrincipal() + routes.getAtualizaEmpresaToFantasia(),
                routes.getPrincipal() + routes.getAtualizaPrecoToId(),
                routes.getPrincipal() + routes.getAtualizaPrecoToIdProEmp(),
                routes.getPrincipal() + routes.getAtualizaPrecoToIdPreProEmp()
        };
        String[] ROLESDELETESROUTES = {
                routes.getPrincipal() + routes.getDeletaUsuarioToId(),
                routes.getPrincipal() + routes.getDeletaUsuarioToLogin(),
                routes.getPrincipal() + routes.getDeletaProdutoToId(),
                routes.getPrincipal() + routes.getDeletaProdutoToGtin(),
                routes.getPrincipal() + routes.getDeletaEmpresaToId(),
                routes.getPrincipal() + routes.getDeletaEmpresaToCnpj(),
                routes.getPrincipal() + routes.getDeletaEmpresaToNome(),
                routes.getPrincipal() + routes.getDeletaEmpresaToFantasia(),
                routes.getPrincipal() + routes.getDeletaPrecoToId(),
                routes.getPrincipal() + routes.getDeletaPrecoToIdProEmp(),
                routes.getPrincipal() + routes.getDeletaPrecoToIdPreProEmp(),
                routes.getPrincipal() + routes.getDeletaPrecoToIdPrePro(),
                routes.getPrincipal() + routes.getDeletaPrecoToIdPreEmp(),
                routes.getPrincipal() + routes.getDeletaPrecoToIdPro(),
                routes.getPrincipal() + routes.getDeletaPrecoToIdEmp()
        };

        List<String[]> listPairPermits = new ArrayList<>();

        listPairPermits.add(AUTH_WHITELIST);

        listPairPermits.add(ALLPERMITPOSTSROUTES);
        listPairPermits.add(ALLPERMITGETSROUTES);
        listPairPermits.add(ALLPERMITPUTSROUTES);
        listPairPermits.add(ALLPERMITDELETESROUTES);

        listPairPermits.add(ROLESPOSTSROUTES);
        listPairPermits.add(ROLESGETSROUTES);
        listPairPermits.add(ROLESPUTSROUTES);
        listPairPermits.add(ROLESDELETESROUTES);


        return listPairPermits;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        getAuthorizationManagerRequestMatcherRegistry(authorize)
                                .anyRequest()
                                .authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception->
                        exception.authenticationEntryPoint(
                                new MyAuthenticationEntryPoint()))
                .build();
    }

    private AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry getAuthorizationManagerRequestMatcherRegistry(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {

        List<String[]> listRoutes = routesPermits(routes);

        return authorize
                .requestMatchers(listRoutes.get(0)).permitAll()
                .requestMatchers(HttpMethod.POST, listRoutes.get(1)).permitAll()
                .requestMatchers(HttpMethod.GET, listRoutes.get(2)).permitAll()
                .requestMatchers(HttpMethod.PUT, listRoutes.get(3)).permitAll()
                .requestMatchers(HttpMethod.DELETE, listRoutes.get(4)).permitAll()//.hasAnyRole("ADMINS", "PAGES")//.permitAll()
                .requestMatchers(HttpMethod.POST, listRoutes.get(5)).permitAll()//.hasAnyRole("ADMINS", "PAGES")
                .requestMatchers(HttpMethod.GET, listRoutes.get(6)).permitAll()//.hasAnyRole("ADMINS", "PAGES")
                .requestMatchers(HttpMethod.PUT, listRoutes.get(7)).permitAll()//.hasAnyRole("ADMINS", "PAGES")
                .requestMatchers(HttpMethod.DELETE, listRoutes.get(8)).permitAll();//.hasAnyRole("ADMINS", "PAGES");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .title("Appi Rest Super Price")
                        .description("Api rest da aplicação Super Price")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Bruno Pereira da Silva")
                                .email("bps20111@hotmail.com")
                                .url("")
                        )
                        .license(new License()
                                .name("License of API REST SUPER PRICE")
                                .url("")
                        )
                );
    }
    @Value("${server.ssl.key-store}")
    private Resource keyStore;
    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    @Bean
    RestTemplate restTemplate() throws Exception {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(keyStore.getURL(),keyStorePassword.toCharArray(), new TrustAllStrategy())
                .build();
        SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslcontext)
                .build();
        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();
        HttpClient httpClient = HttpClients.custom().setConnectionManager(cm)
                .evictExpiredConnections().build();
        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
