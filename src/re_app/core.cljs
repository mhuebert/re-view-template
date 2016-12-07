(ns re-app.core
  (:require
    [re-view.routing :as r]
    [re-view.core :as v :refer [defview]]
    [re-db.d :as d]))

(enable-console-print!)

(defonce _ (r/on-route #(d/transact! [[:db/add ::state :route %]])))

(defview home
  [:div "Hello, world!"])

(defview greeting
  (fn [{:keys [props]}]
    [:div "Hello, " (:name props) "!"]))

(defview layout
  [:div.h-100
   [:.dib.center.left-50
    (for [[href title] [["/" "Home"]
                        ["/greeting/matt" "Greeting"]
                        ["/nothing-here" "404"]]]
      [:a.dib.pa2.black-70.no-underline.f6.bg-black-05 {:href href} title])]
   (r/router (d/get ::state :route)
             "/" home
             "/greeting/:name" greeting
             [:div "Not found!"])])

(defn main []
  (v/render-to-dom (layout) "app-main"))

(main)