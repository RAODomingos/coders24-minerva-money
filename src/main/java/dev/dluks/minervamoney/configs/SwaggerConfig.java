package dev.dluks.minervamoney.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    static ExternalDocumentation gitHubLink;
    static Info infos;

    public static void setGitHubLink() {
        gitHubLink = new ExternalDocumentation()
                .description("GitHub Link")
                .url("https://github.com/dluks82/coders24-minerva-money");
    }

    public static void setInfos() {
        infos = new Info()
                .title("Minerva Money")
                .version("1.0")
                .description("""
                    Este projeto foi desenvolvido para aplicar técnicas avançadas com frameworks modernos, com foco na construção de APIs REST seguras e escaláveis usando o ecossistema Spring. Ele abrange conteúdos fundamentais, como o Spring Framework, Spring Boot e Spring Data JPA, para gerenciar dados de forma eficiente. Utilizando o Spring Web para expor endpoints e o Spring Security para controle de acesso, o sistema também integra consultas customizadas com Query DSL, manipulação de expressões regulares (RegEx) e o uso de REST Clients para comunicações externas, proporcionando uma arquitetura flexível e adaptável para atender a diversas necessidades.
                    
                    Desenvolvida por:
                      - Diogo Oliveira
                      - Isaque Menezes
                      - Rômulo Domingos
                      - Samuel Quaresma
                    """);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        setGitHubLink();
        setInfos();
        return new OpenAPI()
                .info(infos)
                .externalDocs(gitHubLink);
    }
}