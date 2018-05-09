(ns ministrants.core)

(defn from-result
  "Extracts value for ks from a single sample. ks must be in result from
  an anglican query. ks must be given as vector:
      (from-result a-sample [:a :b])"
  [sample ks]
  (get-in sample (flatten [:result ks])))

(defn from-results
  "Extracts values for key ks samples. See from-result."
  [samples ks]
  (reduce
    #(conj %1 (from-result %2 ks))
    []
    samples))

(defn histo-spec
  "Returns a vega-lite spec for plotting a histogram for seq of values."
  ([values binning-params]
  {:data {:values values}
   :mark "bar"
   :encoding {:x {:bin binning-params
                  :field "data"
                  :type "quantitative"}
              :y {:aggregate "count"
                  :type "quantitative"}}})
  ([values]
   (histo-spec values "true")))
