(ns website.projects
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [clojure.string :as string]
              [website.common :as common]
              cljsjs.moment)
    (:import goog.History))

(defonce projects
  (atom
   (vec
      (repeat 5
        {:title "There was a time"
         :tags ["phenomentators" "art" "car" "fest" "hippy-life"]
         :text "Bacon ipsum dolor amet voluptate strip steak lorem chicken laboris aliquip cow minim. Porchetta nostrud pork, sed tri-tip meatball ut biltong beef ribs occaecat. Voluptate mollit ad cillum, cupidatat prosciutto tri-tip anim in t-bone. Lorem in filet mignon, minim veniam cupidatat shankle cupim meatloaf pork belly voluptate laborum jerky cillum ribeye. Fatback shankle esse shoulder bacon aute adipisicing flank pastrami et velit ut consequat. Dolore aliqua landjaeger, sirloin ham adipisicing id. Sint excepteur picanha laborum exercitation alcatra, shankle voluptate.\nAd cupim drumstick, dolor velit picanha consectetur nisi kevin. Laboris pancetta jerky hamburger turkey jowl sirloin aliquip deserunt rump short ribs. Shank fugiat est capicola meatball. Laborum ex mollit spare ribs, laboris minim brisket kielbasa jowl chuck beef ribs tenderloin. Ut leberkas anim, alcatra venison excepteur strip steak frankfurter spare ribs tri-tip corned beef. Alcatra drumstick eu biltong nisi lorem.\nAnim bacon commodo ex porchetta proident adipisicing rump. Irure cupim landjaeger lorem consectetur, chicken shankle. Salami beef ribs ham, ground round deserunt ullamco laborum. In velit andouille boudin eiusmod. Pancetta aute drumstick filet mignon reprehenderit ullamco.\nSed tempor pariatur culpa. Minim meatball jowl, irure anim pastrami kielbasa shank ut. Enim laborum hamburger qui ut mollit laboris nulla dolore pork loin chuck reprehenderit porchetta fugiat. Shank cow enim, swine ex meatball ullamco picanha. Alcatra jowl short loin aliquip, pastrami cupim proident irure brisket qui beef ribs ribeye in laborum labore.\nVeniam officia meatball ad shoulder. Jerky fugiat venison dolore filet mignon voluptate shank hamburger et. Swine in sed culpa labore picanha. Reprehenderit id prosciutto capicola, short loin voluptate sed strip steak laborum sunt officia eu tail ullamco. Quis ground round ullamco, tongue kielbasa flank corned beef anim cow cupidatat ham hock leberkas. Biltong et turkey proident filet mignon ad non landjaeger esse boudin mollit."
         :publish-date (.format (js/moment) "MMMM Do YYYY")}))))

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
