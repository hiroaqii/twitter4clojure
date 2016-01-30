(ns twitter4clojure.core
  (:require [clojure.java.data :as data])
  (:import [twitter4j TwitterFactory Query GeoLocation GeoQuery OEmbedRequest Paging])
    (:gen-class))

(def twitter-instance (. (TwitterFactory.) getInstance))

(defmacro twitter [f]
  `(-> twitter-instance ~f (data/from-java)))

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

;Tweets Resources
(defn get-retweets [status-id]
  (twitter (.getRetweets status-id)))

(defn get-retweeter-ids
  ([status-id cursor]
   (twitter (.getRetweeterIds status-id cursor)))
  ([status-id cnt cursor]
   (twitter (.getRetweeterIds status-id cnt cursor))))

(defn show-status [status-id]
  (twitter (.showStatus status-id)))

(defn destroy-status [status-id]
  (twitter (.getdestroyStatus status-id)))

(defn update-status [status]
  (twitter (.updateStatus status)))

(defn retweetStatus [status-id]
  (twitter (.getretweetStatus status-id)))

(defn get-o-embed [status-id url]
  (twitter (.getOEmbed (OEmbedRequest. status-id url))))

(defn upload-media [^java.io.File media-file]
  (twitter (.uploadMedia media-file)))

;Search Resources
(defn search [s]
  (let [query (Query. s)]
    (twitter (.search query))))

;Direct Message Resources
(defn get-direct-messages []
  (twitter (.getDirectMessages)))

(defn get-sent-direct-messages []
  (twitter (.getSentDirectMessages)))

(defn show-direct-message [direct-message-id]
  (twitter (.showDirectMessage [direct-message-id])))

(defn destroy-direct-message [direct-message-id]
  (twitter (.destroyDirectMessage direct-message-id)))

(defn send-direct-message [user-id text]
  (twitter (.getsendDirectMessage user-id text)))

;Friends & Followers
(defn get-friends-ids
  [& {:keys [user-id screen-name cnt cursor] :or {cursor -1} :as all}]
  (cond
    (and user-id cnt)     (twitter (.getFriendsIDs user-id cursor cnt))
    (some? user-id)       (twitter (.getFriendsIDs user-id cursor))
    (and screen-name cnt) (twitter (.getFriendsIDs screen-name cursor cnt))
    (some? screen-name)   (twitter (.getFriendsIDs screen-name cursor))
    :else                 (twitter (.getFriendsIDs cursor))))

(defn get-followers-ids
  [& {:keys [user-id screen-name cnt cursor] :or {cursor -1} :as all}]
  (cond
    (and user-id cnt)     (twitter (.getFollowersIDs user-id cursor cnt))
    (some? user-id)       (twitter (.getFollowersIDs user-id cursor))
    (and screen-name cnt) (twitter (.getFollowersIDs screen-name cursor cnt))
    (some? screen-name)   (twitter (.getFollowersIDs screen-name cursor))
    :else                 (twitter (.getFollowersIDs cursor))))

(defn get-incoming-friendships
  ([] (get-incoming-friendships -1))
  ([cursor](twitter (.getIncomingFriendships cursor))))

(defn get-outgoing-friendships
  ([] (get-outgoing-friendships -1))
  ([cursor] (twitter (.getOutgoingFriendships cursor))))

(defn create-friendship
  [& {:keys [user-id screen-name follow] :or {follow false} :as all}]
  (let [id-or-name (if (nil? user-id) screen-name user-id)]
    (twitter (.createFriendship id-or-name follow))))

(defn destroy-friendship [id-or-name]
  (twitter (.destroyFriendship id-or-name)))

(defn update-friendship
  [& {:keys [user-id screen-name enable-device-notification retweets] :or {enable-device-notification false retweets false} :as all}]
  (let [id-or-name (if (nil? user-id) screen-name user-id)]
    (twitter (.updateFriendship id-or-name enable-device-notification retweets))))

(defn show-friendship
  [source target]
  (twitter (.showFriendship source target)))

(defn get-friends-list
  [& {:keys [user-id screen-name cursor cnt skip-status include-user-entities]
      :or   {cursor -1 cnt 20 skip-status false include-user-entities false}
      :as    all}]
  (twitter
   (.getFriendsList
    (if user-id user-id screen-name) cursor cnt skip-status include-user-entities)))

(defn get-followers-list
  [& {:keys [user-id screen-name cursor cnt skip-status include-user-entities]
      :or   {cursor -1 cnt 20 skip-status false include-user-entities false}
      :as    all}]
  (twitter
   (.getFollowersList
    (if user-id user-id screen-name) cursor cnt skip-status include-user-entities)))

;Users Resources
(defn get-account-settings []
  (twitter (.getAccountSettings)))

(defn verify-credentials []
  (twitter (.verifyCredentials)))

(defn get-blocks-list []
  (twitter (.getBlocksList)))

(defn get-blocks-ids []
  (twitter (.getBlocksIDs)))

(defn create-block [id-or-name]
  (twitter (.createBlock id-or-name)))

(defn destroy-block [id-or-name]
  (twitter (.destroyBlock id-or-name)))

(defn show-user [user-id]
  (twitter (.showUser user-id)))

(defn search-users [query page]
  (twitter (.searchUsers query page)))

(defn get-contributees [id-or-name]
  (twitter (.getContributees id-or-name)))

(defn get-contributors [id-or-name]
  (twitter (.getContributors id-or-name)))

(defn remove-profile-banner []
  (twitter (.removeProfileBanner)))

(defn update-profile-banner [image]
  (twitter (.updateProfileBanner image)))

(defn create-mute [id-or-name]
  (twitter (.createMute id-or-name)))

(defn destroy-mute [id-or-name]
  (twitter (.destroyMute id-or-name)))

(defn get-mutes-ids
  ([] (get-mutes-ids -1))
  ([cursor] (twitter (.getMutesIDs cursor))))

(defn get-mutes-list
  ([] (get-mutes-list -1))
  ([cursor] (twitter (.getMutesList cursor))))

;Suggested Users Resources
(defn get-user-suggestions [category-slug]
  (twitter (.getUserSuggestions category-slug)))

(defn get-suggested-user-categories []
  (twitter (.getSuggestedUserCategories)))

(defn get-member-suggestions [category-slug]
  (twitter (.getMemberSuggestions category-slug)))

;Favorites Resources
(defn get-favorites []
  (twitter (.getFavorites)))

(defn destroy-favorite [status-id]
  (twitter (.destroyFavorite status-id)))

(defn create-favorite [status-id]
  (twitter (.createFavorite status-id)))

;Lists Resources
(defn get-user-lists
  ([id-or-name] (get-user-lists id-or-name false))
  ([id-or-name reverse] (twitter (.getUserLists id-or-name reverse))))

(defn get-user-list-statuses
  ([id ^Paging paging]
   (twitter (.getUserListStatuses id paging)))
  ([id-or-name slug ^Paging paging]
   (twitter (.getUserListStatuses id-or-name slug paging))))

(defn destroy-user-list-member
  ([list-id id-or-name]
   (twitter (.destroyUserListMember list-id id-or-name)))
  ([id-or-name slug user-id]
   (twitter (.destroyUserListMember id-or-name slug user-id))))

(defn get-user-list-memberships
  [& {:keys [list-id screen-name cnt cursor filter-to-owned-lists]
      :or {cnt 20 cursor -1 filter-to-owned-lists false} :as all}]
  (let [id-or-name (if (nil? list-id) screen-name list-id)]
    (if (nil? id-or-name)
      (twitter (.getUserListMemberships cnt cursor))
      (twitter (.getUserListMemberships list-id cnt cursor filter-to-owned-lists )))))

(defn get-user-list-subscribers
  [& {:keys [list-id owner-id screen-name slug cnt cursor skip-status]
      :or {cnt 20 cursor -1 skip-status false} :as all}]
  (if (not= list-id nil)
    (twitter (.getUserListSubscribers list-id cnt cursor skip-status))
    (let [id-or-name (if (nil? owner-id) screen-name owner-id)]
      (twitter (.getUserListSubscribers id-or-name slug cnt cursor skip-status)))))

(defn create-user-list-subscription
  ([list-id]
   (twitter (.createUserListSubscription list-id)))
  ([id-or-name slug]
   (twitter (.createUserListSubscription id-or-name slug))))

(defn show-user-list-subscription
  ([list-id user-id]
   (twitter (.showUserListSubscription list-id user-id)))
  ([id-or-name slug user-id]
   (twitter (.showUserListSubscription id-or-name slug user-id))))

(defn destroy-userList-subscription
  ([list-id]
   (twitter (.destroyUserListSubscription list-id)))
  ([id-or-name slug]
   (twitter (.destroyUserListSubscription id-or-name slug))))

(defn show-user-list-membership
  ([list-id user-id]
   (twitter (.showUserListMembership list-id user-id)))
  ([id-or-name slug user-id]
   (twitter (.showUserListMembership id-or-name slug user-id))))

(defn get-user-list-members
  [& {:keys [list-id owner-id screen-name slug cnt cursor skip-status]
      :or {cnt 20 cursor -1 skip-status false} :as all}]
  (if (not= list-id nil)
    (twitter (.getUserListMembers list-id cnt cursor skip-status))
    (let [id-or-name (if (nil? owner-id) screen-name owner-id)]
      (twitter (.getUserListMembers id-or-name slug cnt cursor skip-status)))))

(defn create-user-list-member
  ([list-id user-id]
   (twitter (.createUserListMember list-id user-id)))
  ([id-or-name slug user-id]
   (twitter (.createUserListMember id-or-name slug user-id))))

(defn destroy-user-list
  ([list-id] (twitter (.destroyUserList list-id)))
  ([id-or-name slug] (twitter (.destroyUserList id-or-name slug))))

(defn update-user-list
  ([list-id new-list-name is-public-list new-desc]
   (twitter (.updateUserList list-id new-list-name is-public new-desc)))
  ([id-or-name slug new-list-name is-public-list new-desc]
   (twitter (.updateUserList id-or-name slug new-list-name is-public new-desc))))

(defn create-user-list
  [list-name is-public desc]
  (twitter (.createUserList list-name is-public desc)))

(defn show-user-list
  ([list-id] (twitter (.showUserList list-id)))
  ([owner-id slug] (twitter (.showUserList owner-id slug))))

(defn get-user-list-subscriptions
  ([id-or-name]
   (get-user-list-subscriptions id-or-name -1))
  ([id-or-name cursor]
   (get-user-list-subscriptions id-or-name 20 cursor))
  ([id-or-name cnt cursor]
   (twitter (.getUserListSubscriptions id-or-name cnt cursor))))

;Saved Searches Resources
(defn get-saved-searches []
  (twitter (.getSavedSearches)))

(defn show-saved-search [saved-search-id]
  (twitter (.showSavedSearch saved-search-id)))

(defn create-saved-search [^String query]
  (twitter (.createSavedSearch query)))

(defn destroy-saved-search [saved-search-id]
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
