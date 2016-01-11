(ns twitter4clojure.core
  (:import [twitter4j TwitterFactory])
    (:gen-class))

(def twitter (. (TwitterFactory.) getInstance))

;Timeline Resources
(defn get_mentions_timeline []
  (.getMentionsTimeline twitter))

(defn get_user_timeline
  ([] (.getUserTimeline twitter))
  ([user](.getUserTimeline twitter user)))

(defn get_home_timeline []
  (.getHomeTimeline twitter))

(defn get_retweets_of_me []
  (.getRetweetsOfMe twitter))
