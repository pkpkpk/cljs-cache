(ns cljs.cache.dev
  (:require [clojure.repl :refer :all]
            [clojure.tools.build.api :as b]
            [deps-deploy.deps-deploy :as dd]))

(def lib 'com.github.pkpkpk/cljs-cache)

(def version (format "1.0.%s" (b/git-count-revs nil)))
(def basis (b/create-basis {:project "deps.edn"}))
(def jar-file (format "target/%s-%s.jar" (name lib) version))
(def class-dir "target/classes")

(defn clean [] (b/delete {:path "target"}))

(def pom {:src-dirs  ["src/main"]
          :class-dir class-dir
          :lib       lib
          :version   version
          :basis     basis})

(defn jar []
  (b/write-pom pom)
  (b/copy-dir {:src-dirs  ["src/main"]
               :target-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file jar-file}))

(defn deploy []
  (dd/deploy {:artifact jar-file
              :installer :remote
              :pom-file (b/pom-path pom)}))