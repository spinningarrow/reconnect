(ns reconnect.core
  (:require [react-dom]
            [reagent.core :as r]
            [reagent.dom :as rd]))


(def data
  {:test [{:name "Jane"
           :things [{:type :phone-call :date "2021-02-07T19:03:59.039Z"}]}
          {:name "Sahil"
           :things [{:type :phone-call :date "2021-02-08T19:03:59.039Z"}
                    {:type :meeting :date "2021-02-09T19:03:59.039Z"}]}]})

(defn daysago
  [isodate]
  (let [days (-> (- (js/Date.) (js/Date. isodate))
                 (/ 1000)
                 (/ 60)
                 (/ 60)
                 (/ 24)
                 Math/floor)]
    (str days " days ago")))

(defn average [a b]
  (/ (+ a b) 2.0))

(defn thing
  [item]
  [:div
   [:p (item :name)]
   [:p (daysago (:date (get (item :things) 0)))]])

(defn data-list
  []
  [:div "I am the data list"
   (into [:ul]
         (map (fn [item] [:li [thing item]]) (data :test)))])

(defn app-component
  []
  [:div "Hello I am the app"
   [data-list]])

(defn mount
  []
  (rd/render [app-component]
             (.querySelector js/document "#app")))

(mount)
