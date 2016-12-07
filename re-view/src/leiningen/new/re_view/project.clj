(defproject {{ns-name}} "0.0.1-SNAPSHOT"
  :description "A re-view project."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.293"]
                 [org.clojars.mhuebert/re-view "0.2.5-SNAPSHOT"]]

  :plugins [[lein-figwheel "0.5.8"]
            [lein-cljsbuild "1.1.4" :exclusions [org.clojure/clojure]]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"]

  :source-paths ["src"]

  :cljsbuild {:builds [{:id           "dev"
                        :source-paths ["src"]
                        :figwheel     true
                        :compiler     {:main                 "{{ns-name}}.core"
                                       :asset-path           "/js/compiled/out"
                                       :output-to            "resources/public/js/compiled/app.js"
                                       :output-dir           "resources/public/js/compiled/out"
                                       :source-map-timestamp true
                                       :parallel-build       true}}
                       {:id           "prod"
                        :source-paths ["src"]
                        :compiler     {:main          "{{ns-name}}.core"
                                       :output-to     "resources/public/js/compiled/app.js"
                                       :output-dir    "resources/public/js/compiled/out-prod"
                                       :optimizations :advanced}}]}

  :figwheel {:css-dirs     ["resources/public/css"]
             :ring-handler figwheel-server.core/handler
             :server-port  4000}

  :profiles {:dev {:dependencies [[figwheel-pushstate-server "0.1.0"]
                                  [binaryage/devtools "0.8.2"]
                                  [figwheel-sidecar "0.5.8"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src" "dev"]
                   ;; for CIDER
                   :plugins      [[cider/cider-nrepl "0.14.0"]]
                   :repl-options {; for nREPL dev you really need to limit output
                                  :init             (set! *print-length* 50)
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
