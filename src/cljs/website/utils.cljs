(ns website.utils
  (:require
    [reagent.core :refer [atom]]
    [datascript :as d]
    [cljs-uuid.core :as uuid]))

;; The following DataScript Query Wrappers come from
;; https://github.com/tonsky/datascript-chat/blob/gh-pages/src/datascript_chat/util.cljs
;; Which has NOLICENSE, so sue me
(defn -q [q & args]
    (let [key (str q)
          _ (.time js/console key)
          res (apply d/q q args)
          _ (.timeEnd js/console key)]
      res))

(defn q1
  "Return first element of first tuple of result"
  [q & args]
  (->> (apply -q q args) ffirst))

(defn q1-by
  "Return single entity id by attribute existence or attribute value"
  ([db attr]
    (->> (-q '[:find ?e :in $ ?a :where [?e ?a]] db attr) ffirst))
  ([db attr value]
    (->> (-q '[:find ?e :in $ ?a ?v :where [?e ?a ?v]] db attr value) ffirst)))

(defn q1s
  "Return seq of first elements of each tuple"
  [q & args]
  (->> (apply -q q args) (map first)))

(defn qe
  "If queried entity id, return single entity of first result"
  [q db & sources]
  (->> (apply -q q db sources)
       ffirst
       (d/entity db)))

(defn qes
  "If queried entity ids, return all entities of result"
  [q db & sources]
  (->> (apply -q q db sources)
       (map #(d/entity db (first %)))))

(defn qe-by
  "Return single entity by attribute existence or specific value"
  ([db attr]
    (qe '[:find ?e :in $ ?a :where [?e ?a]] db attr))
  ([db attr value]
    (qe '[:find ?e :in $ ?a ?v :where [?e ?a ?v]] db attr value)))

(defn qes-by
  "Return all entities by attribute existence or specific value"
  ([db attr]
    (qes '[:find ?e :in $ ?a :where [?e ?a]] db attr))
  ([db attr value]
    (qes '[:find ?e :in $ ?a ?v :where [?e ?a ?v]] db attr value)))

(defn qmap
  "Convert returned 2-tuples to a map"
  [q & sources]
  (into {} (apply -q q sources)))

;; Next two functions come from https://github.com/bricef/datascript-workshop/blob/master/src/cljs/datascript-workshop/utils.cljs
;; Eclipse public license 1.0 or later
;; TODO: Ensure that this bind operation is performant
;; TODO: Figure out how to benchmark this)
(defn bind
  "Bind a component's atom to a DataScript query
   Updating atom upon underlying data changes"
  ([conn q]
   (bind conn q (atom nil)))
  ([conn q state]
   (let [k (uuid/make-random)]
     (reset! state (d/q q @conn))
     (d/listen! conn k (fn [tx-report]
                         (let [novelty (d/q q (:tx-data tx-report))]
                           (when (not-empty novelty) ;; Only update if query results actually changed
                             (reset! state (d/q q (:db-after tx-report)))))))
     (set! (.-__key state) k)
     state)))

(defn unbind
  "Remove query binding"
  [conn state]
  (d/unlisten! conn (.-__key state)))

;; The dangerous function is take from reagent cookbook
;; https://github.com/reagent-project/reagent-cookbook/tree/master/old-recipes/page-from-markdown
;; Which has no license but is meant to promote reagent usage
(defn dangerous
  "Dangerously set inner html of a react component
   Used for in-browser markdown rendering"
  ([comp content]
     (dangerous comp nil content))
  ([comp props content]
     [comp (assoc props :dangerouslySetInnerHTML {:__html content })]))
