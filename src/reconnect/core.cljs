(ns reconnect.core
  (:require [react-dom]
            [reagent.core :as r]
            [reagent.dom :as rd]))

;; data
(def interactions
  [{:name "Jane" :date "22 Feb" :type "meet"}
   {:name "James" :date "21 Feb" :type "meet"}
   {:name "Clara" :date "20 Feb" :type "call"}
   {:name "David" :date "20 Feb" :type "meet"}])

(def people
  [{:name "Jane" :frequency 14}
   {:name "James" :frequency 7}
   {:name "Clara" :frequency 60}])

;; helpers
(defn daysago
  [isodate]
  (let [days (-> (- (js/Date.) (js/Date. isodate))
                 (/ 1000)
                 (/ 60)
                 (/ 60)
                 (/ 24)
                 Math/floor)]
    (str days " days ago")))

;; components
(defn alerts
  []
  [:div "Get in touch"
   [:ul
    [:li "Jane"]
    [:li "James"]]])

(defn event-item
  [item]
  [:li {:class (item :type)} (item :name)])

(defn event-list
  [items]
  (into [:ul]
        (map (fn [item] [event-item item]) items)))

(defn by-date
  [data]
  [:div "History"
   (into [:ul]
         (map (fn [[date events]] [:li date [event-list events]]) (group-by :date data)))])

(defn app-component
  []
  [:div
   [alerts]
   [by-date interactions]])

;; main
(defn mount
  []
  (rd/render [app-component]
             (.querySelector js/document "#app")))

(mount)
