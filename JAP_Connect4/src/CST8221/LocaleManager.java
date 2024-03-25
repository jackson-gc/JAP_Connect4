package CST8221;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LocaleManager {
    private static Locale currentLocale = Locale.getDefault();
    private static final List<LocaleChangeListener> listeners = new ArrayList<>();

    public static synchronized void setLocale(Locale locale) {
        currentLocale = locale;
        for (LocaleChangeListener listener : listeners) {
            listener.localeChanged(locale);
        }
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void addLocaleChangeListener(LocaleChangeListener listener) {
        listeners.add(listener);
    }

    public interface LocaleChangeListener {
        void localeChanged(Locale newLocale);
    }
}
