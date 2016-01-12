(ns twitter4clojure.core
  (:import [twitter4j TwitterFactory Query])
    (:gen-class))

(def twitter (. (TwitterFactory.) getInstance))

;Timeline Resources
(defn get-mentions-timeline []
  (.getMentionsTimeline twitter))

(defn get-user-timeline
  ([] (.getUserTimeline twitter))
  ([user](.getUserTimeline twitter user)))

(defn get-home-timeline []
  (.getHomeTimeline twitter))

(defn get-retweets-of-me []
  (.getRetweetsOfMe twitter))

;Search Resources
(defn search [s]
  (let [query (Query. s)]
    (.search twitter query)))

;Spam Reporting Resource
(defn report-spam [screen-name]
  (.reportSpam twitter screen-name))

;Help Resources
(defn get-api-configuration []
  (.getAPIConfiguration twitter))

(defn get-languages []
  (.getLanguages twitter))

(defn get-privacy-policy []
  (.getPrivacyPolicy twitter))

(defn get-terms-of-service []
  (.getTermsOfService twitter))

(defn get-rate-limit-status []
  (.getRateLimitStatus twitter))
