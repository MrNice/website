(ns website.projects
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [clojure.string :as string]
              [website.common :as common]
              [website.blog :as blog]
              [website.fixtures :refer [posts]]
              [datascript :as d]
              [website.utils :refer [bind unbind]]
              cljsjs.moment)
    (:import goog.History))

(def conn (d/create-conn {
  :post/tags {}
  :post/title {}
  :post/markdown {}
  :post/rendered {}
  :post/publish-date {}
  :post/author {:db/valueType :db.type/ref}
  :goal/author {:db.valueType :db.type/ref}
  :goal/title {}
  :goal/content {}
  :person/name {}
}))

(d/transact! conn posts)

(def q-projects-all
  '[:find   ?e
    :where [?e :post/tags ?tags]
           [(some #(= "project" %) ?tags)]])

;; (def projects
;;   (bind conn q-projects-all))

(comment
(defn project-posts []
  (map blog/post @projects))
  )

(defn projects-page []
  [:div
    (common/heading "Projects")])
