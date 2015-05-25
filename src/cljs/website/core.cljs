(ns website.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [cljsjs.react :as react]
              [dommy.core :as dommy :refer-macros [sel1]]
              [website.common :as common]
              [website.blog :as blog :refer [blog-page]]
              [website.goals :as goals :refer [goals-page]]
              [website.search :as search :refer [search-page]]
              [website.resume :as resume :refer [resume-page]]
              [website.projects :as projects :refer [projects-page]]
              [clojure.string :as string])
    (:import goog.History))

(enable-console-print!)

(defn page-width! [width]
  "Set body width, used because resume-page must be wider"
  (if (number? width)
    (dommy/set-style! (sel1 :body) :width (str width "px"))
    (recur 600)))

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (session/put! :current-page #'projects-page))

(secretary/defroute projects-path "/projects" []
  (session/put! :current-page #'projects-page))

(secretary/defroute goal-path "/goals" []
  (session/put! :current-page #'goals-page))

(secretary/defroute blog-path "/blog" {:as params}
  (session/put! :current-page #'blog-page))

(secretary/defroute "/blog/:year/:month" {:as params}
  (session/put! :current-page #'blog-page))
;; #'blog-page = (var blog-page)
(secretary/defroute "/resume" []
  (session/put! :current-page #'resume-page))

(secretary/defroute "/search/:type/:value" {:as params}
  (session/put! :current-page #'search-page))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       ;; Inelegant hack to allow resume to be wider
       (if (= (.-token event) "/resume")
         (page-width! 750)
         (page-width! 600))
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (hook-browser-navigation!)
  (mount-root))
