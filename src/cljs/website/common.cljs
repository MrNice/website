(ns website.common
    (:require [reagent.core :as reagent :refer [atom]]
              [clojure.string :as string])
    (:import goog.History))

(defonce sections (atom ["Projects" "Goals" "Blog" "Resume"]))

;; -------------------------
;; Views
(defn header []
  [:div
   [:ul.flex-container.flex-space-around.header
     (for [section @sections]
       ^{:key section} [:li.title-item  [:a {:href (str "#/" (string/lower-case section))} section]])]
   [:hr]])

(defn heading [name]
  [:div
    [header]
    [:h1.heading name]])

(defn tag-component [tags]
  (for [tag tags] [:span tag]))
