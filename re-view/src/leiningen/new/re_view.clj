(ns leiningen.new.re-view
    (:use [leiningen.new.templates :only [renderer name-to-path sanitize-ns ->files]]))

(def render (renderer "re-view"))

(defn re-view
      [name]
      (let [data {:name      name
                  :ns-name   (sanitize-ns name)
                  :sanitized (name-to-path name)}]
           (->files data
                    ["src/{{sanitized}}/core.cljs" (render "src/re_app/core.cljs" data)]
                    ["project.clj" (render "project.clj" data)]
                    ["resources/public/css/styles.css" (render "resources/public/css/styles.css")]
                    ["resources/public/index.html" (render "resources/public/index.html")])))