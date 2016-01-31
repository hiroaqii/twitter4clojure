# twitter4clojure

A Clojure wrapper for Twitter4J 

Support API  [http://twitter4j.org/en/api-support.html](http://twitter4j.org/en/api-support.html) (Streaming Resources is not supported yet)

[![Clojars Project](https://clojars.org/twitter4clojure/latest-version.svg)](https://clojars.org/twitter4clojure)

## Usage

### Configuration

[http://twitter4j.org/en/configuration.html](http://twitter4j.org/en/configuration.html)

### Example

```clojure
(ns example.core
  (:require [twitter4clojure.core :as twitter]))
  
  ;search
  (twitter/search "clojure")
  
  ;search (tweet only)
  (->> "clojure"
       (twitter/search)
       (:tweets)
       (map :text))

  ;search (screenName and tweet)
  (->> "clojure"
       (twitter/search)
       (:tweets)
       (map (fn [x] [(:screenName (:user x)) (:text x)])))

  ;advanced search
  (def query (twitter/make-query "clojure" :lang "ja" :count 10))
  (->> query
       (twitter/search)
       (:tweets)
       (map (fn [x] [(:screenName (:user x)) (:text x)]))) 

  ;tweet   
  (twitter/update-status "Hello Clojurians!!!")
  ```
  
## License
  
  Distributed under the Eclipse Public License, the same as Clojure.
  
