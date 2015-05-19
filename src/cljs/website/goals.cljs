(ns website.goals
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [website.common :as common])
    (:import goog.History))

(defn goals-page []
  [:div [common/header]
        [:h1 "Goals"]])