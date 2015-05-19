(ns website.search
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [website.common :as common])
    (:import goog.History))

(defn search-page []
  [:div [common/header]
        [:h1 "Search"]])

(comment
(defn format-post-body [text]
  [:div (for [line (string/split-lines text)] [:p line])])

(defn tag-component [tags]
  [:div (map (fn [tag] [:a {:href (str "#/search/tag/" tag)} tag]) tags)])

(defn post [{:keys [title tags text publish-date]} project]
  [:div [:h2 title]
        [tag-component tags]
        [:p publish-date]
        [format-post-body text]])

(defn projects-page []
  [:div [common/header]
        [:h1 "Projects"]
        (map post @projects)])
)
