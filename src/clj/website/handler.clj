(ns website.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-js include-css]]
            [prone.middleware :refer [wrap-exceptions]]
            [environ.core :refer [env]]))

(def home-page
  (html
   [:html
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]
     (include-css (if (env :dev) "css/normalize.css" "css/normalize.min.css"))
     (include-css "http://fonts.googleapis.com/css?family=Open+Sans:700italic,700,300,400")
     (include-css (if (env :dev) "css/site.css" "css/site.min.css"))]
    [:body
     [:div#app]
     (include-js "js/app.js")]]))

(defroutes routes
  (GET "/" [] home-page)
  (resources "/")
  (not-found "Not Found"))

(def app
  (let [handler (wrap-defaults routes site-defaults)]
    (if (env :dev) (wrap-exceptions handler) handler)))
