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
    :date-range "2014"
    :main-technologies ["Arduino" "Total Control Lighting"]
    :bullet-points ["Designed articles of clothing with embedded RGBLED accents, powered by the Arduino platform"
                    "Designed and built entire power systems, down to acid-etching PCB boards"
                    "Wrote C programs to efficiently manipulate hundreds of LEDs within 2kB of RAM"]}])


(defn resume-page []
  [:div [common/header]
    [:h2 "Nicholas van de Walle"]
    [:ul
      [:li "San Francisco, CA"]
      [:li "(510) 701-0876"]
      [:li [:a {:href (str "mailto:" email)} email]]
      [:li [:a {:href "https://github.com/mrnice"} "github.com/MrNice"]]]
    [:h3 "Programming & Maker Skills"]
    [:hr]
    [:h4 "Languages, Frameworks, Libraries:"]
    [:p (str "Proficient: " (apply str (interpose ", " (:proficient (:coding proficiencies)))))]
    [:p (str "Familiar: " (apply str (interpose ", " (:familiar (:coding proficiencies)))))]
    [:h4 "Tools and Software"]
    [:p (str "Awesome: " (apply str (interpose ", " (:awesome (:tools proficiencies)))))]
    [:p (str "Capable: " (apply str (interpose ", " (:capable (:tools proficiencies)))))]
    [:h3 "Projects and Positions"]
    [:hr]

    ])