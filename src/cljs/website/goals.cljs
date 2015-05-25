(ns website.goals
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [website.common :as common]
              [website.blog :as blog])
    (:import goog.History))

(defonce goals
  (atom
    (vec
      (repeat 20
        {:title "Write a technical bestseller"
         :text "My whole programming career, I've been searching for programming nirvana. I want to be able to write clean, maintainable codebases with gusto.\nOver the years, I've found many shortcuts that have made my work significantly faster and easier. I'd like to share those in a book."}))))

(defn goal-component [{:keys [title text]} goal]
  [:div.post
    [:h2.post-title title]
    [blog/format-post-body text]])

(defn goals-page []
  [:div
    [common/heading "Goals"]
    (map goal-component @goals)])