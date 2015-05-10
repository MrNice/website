(ns website.common
    (:require [reagent.core :as reagent :refer [atom]]
              [clojure.string :as string])
    (:import goog.History))

(defonce sections (atom ["Projects" "Goals" "Blog" "Resume"]))

;; -------------------------
;; Views
(defn header []
  [:ul
   (for [section @sections]
     ^{:key section} [:li [:a {:href (str "#/" (string/lower-case section))} section]])])

(defn tag-component [tags]
  (for [tag tags] [:span tag]))
