package ve.com.xv_dev.todolist.networking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author Xavier Guti&#233;rrez
 *         18/01/17
 */

@Singleton
public class CookieStore implements CookieJar {

    private final Set<Cookie> cookieStore = new HashSet<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        /**
         *Saves cookies from HTTP response
         * If the response includes a trailer this method is called second time
         */
        //Save cookies to the store
        cookieStore.addAll(cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        /**
         * Load cookies from the jar for an HTTP request.
         * This method returns cookies that have not yet expired
         */
        List<Cookie> validCookies = new ArrayList<>();
        for (Cookie cookie : cookieStore) {
            LogCookie(cookie);
            if (cookie.expiresAt() < System.currentTimeMillis()) {
                // invalid cookies
            } else {
                validCookies.add(cookie);
            }
        }
        return validCookies;
    }

    //Print the values of cookies - Useful for testing
    private void LogCookie(Cookie cookie) {
        System.out.println("String: " + cookie.toString());
        System.out.println("Expires: " + cookie.expiresAt());
        System.out.println("Hash: " + cookie.hashCode());
        System.out.println("Path: " + cookie.path());
        System.out.println("Domain: " + cookie.domain());
        System.out.println("Name: " + cookie.name());
        System.out.println("Value: " + cookie.value());
    }
}
