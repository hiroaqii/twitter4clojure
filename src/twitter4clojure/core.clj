(ns twitter4clojure.core
  (:import [twitter4j TwitterFactory Query GeoLocation GeoQuery])
    (:gen-class))

(def twitter-instance (. (TwitterFactory.) getInstance))

(defmacro twitter [f]
  `(-> (. (TwitterFactory.) getInstance)
      ~f
      (data/from-java)))

;Timeline Resources
(defn get-mentions-timeline []
  (twitter (.getMentionsTimeline)))

(defn get-user-timeline
  ([] (twitter (.getUserTimeline)))
  ([^String user] (twitter (.getUserTimeline user))))

(defn get-home-timeline []
  (twitter (.getHomeTimeline )))

(defn get-retweets-of-me []
  (twitter (.getRetweetsOfMe )))

;Search Resources
(defn search [s]
  (let [query (Query. s)]
    (twitter (.search query))))

;Favorites Resources
(defn get-favorites []
  (twitter (.getFavorites)))

(defn destroy-favorite [status-id]
  (twitter (.destroyFavorite status-id)))

(defn create-favorite [status-id]
  (twitter (.createFavorite status-id)))

;Saved Searches Resources
(defn get-saved-searches []
  (twitter (.getSavedSearches)))

(defn show-saved-search [saved-search-id]
  (twitter (.showSavedSearch saved-search-id)))

(defn create-saved-search [^String query]
  (twitter (.createSavedSearch query)))

(defn destroySavedSearch [saved-search-id]
  (twitter (.destroySavedSearch saved-search-id)))

;Places & Geo Resourcs
(defn get-geo-details [place-id]
  (twitter (.getGeoDetails place-id)))

(defn reverse-geo-code [latitude longitude]
  (let [query (GeoQuery. (GeoLocation. latitude longitude))]
    (twitter (.reverseGeoCode query))))

(defn search-places [latitude longitude]
  (let [query (GeoQuery. (GeoLocation. latitude longitude))]
    (twitter (.searchPlaces query))))

;Trends Resources
(defn get-place-trends [woeid]
  (twitter (.getPlaceTrends woeid)))

(defn get-available-trends []
  (twitter (.getAvailableTrends)))

(defn get-closest-trends [latitude longitude]
  (let [location (GeoLocation. latitude longitude)]
    (twitter (.getClosestTrends location))))

;Spam Reporting Resource
(defn report-spam [screen-name]
  (twitter (.reportSpam screen-name)))

;Help Resources
(defn get-api-configuration []
  (twitter (.getAPIConfiguration)))

(defn get-languages []
  (twitter (.getLanguages)))

(defn get-privacy-policy []
  (twitter (.getPrivacyPolicy )))

(defn get-terms-of-service []
  (twitter (.getTermsOfService)))

(defn get-rate-limit-status []
  (twitter (.getRateLimitStatus)))
