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

;Search Resource
(defn search [s]
  (let [query (Query. s)]
    (.search twitter query)))
