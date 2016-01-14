(ns twitter4clojure.core
  (:import [twitter4j TwitterFactory Query GeoLocation GeoQuery])
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

;Places & Geo Resourcs
(defn get-geo-details [place-id]
  (.getGeoDetails twitter place-id))

(defn reverse-geo-code [latitude longitude]
  (let [query (GeoQuery. (GeoLocation. latitude longitude))]
    (.reverseGeoCode twitter query)))

(defn search-places [latitude longitude]
  (let [query (GeoQuery. (GeoLocation. latitude longitude))]
    (.searchPlaces twitter query)))

;Trends Resources
(defn get-place-trends [woeid]
  (.getPlaceTrends twitter woeid))

(defn get-available-trends []
  (.getAvailableTrends twitter))

(defn getClosestTrends [latitude longitude]
  (let [location (GeoLocation. latitude longitude)]
      (.getClosestTrends twitter location)))

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
