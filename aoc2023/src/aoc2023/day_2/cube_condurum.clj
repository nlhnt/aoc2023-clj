(ns aoc2023.day-2.cube-condurum
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as s]))

(defn read-lines-create-vector [file-path]
  (->> (io/reader file-path) ; Open a reader for the file
       (line-seq) ; Read lines as a lazy sequence
       (vec) ; Convert the lazy sequence to a vector
       ))

(def file-path "src/aoc2023/day_1/input") ; Define filepath

(def lines-vector (read-lines-create-vector file-path))

(defn find-first-two-numbers [input-str]
  (let [numbers (re-seq #"\d+" input-str)]
    (take 2 (map #(Integer. %) numbers))))

(defn find-first-two-digits [input-str]
  (let [numbers (re-seq #"\d" input-str)]
    (take 2 (map #(Integer. %) numbers))))

(def digits-map {"zero" "0"
                 "one" "1"
                 "two" "2"
                 "three" "3"
                 "four" "4"
                 "five" "5"
                 "six" "6"
                 "seven" "7"
                 "eight" "8"
                 "nine" "9"})

(doseq [[k v] digits-map] (
                           (println {(apply str (reverse k)) v}) 
                          ;;  (println k)
                           ))

(doseq [[k v] {:one "two" :three "four"}] (println "Found key" k "with value" v))

(doseq [[k v] {:one "two" :three "four"}] (println "Found key" (apply str (reverse (str k))) "with value " v) )


(doseq [[k v] digits-map] (println "Found key" (apply str (reverse (str k))) "with value " v))

(def reverse-digits-map
  (reduce (fn [extended-digits-map [k v]]
            (assoc extended-digits-map (apply str (reverse k)) v))
          {}
          digits-map)
)
reverse-digits-map

(def full-digits-map (
                      conj digits-map reverse-digits-map
))
full-digits-map

(doseq [[k v] digits-map] (
                           let [new-map digits-map] (
                                                     (conj new-map {(apply str (reverse k)) v})
                                                     (println new-map))))

(doseq [el digits-map] (println el))

;; (doseq [el [1 2 3]] (println el))

(get digits-map "zero")

(def digits-regex #"\d|zero|one|two|three|four|five|six|seven|eight|nine")


(println digits-regex)

(def reverse-digits-regex-string
  (reduce (fn [current-str spelled-digit]
    (apply str (concat current-str "|" spelled-digit))) "" (keys reverse-digits-map)))

;; (defn union-re-patterns [& patterns]
;;   (re-pattern (apply str (interpose "|" (map #(str "(?:" % ")") patterns)))))

;; (union-re-patterns )

(def reverse-digits-regex (re-pattern (apply str (concat (str #"\d") reverse-digits-regex-string))))
reverse-digits-regex


(defn get-first-and-last-digits-spelled-too [input-str]
  (let [numbers (re-seq #"\d|zero|one|two|three|four|five|six|seven|eight|nine" input-str)]
    [(first numbers) (last numbers)]))

(defn spelled-digit-to-number [spelled-digit]
  (case (s/lower-case spelled-digit)
    "zero" 0
    "one" 1
    "two" 2
    "three" 3
    "four" 4
    "five" 5
    "six" 6
    "seven" 7
    "eight" 8
    "nine" 9
    ; Add more cases as needed
    :else nil)) ; Return nil for unknown spelled digits

(defn get-first-and-last-digits [input-str]
  (let [numbers (re-seq #"\d" input-str)]
    [(first (map #(Integer. %) numbers)) (last (map #(Integer. %) numbers))]))

(defn concatenate-numbers [numbers]
  (apply str numbers))

(defn process-line [input-str]
  ((comp concatenate-numbers get-first-and-last-digits) input-str))

(defn sum-calibration-values [dirty-calibration-sequence]
  ((comp + (map process-line)) dirty-calibration-sequence))

(def all-two-digit-numbers (map #(Integer. %) (map concatenate-numbers (map get-first-and-last-digits lines-vector))))

(def result (apply + all-two-digit-numbers))

;; in lein repl
;; (require '[aoc2023.day-1.trebuchet :as t] :reload)
;; show result with `t/result`