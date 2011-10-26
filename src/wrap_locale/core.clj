(ns wrap-locale.core
  (import org.apache.tomcat.util.http.AcceptLanguage))

(defn locale-to-vec [locale]
  (if (nil? locale)
    nil
    (let [language (.getLanguage locale)
          country (.getCountry locale)
          variant (.getVariant locale)]
      (vec (take-while (comp not empty?) [language country variant])))))

(defn wrap-locale [handler]
  (fn [req]
    (handler
     (assoc req :locale
            (locale-to-vec (AcceptLanguage/getLocale (get-in req [:headers "accept-language"])))))))

