(ns documeds.templates.layouts
  (:require [noir.session :as sess]
            [noir.options :as options])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers
        hiccup.form-helpers))

(defn javascript-assets []
  (if (options/dev-mode?)
    (include-js "/assets/autocomplete.js"
                "/assets/dusts/autocomplete/result.js")
    (include-js "/assets/production.js")))

(defn css-assets []
  (if (options/dev-mode?)
    (include-css "/css/bootstrap.css"
                 "/css/app.css")
    (include-css "/assets/production.css")))

(defpartial application [& content]
  (html5
    [:head
      (include-js "/assets/vendor.js")
      [:title "Clojure AutoComplete"]
      (css-assets)]
    [:body
      [:div#wrapper
        (when-let [message (sess/flash-get)]
          [:div#flash message])
        [:div#search
          [:input {:class "text" :id "autocomplete" :type "text" :autocomplete "off" :name "q"}]
          [:button {:class "btn btn-primary"} "Go"]]
        [:div.spacer]
        [:div#results
          [:ul#resultsList]]
        content " "
        [:br]]
        (javascript-assets)]))

(defn flash! [message]
  (sess/flash-put! message)
  nil)
