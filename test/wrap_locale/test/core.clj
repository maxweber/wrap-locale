(ns wrap-locale.test.core
  (:use wrap-locale.core
        [lazytest.describe
         :only [describe it given do-it]]
        [lazytest.expect :only [expect]]
        ring.mock.request))

(def de-DE java.util.Locale/GERMANY)

(def de java.util.Locale/GERMAN)

(def java-locale (java.util.Locale. "de" "DE" "variant"))

(def locale-vector ["de" "DE" "variant"])

(describe locale-to-vec
  (do-it "should convert a Java locale to a vector"
    (expect (= locale-vector (locale-to-vec java-locale)))
    (expect (= ["de"] (locale-to-vec de)))
    (expect (= ["de" "DE"] (locale-to-vec de-DE))))
  (it "should be nil safe"
    (= nil (locale-to-vec nil))))

(describe wrap-locale
  (given [accept-language-header "de-DE,de;q=0.8,en-US;q=0.6,en;q=0.4"
          ring-request (-> (request :get "/")
                           (assoc-in [:headers "accept-language"] accept-language-header))]
    (it "should parse the Accept-Language header and add the result as :locale vector to the ring request"
      (= ["de" "DE"] (:locale ((wrap-locale identity) ring-request))))))
