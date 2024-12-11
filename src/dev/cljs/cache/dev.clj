(ns cljs.cache.dev
  (:require [clojure.tools.build.api :as b]))

(def lib 'com.github.pkpkpk/cljs-cache)

(def version (format "1.0.%s" (b/git-count-revs nil)))
(def basis (b/create-basis {:project "deps.edn"}))
(def jar-file (format "target/%s-%s.jar" (name lib) version))

(defn clean
  ([] (b/delete {:path "target"}))
  ([_] (b/delete {:path "target"})))

(defn jar []
  (b/jar {:class-dir nil
          :src-pom "./template/pom.xml"
          :lib lib
          :version version
          :basis basis
          :jar-file jar-file
          :src-dirs ["src/main" "target/deps"]}))

