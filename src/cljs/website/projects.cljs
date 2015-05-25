(ns website.projects
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [clojure.string :as string]
              [website.common :as common]
              [website.blog :as blog]
              cljsjs.moment)
    (:import goog.History))

(defonce projects
  (atom
    (vec
      (repeat 10
        {:title "There was a time"
         :tags ["phenomentators" "art" "car" "fest" "hippy-life"]
         :markdown "#### Hey there"
         :rendered "Caught in a landslide, no escape from reality. Open your eyes and seeeeeee. Bacon ipsum dolor amet voluptate strip steak lorem chicken laboris aliquip cow minim. Porchetta nostrud pork, sed tri-tip meatball ut biltong beef ribs occaecat. Voluptate mollit ad cillum, cupidatat prosciutto tri-tip anim in t-bone.\n\n Lorem in filet mignon, minim veniam cupidatat shankle cupim meatloaf pork belly voluptate laborum jerky cillum ribeye. Fatback shankle esse shoulder bacon aute adipisicing flank pastrami et velit ut consequat. Dolore aliqua landjaeger, sirloin ham adipisicing id. Sint excepteur picanha laborum exercitation alcatra, shankle voluptate.\n\n Ad cupim drumstick, dolor velit picanha consectetur nisi kevin. Laboris pancetta jerky hamburger turkey jowl sirloin aliquip deserunt rump short ribs. Shank fugiat est capicola meatball. Laborum ex mollit spare ribs, laboris minim brisket kielbasa jowl chuck beef ribs tenderloin. Ut leberkas anim, alcatra venison excepteur strip steak frankfurter spare ribs"
         :publish-date (.format (js/moment) "MMMM Do YYYY")}))))

(defn project-posts []
  (map blog/post @projects))

(defn projects-page []
  [:div
    (common/heading "Projects")
    (project-posts)])
