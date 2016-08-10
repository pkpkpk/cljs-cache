#cljs.cache : Clojurescript port of clojure/core.cache

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.mmb90/cljs-cache.svg)](https://clojars.org/org.clojars.mmb90/cljs-cache)

* An underlying `CacheProtocol` used as the base abstraction for implementing new synchronous caches

* A `defcache` macro for hooking your `CacheProtocol` implementations into the Clojurescript associative data capabilities.

* Implementations of some basic caching strategies
  - Least-recently-used (LRUCache)
  - Time-to-live (TTLCache)
  - Naive cache (BasicCache)
  
* Factory functions for each existing cache type

##Example Usage

```clojure
    (require '[cljs.cache :as cache])
	
    (def C (cache/lru-cache-factory {:a 1, :b 2} :threshold 2))
	
    (if (cache/has? C :c)
      (cache/hit C :c)
      (cache/miss C :c 42))
	
    ;=> {:b 2, :c 42}
	
    (cache/evict C :b)
	
    ;=> {:a 1}
    
    (def C (cache/lru-cache-factory {:a 1, :b 2} :threshold 3))
	
    (if (cache/has? C :c)
      (cache/hit C :c)
      (cache/miss C :c 42))
	
	;=> {:a 1, :b 2, :c 42}
	
	;; Technically in order to see those results at the repl you'd have
	;; to use .-cache but I left it off for clarity since that's what's
	;; in the cache.
	
	(.-cache (cache/evict C :b))
	
	;=> {:a 1}
```

Refer to docstrings in the `clojure.core.cache` namespace, or the [autogenerated API documentation](http://clojure.github.com/core.cache/) for additional documentation

## License ##

    Copyright (c) Rich Hickey. All rights reserved. The use and
    distribution terms for this software are covered by the Eclipse
    Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
    which can be found in the file epl-v10.html at the root of this
    distribution. By using this software in any fashion, you are
    agreeing to be bound by the terms of this license. You must
    not remove this notice, or any other, from this software.
