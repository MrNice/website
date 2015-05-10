(ns website.common
    (:require [reagent.core :as reagent :refer [atom]]
              [clojure.string :as string]
              cljsjs.moment)
    (:import goog.History))

(def sections ["Projects" "Goals" "Blog" "Resume"])

;; -------------------------
;; Views
(defn header []
  [:ul
   (for [section sections]
     [:li
      [:a {:href (str "#/" (string/lower-case section))}
          section]])])
