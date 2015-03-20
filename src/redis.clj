(ns documeds.redis
  (:use [aleph.redis :only (redis-client)]))

; Redis Connection -----------------------------------------------
(def r (delay (redis-client {:host "localhost", :port "6379"})))

; Autocomplete Keys -------------------------------------------------
(def key-autocomplete "clomate-index:")
(def key-database     "clomate-data:")
(def key-cachebase    "clomate-cache:")
