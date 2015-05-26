(ns website.blog
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [clojure.string :as string]
              [website.common :as common]
              [markdown.core :as md]
              [website.fixtures :as fixtures]
              cljsjs.moment)
    (:import goog.History))

(defn posts-by-tag [tag]
  (filterv #(some #{tag} (:tags %)) fixtures/posts))

(def blogs
  (atom (posts-by-tag "blog")))

(defn format-post-body [text]
  [:div (for [line (string/split-lines text)]
          ^{:key (.random js/Math)} [:p line])])

(defn tag-component [tags]
  [:div.post-tags.flex-container.flex-space-between
    (map
      (fn [tag]
        ^{:key (.random js/Math)}
        [:a {:href (str "#/search/tag/" tag)} tag])
      tags)])

(defn post [{:keys [title tags rendered publish-date]} project]
  [:div.post
    [:h2.post-title title]
    [tag-component tags]
    ^{:key (.random js/Math)} [:p.post-date publish-date]
    [format-post-body rendered]])

(defn blog-page []
  [:div
    [common/heading "Blag"]
    (map post @blogs)])
