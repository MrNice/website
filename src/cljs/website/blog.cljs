(ns website.blog
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [clojure.string :as string]
              [website.common :as common]
              [markdown.core :as md]
              cljsjs.moment)
    (:import goog.History))

(defonce blogs
  (atom
    (vec
      (repeat 5
        {:title "Is this real life"
         :tags ["is" "this" "just" "fantasy"]
         :rendered "Caught in a landslide, no escape from reality. Open your eyes and seeeeeee. Bacon ipsum dolor amet voluptate strip steak lorem chicken laboris aliquip cow minim. Porchetta nostrud pork, sed tri-tip meatball ut biltong beef ribs occaecat. Voluptate mollit ad cillum, cupidatat prosciutto tri-tip anim in t-bone.\n\n Lorem in filet mignon, minim veniam cupidatat shankle cupim meatloaf pork belly voluptate laborum jerky cillum ribeye. Fatback shankle esse shoulder bacon aute adipisicing flank pastrami et velit ut consequat. Dolore aliqua landjaeger, sirloin ham adipisicing id. Sint excepteur picanha laborum exercitation alcatra, shankle voluptate.\n\n Ad cupim drumstick, dolor velit picanha consectetur nisi kevin. Laboris pancetta jerky hamburger turkey jowl sirloin aliquip deserunt rump short ribs. Shank fugiat est capicola meatball. Laborum ex mollit spare ribs, laboris minim brisket kielbasa jowl chuck beef ribs tenderloin. Ut leberkas anim, alcatra venison excepteur strip steak frankfurter spare ribs"
         :markdown "<p>Caught in a landslide, no escape from reality. Open your eyes and seeeeeee. Bacon ipsum dolor amet voluptate strip steak lorem chicken laboris aliquip cow minim. Porchetta nostrud pork, sed tri-tip meatball ut biltong beef ribs occaecat. Voluptate mollit ad cillum, cupidatat prosciutto tri-tip anim in t-bone. Lorem in filet mignon, minim veniam cupidatat shankle cupim meatloaf pork belly voluptate laborum jerky cillum ribeye. Fatback shankle esse shoulder bacon aute adipisicing flank pastrami et velit ut consequat. Dolore aliqua landjaeger, sirloin ham adipisicing id. Sint excepteur picanha laborum exercitation alcatra, shankle voluptate.</p><p>Ad cupim drumstick, dolor velit picanha consectetur nisi kevin. Laboris pancetta jerky hamburger turkey jowl sirloin aliquip deserunt rump short ribs. Shank fugiat est capicola meatball. Laborum ex mollit spare ribs, laboris minim brisket kielbasa jowl chuck beef ribs tenderloin. Ut leberkas anim, alcatra venison excepteur strip steak frankfurter spare ribs tri-tip corned beef. Alcatra drumstick eu biltong nisi lorem.</p><p>Anim bacon commodo ex porchetta proident adipisicing rump. Irure cupim landjaeger lorem consectetur, chicken shankle. Salami beef ribs ham, ground round deserunt ullamco laborum. In velit andouille boudin eiusmod. Pancetta aute drumstick filet mignon reprehenderit ullamco.</p><p>Sed tempor pariatur culpa. Minim meatball jowl, irure anim pastrami kielbasa shank ut. Enim laborum hamburger qui ut mollit laboris nulla dolore pork loin chuck reprehenderit porchetta fugiat. Shank cow enim, swine ex meatball ullamco picanha. Alcatra jowl short loin aliquip, pastrami cupim proident irure brisket qui beef ribs ribeye in laborum labore.</p><p>Veniam officia meatball ad shoulder. Jerky fugiat venison dolore filet mignon voluptate shank hamburger et. Swine in sed culpa labore picanha. Reprehenderit id prosciutto capicola, short loin voluptate sed strip steak laborum sunt officia eu tail ullamco. Quis ground round ullamco, tongue kielbasa flank corned beef anim cow cupidatat ham hock leberkas. Biltong et turkey proident filet mignon ad non landjaeger esse boudin mollit.</p>"
         :publish-date (.format (js/moment) "MMMM Do YYYY")}))))

(defn format-post-body [text]
  [:div (for [line (string/split-lines text)] [:p line])])

(defn tag-component [tags]
  [:div.post-tags.flex-container.flex-space-between
    (map (fn [tag] [:a {:href (str "#/search/tag/" tag)} tag]) tags)])

(defn post [{:keys [title tags rendered publish-date]} project]
  [:div.post
    [:h2.post-title title]
    [tag-component tags]
    [:p.post-date publish-date]
    [format-post-body rendered]])


(defn blog-page []
  [:div
    [common/heading "Blag"]
    (map post @blogs)])
