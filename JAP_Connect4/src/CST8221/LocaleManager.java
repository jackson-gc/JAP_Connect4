package CST8221;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * LocaleManager class manages the application's locale settings.
 * It provides methods to set and retrieve the current locale, and allows listeners
 * to be notified when the locale changes.
 */
public class LocaleManager {

    /** The default locale for the application */
    @SuppressWarnings("deprecation")
    public static Locale defaultLocale = new Locale("en", "US");

    /** ResourceBundle for accessing localized messages */
    public static ResourceBundle messages = ResourceBundle.getBundle("message", defaultLocale);

    /** List to hold LocaleChangeListener instances */
    private static final List<LocaleChangeListener> listeners = new ArrayList<>();

    /**
     * Sets the current locale to the specified locale and notifies listeners.
     * @param locale The new locale to set
     */
    public static synchronized void setLocale(Locale locale) {
        defaultLocale = locale;
        for (LocaleChangeListener listener : listeners) {
            listener.localeChanged(locale);
        }
    }

    /**
     * Gets the current locale of the application.
     * @return The current Locale instance
     */
    public static Locale getCurrentLocale() {
        return defaultLocale;
    }

    /**
     * Adds a LocaleChangeListener to the list of listeners.
     * @param listener The listener to add
     */
    public static void addLocaleChangeListener(LocaleChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Interface for receiving notifications about locale changes.
     * Implementations of this interface can be registered as listeners.
     */
    public interface LocaleChangeListener {
        /**
         * Called when the locale changes.
         * @param newLocale The new locale
         */
        void localeChanged(Locale newLocale);
    }
}
