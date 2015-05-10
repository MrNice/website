(ns website.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [cljsjs.react :as react]
              [website.common :as common]
              [website.projects :as projects :refer [projects-page]]
              [clojure.string :as string])
    (:import goog.History))

(defn blog-page [] [common/header])

(defn goals-page [] [common/header])

(defn resume-page []
  [:div [common/header]
   [:p "There is much to be learned"]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (session/put! :current-page #'projects-page))

(secretary/defroute "/projects" []
  (session/put! :current-page #'projects-page))

(secretary/defroute "/goals" []
  (session/put! :current-page #'goals-page))

(secretary/defroute "/blog" []
  (session/put! :current-page #'blog-page))

(secretary/defroute "/resume" []
  (session/put! :current-page #'resume-page))


;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (hook-browser-navigation!)
  (mount-root))
