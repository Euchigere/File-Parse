import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigParserTest {
    ConfigParser config;

    @Test
    void parseConfigFileOnProductionMode() {
        config = new ConfigParser(new File("src/config.txt"));
        assertAll(
                () -> assertEquals("production", config.get("mode")),
                () -> assertEquals("fast", config.get("pipeline")),
                () -> assertEquals("sq04_db", config.get("dbname")),
                () -> assertEquals("127.0.0.1", config.get("host")),
                () -> assertEquals("8080", config.get("application.port")),
                () -> assertEquals("green", config.get("theme")),
                () -> assertEquals("/api/v1", config.get("application.context-url")),
                () -> assertEquals("fintek", config.get("application.name"))
        );
    }

    @Test
    void parseConfigFileOnStagingMode() {
        config = new ConfigParser(new File("src/config-staging.txt"));
        assertAll(
                () -> assertEquals("staging", config.get("mode")),
                () -> assertEquals("fast-staging", config.get("pipeline")),
                () -> assertEquals("sq04_db", config.get("dbname")),
                () -> assertEquals("127.0.0.1", config.get("host")),
                () -> assertEquals("8081", config.get("application.port")),
                () -> assertEquals("blue", config.get("theme")),
                () -> assertEquals("/api/v1", config.get("application.context-url")),
                () -> assertEquals("fintek-staging", config.get("application.name"))
        );
    }

    @Test
    void parseConfigFileOnDevelopmentMode() {
        config = new ConfigParser(new File("src/config-development.txt"));
        assertAll(
                () -> assertEquals("development", config.get("mode")),
                () -> assertEquals("fast-development", config.get("pipeline")),
                () -> assertEquals("sq04_db-development", config.get("dbname")),
                () -> assertEquals("127.0.0.1", config.get("host")),
                () -> assertEquals("8082", config.get("application.port")),
                () -> assertEquals("yellow", config.get("theme")),
                () -> assertEquals("/api/v1", config.get("application.context-url")),
                () -> assertEquals("fintek-development", config.get("application.name"))
        );
    }
}