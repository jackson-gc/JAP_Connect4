package CST8221;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class LocaleManager {
	
    public static Locale defaultLocale = new Locale("en", "US");
    public static ResourceBundle messages = ResourceBundle.getBundle("message", defaultLocale);
    private static final List<LocaleChangeListener> listeners = new ArrayList<>();

    public static synchronized void setLocale(Locale locale) {
    	defaultLocale = locale;
        for (LocaleChangeListener listener : listeners) {
            listener.localeChanged(locale);
        }
    }

    public static Locale getCurrentLocale() {
        return defaultLocale;
    }

    public static void addLocaleChangeListener(LocaleChangeListener listener) {
        listeners.add(listener);
    }

    public interface LocaleChangeListener {
        void localeChanged(Locale newLocale);
    }
}
