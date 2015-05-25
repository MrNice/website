(ns website.search
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [website.common :as common])
    (:import goog.History))

(defn search-page []
  [:div [common/header]
        [:h1 "Search"]])
