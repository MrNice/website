(ns website.utils)

(defn dangerous
  ([comp content]
     (dangerous comp nil content))
  ([comp props content]
     [comp (assoc props :dangerouslySetInnerHTML {:__html content })]))