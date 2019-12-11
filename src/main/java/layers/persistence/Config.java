package layers.persistence;

import java.io.InputStream;
import java.util.Properties;

/**
 * Получаем настройки из файла app.properties
 */
public class Config {

    private static final Config INSTANCE = new Config();
    private final Properties values = new Properties();

    private Config() {
    }

    public static Config getInstance() {
        INSTANCE.init();
        return INSTANCE;
    }

    private void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(
                "app.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}