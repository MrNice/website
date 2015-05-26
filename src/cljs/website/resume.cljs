(ns website.resume
    (:require [reagent.core :as reagent]
              [reagent.session :as session]
              [website.common :as common])
    (:import goog.History))

(def email "nicholas@niceguys.co")

(def proficiencies
  {:coding {
     :proficient ["JavaScript" "Node" "MongoDB" "Express / Restify" "Backbone" "jQuery" "HTML5 / CSS3" "Famo.us" "Python"]
     :familiar ["C" "MySQL" "Embedded Linux (Raspberry Pi)" "Arduino" "Tessel" "Django" "Neo4j" "D3" "three.js"]}
   :tools {
     :awesome ["Autodesk Inventor" "AutoCAD" "3D Printing (extrusion, sintering)" "CNC Router" "Soldering Iron" "Sketch"]
     :capable ["Hand & Power Tools" "3-axis CNC Mill" "Metalworking Mills and Lathes" "EagleCAD" "Illustrator"]}})

(def projects-positions
 [{:position "Node.js Engineer"
   :where-or-what "Visual Supply Company (VSCO)"
   :date-range "2014-2015"
   :main-technologies ["Node" "MongoDB" "MySQL" "Express"]
   :bullet-points ["Supported and maintained the in-app store application, managing differences between the App and Play stores"
                   "Modularized legacy codebases, creating well documented reusable modules and middleware"
                   "Implemented tooling to increase production visibility and make production debugging possible"
                   "Gave presentations and lectures on modern node.js application design so we could build better and faster"]}
   {:position "Back-End Software Engineer"
    :where-or-what "Monsoon Co."
    :date-range "2014"
    :main-technologies ["Node" "MongoDB" "Express" "Keystone"]
    :bullet-points ["Worked in an agile team of 4 engineers to build a set of back-end services for an iOS / Android app that supports the sales team of a major, international headphones brand"
                    "Designed and implemented an API for custom user survey application"
                    "Implemented custom user tracking and app analytics"]}
   {:position "Game Developer"
    :where-or-what "Leap Pong"
    :date-range "2014"
    :main-technologies ["three.js" "Leap Motion"]
    :bullet-points ["Built the classic game ‘Pong’ from scratch, using three.js to interface with webGL"
                    "Leveraged the Leap Motion Controller for paddle control"
                    "Mentioned on the Leap Motion developer blog"]}
   {:position "Content Manager"
    :where-or-what "Nvidia GPU Tech Conf"
    :date-range "2013"
    :main-technologies ["Django" "xlrd" "Python" "grep" "Vim scripts"]
    :bullet-points ["Worked with a small, varied team to ensure that all 350+ talks from the conference were available online as screencasts within 24 hours of recording"
                    "Wrote many small scripts to automate previously labor-intensive processes"
                    "Modified Django to produce static HTML pages from data stored in an excel spreadsheet"]}
   {:position "Lead Designer"
    :where-or-what "trip.wear"
    :date-range "2012"
    :main-technologies ["Arduino" "Total Control Lighting"]
    :bullet-points ["Designed articles of clothing with embedded RGBLED accents, powered by the Arduino platform"
                    "Designed and built entire power systems, down to acid-etching PCB boards"
                    "Wrote C programs to efficiently manipulate hundreds of LEDs within 2kB of RAM"]}])

(def educations
  [{:where "Hack Reactor, Advanced Software Engineering Immersive"
   :when "2014"
   :bullet-points ["Learned Full-Stack JavaScript Engineering"
                   "Focused on product vision and team dynamics"]}
  {:where "Laney College, Mathematics"
   :when "2013"}
  {:where "Oakland Technical High School, Engineering Academy"
   :when "2012"
   :bullet-points ["Focused on Mechanical Engineering and Design, with Some Architecture"]}])

(defn sub-heading [string]
  [:div
    [:h3.resume-title string] [:hr]])

(defn contact-info []
  [:ul.flex-container.flex-space-around.contact-info
    [:li "San Francisco, CA"]
    [:li [:a {:href "https://github.com/mrnice"} "github.com/MrNice"]]
    [:li [:a {:href "/contact"} "Contact"]]])

(defn commafy [list]
  (apply str (interpose ", " list)))

(defn skills-list [proficiencies]
  [:div
    [:h4 "Languages, Frameworks, Libraries:"]
    [:p (str "Proficient: " (commafy (:proficient (:coding proficiencies))))]
    [:p (str "Familiar: " (commafy (:familiar (:coding proficiencies))))]
    [:h4 "Tools and Software"]
    [:p (str "Awesome: " (commafy (:awesome (:tools proficiencies))))]
    [:p (str "Capable: " (commafy (:capable (:tools proficiencies))))]])

(defn project-component [{:keys [position where-or-what date-range main-technologies bullet-points]} project]
  [:div
    [:div.bold.flex-container.flex-space-between
      [:span (str position " - " where-or-what)]
      [:span.technologies.italic
        (str "(" (apply str (interpose ", " main-technologies)) ")")]
      [:span.resume-tidbits.italic date-range]]
    [:ul
     (map #(identity [:li %]) bullet-points)]])

(defn education-component [{:keys [where when bullet-points]} experience]
  [:div
    [:div.bold.flex-container.flex-space-between
      [:span where]
      [:span.resume-tidbits.italic when]]
    [:ul
     (map #(identity [:li %]) bullet-points)]])

(defn resume-page []
  [:div
    (common/header)
    [:h2.resume-title "Nicholas van de Walle"]
    [:div.resume
      (contact-info)
      (sub-heading "Programming & Maker Skills")
      (skills-list proficiencies)
      (sub-heading "Projects and Positions")
      (map project-component projects-positions)
      (sub-heading "Education")
      (map education-component educations)]])