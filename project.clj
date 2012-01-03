(defproject wrap-locale "0.1.0-SNAPSHOT"
  :description "A workaround to parse the Accept-Language header of a HTTP request."
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [tomcat/tomcat-util "5.5.23"]]
  :dev-dependencies [[com.stuartsierra/lazytest "1.1.2"]
                     [swank-clojure "1.4.0-SNAPSHOT"]
                     [ring-mock "0.1.1"]])
