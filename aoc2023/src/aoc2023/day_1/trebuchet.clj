(ns aoc2023.day-1.trebuchet
  (:require [clojure.java.io :as io]))

(defn read-lines-create-vector [file-path]
  (->> (io/reader file-path) ; Open a reader for the file
       (line-seq) ; Read lines as a lazy sequence
       (vec) ; Convert the lazy sequence to a vector
  ))

(def file-path "src/aoc2023/day_1/input") ; Define filepath

(def lines-vector (read-lines-create-vector file-path))

(defn find-first-two-numbers [input-str]
  (let [numbers (re-seq #"\d+" input-str)]
    (take 2 (map #(Integer. %) numbers)))
  )

(defn find-first-two-digits [input-str]
  (let [numbers (re-seq #"\d" input-str)]
    (take 2 (map #(Integer. %) numbers))))

(defn get-first-and-last-digits [input-str]
  (let [numbers (re-seq #"\d" input-str)]
     [
      (first (map #(Integer. %) numbers)) (last (map #(Integer. %) numbers))
      ]
    )
  )

(defn concatenate-numbers [numbers]
  (apply str numbers))

(defn process-line [input-str]
  ((comp concatenate-numbers get-first-and-last-digits) input-str)
  )

(defn sum-calibration-values [dirty-calibration-sequence]
  ((comp + (map process-line)) dirty-calibration-sequence)
  )

(def all-two-digit-numbers (map #(Integer. %) (map concatenate-numbers (map get-first-and-last-digits lines-vector))))

(def result (apply + all-two-digit-numbers))

;; in lein repl
;; (require '[aoc2023.day-1.trebuchet :as t] :reload)
;; show result with `t/result`